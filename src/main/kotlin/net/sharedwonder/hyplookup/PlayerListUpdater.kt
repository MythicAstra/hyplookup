/*
 * Copyright (C) 2024 sharedwonder (Liu Baihao).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sharedwonder.hyplookup

import java.util.UUID
import java.util.concurrent.Executors
import java.util.concurrent.SynchronousQueue
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import net.sharedwonder.hyplookup.data.PlayerData
import net.sharedwonder.hyplookup.util.MCText
import net.sharedwonder.lightproxy.packet.PacketUtils
import org.apache.logging.log4j.LogManager

class PlayerListUpdater(val hypLookupContext: HypLookupContext) : Thread("HYPL-PlayerListUpdater") {
    private val updatingSync = SynchronousQueue<Any>()

    private val continuingSync = SynchronousQueue<Any>()

    private val none = Any()

    @Volatile
    private var needUpdate = true

    @Volatile
    private var canContinue = true

    private var queryingThreadExecutor = Executors.newVirtualThreadPerTaskExecutor()

    fun triggerUpdating() {
        needUpdate = true
        continueUpdating()
        updatingSync.offer(none)
    }

    override fun run() {
        try {
            while (!isInterrupted) {
                if (needUpdate) {
                    needUpdate = false
                    update()
                    continue
                }

                try {
                    updatingSync.take()
                } catch (exception: InterruptedException) {
                    return
                }
            }
        } finally {
            queryingThreadExecutor.shutdownNow()
        }
    }

    private fun continueUpdating() {
        canContinue = true
        continuingSync.offer(none)
    }

    private fun update() {
        try {
            var players = ArrayList<Pair<UUID, String>>(hypLookupContext.players.size)
            for ((uuid, name) in hypLookupContext.players) {
                if (isInterrupted || needUpdate) {
                    return
                }

                players.add(uuid to name)
                PlayerDataFetcher.fetch(uuid, name, queryingThreadExecutor) { continueUpdating() }
            }

            if (isInterrupted || needUpdate) {
                return
            }

            val uuids = ArrayList<UUID>(players.size)
            val table = ArrayList<Array<String>>(players.size)

            while (players.isNotEmpty() && !isInterrupted && !needUpdate) {
                canContinue = false

                val next = ArrayList<Pair<UUID, String>>(players.size)

                for ((uuid, name) in players) {
                    if (isInterrupted || needUpdate) {
                        return
                    }

                    val text = try {
                        buildText(uuid, name)
                    } catch (exception: IllegalStateException) {
                        return
                    }
                    if (text != null) {
                        uuids.add(uuid)
                        table.add(text)
                    } else {
                        next.add(uuid to name)
                    }
                }

                if (isInterrupted || needUpdate) {
                    return
                }

                val packet = buildPacket(uuids, table)

                if (isInterrupted || needUpdate) {
                    return
                }

                hypLookupContext.connectionContext.sendToClient(packet)
                players = next

                if (canContinue) {
                    continue
                }

                try {
                    continuingSync.take()
                } catch (exception: InterruptedException) {
                    interrupt()
                    return
                }
            }
        } catch (exception: Throwable) {
            logger.error("An error occurred while updating player list", exception)
        }
        return
    }

    private fun buildText(uuid: UUID, name: String): Array<String>? {
        val playerData = PlayerDataFetcher.getCached(uuid) ?: return null

        val namePrefix = hypLookupContext.playerToTeam[name]?.let { hypLookupContext.teamPrefix[it] } ?: ""
        val namePostfix = hypLookupContext.playerToTeam[name]?.let { hypLookupContext.teamPostfix[it] } ?: ""

        val originalText = "$namePrefix$name$namePostfix"
        return if (playerData is PlayerData) {
            checkNotNull(hypLookupContext.currentGame).buildShortStatsText(playerData, originalText)
        } else {
            arrayOf("${MCText.DARK_RED}${MCText.BOLD}NICK ${MCText.RESET}$originalText")
        }
    }

    private fun buildPacket(uuids: List<UUID>, table: List<Array<String>>): ByteBuf {
        val chunk = Unpooled.buffer()
        chunk.writeByte(PLAYER_LIST_UPDATE_DISPLAY_NAME)
        PacketUtils.writeVarint(chunk, uuids.size)

        val textTable = MCText.alignTextTable(table, "${MCText.DARK_GRAY} | ")
        for ((uuid, text) in uuids zip textTable) {
            PacketUtils.writeUuid(chunk, uuid)
            chunk.writeBoolean(true)
            PacketUtils.writeUtf8String(chunk, MCText.serialize(text))
        }

        val size = chunk.readableBytes() + 1
        val packet = Unpooled.buffer(size + PacketUtils.VARINT_MAX_SIZE)
        PacketUtils.writeVarint(packet, size)
        PacketUtils.writeVarint(packet, PID_SP_UPDATE_PLAYER_LIST)
        packet.writeBytes(chunk)
        chunk.release()
        return packet
    }

    companion object {
        private val logger = LogManager.getLogger(PlayerListUpdater::class.java)
    }
}
