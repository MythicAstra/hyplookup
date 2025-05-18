/*
 * Copyright (C) 2025 MythicAstra
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
import java.util.concurrent.atomic.AtomicBoolean
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import net.sharedwonder.hyplookup.data.PlayerData
import net.sharedwonder.hyplookup.util.McText
import net.sharedwonder.lightproxy.packet.PacketUtils
import org.apache.logging.log4j.LogManager

class PlayerListUpdater(val hypLookupContext: HypLookupContext) : Thread("HYPL-PlayerListUpdater") {
    private val updatingSync = SynchronousQueue<Any>()

    private val continuingSync = SynchronousQueue<Any>()

    private val none = Any()

    private var needUpdate = AtomicBoolean(true)

    private var canContinue = AtomicBoolean(true)

    private var queryingThreadExecutor = Executors.newVirtualThreadPerTaskExecutor()

    fun triggerUpdating() {
        needUpdate.set(true)
        continueUpdating()
        updatingSync.offer(none)
    }

    override fun run() {
        try {
            while (!isInterrupted) {
                if (needUpdate.getAndSet(false)) {
                    update()
                    continue
                }

                updatingSync.take()
            }
        } catch (_: InterruptedException) {
        } finally {
            queryingThreadExecutor.shutdownNow()
        }
    }

    private fun update() {
        try {
            var players = ArrayList<Pair<UUID, String>>(hypLookupContext.players.size)
            for ((uuid, name) in hypLookupContext.players) {
                if (isInterrupted || needUpdate.get()) {
                    return
                }

                players.add(uuid to name)
                PlayerDataFetcher.fetch(uuid, name, queryingThreadExecutor) { continueUpdating() }
            }

            if (isInterrupted || needUpdate.get()) {
                return
            }

            val uuids = ArrayList<UUID>(players.size)
            val table = ArrayList<Array<String>>(players.size)

            while (players.isNotEmpty() && !isInterrupted && !needUpdate.get()) {
                canContinue.set(false)

                val next = ArrayList<Pair<UUID, String>>(players.size)

                for ((uuid, name) in players) {
                    if (isInterrupted || needUpdate.get()) {
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

                if (isInterrupted || needUpdate.get()) {
                    return
                }

                val packet = buildPacket(uuids, table)
                hypLookupContext.connectionContext.sendToClient(packet)
                players = next

                if (isInterrupted || needUpdate.get()) {
                    return
                }

                continuingSync.take()
            }
        } catch (exception: InterruptedException) {
            throw exception
        } catch (exception: Exception) {
            logger.error("An error occurred while updating player list", exception)
        }
    }

    private fun continueUpdating() {
        canContinue.set(true)
        continuingSync.offer(none)
    }

    private fun buildText(uuid: UUID, name: String): Array<String>? {
        val playerData = PlayerDataFetcher.getCached(uuid) ?: return null

        val namePrefix = hypLookupContext.playerToTeam[name]?.let { hypLookupContext.teamPrefix[it] } ?: ""
        val namePostfix = hypLookupContext.playerToTeam[name]?.let { hypLookupContext.teamPostfix[it] } ?: ""

        val originalText = "$namePrefix$name$namePostfix"
        return if (playerData is PlayerData) {
            checkNotNull(hypLookupContext.currentGame).buildShortStatsText(playerData, originalText)
        } else {
            arrayOf("${McText.DARK_RED}${McText.BOLD}NICK ${McText.RESET}$originalText")
        }
    }

    private fun buildPacket(uuids: List<UUID>, table: List<Array<String>>): ByteBuf {
        val chunk = Unpooled.buffer()
        chunk.writeByte(PLAYER_LIST_UPDATE_DISPLAY_NAME)
        PacketUtils.writeVarint(chunk, uuids.size)

        val textTable = McText.alignTextTable(table, "${McText.DARK_GRAY} | ")
        for ((uuid, text) in uuids zip textTable) {
            PacketUtils.writeUuid(chunk, uuid)
            chunk.writeBoolean(true)
            PacketUtils.writeUtf8String(chunk, McText.serialize(text))
        }

        val size = chunk.readableBytes() + 1
        val packet = Unpooled.buffer(size + PacketUtils.VARINT_MAX_SIZE)
        PacketUtils.writeVarint(packet, size)
        PacketUtils.writeVarint(packet, PID_SP_UPDATE_PLAYER_LIST)
        packet.writeBytes(chunk)
        chunk.release()
        return packet
    }
}

private val logger = LogManager.getLogger(PlayerListUpdater::class.java)
