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

package net.sharedwonder.hyplookup.util

import java.util.UUID
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import net.sharedwonder.hyplookup.HypLookupContext
import net.sharedwonder.hyplookup.PID_SP_UPDATE_PLAYER_LIST
import net.sharedwonder.hyplookup.PLAYER_LIST_UPDATE_DISPLAY_NAME
import net.sharedwonder.hyplookup.data.RealNamePlayer
import net.sharedwonder.lightproxy.packet.PacketUtils
import net.sharedwonder.lightproxy.util.MCText
import org.apache.logging.log4j.LogManager

class PlayerListUpdater(val hypLookupContext: HypLookupContext) : Thread("HYPL-PlayerListUpdater") {
    private val updatingLock = ReentrantLock()

    private val needUpdate = updatingLock.newCondition()

    private val continuingLock = ReentrantLock()

    private val canContinue = continuingLock.newCondition()

    @Volatile
    private var needUpdateBool = true

    @Volatile
    private var canContinueBool = true

    private var queryingThreadExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2)

    fun triggerUpdating() {
        needUpdateBool = true
        continueUpdating()
        updatingLock.lock()
        try {
            needUpdate.signal()
        } finally {
            updatingLock.unlock()
        }
    }

    override fun run() {
        try {
            while (!isInterrupted) {
                if (needUpdateBool) {
                    needUpdateBool = false
                    update()
                    continue
                }

                updatingLock.lock()
                try {
                    if (!needUpdateBool) {
                        needUpdate.await()
                    }
                } catch (exception: InterruptedException) {
                    return
                } finally {
                    updatingLock.unlock()
                }
            }
        } finally {
            queryingThreadExecutor.shutdownNow()
        }
    }

    private fun continueUpdating() {
        canContinueBool = true
        continuingLock.lock()
        try {
            canContinue.signal()
        } finally {
            continuingLock.unlock()
        }
    }

    private fun update() {
        try {
            var players = ArrayList<Pair<UUID, String>>(hypLookupContext.players.size)
            for ((uuid, name) in hypLookupContext.players) {
                if (isInterrupted || needUpdateBool) {
                    return
                }

                players.add(uuid to name)
                PlayerDataFetcher.fetch(uuid, name, queryingThreadExecutor) { continueUpdating() }
            }

            if (isInterrupted || needUpdateBool) {
                return
            }

            val uuids = ArrayList<UUID>(players.size)
            val table = ArrayList<Array<String>>(players.size)

            while (players.isNotEmpty() && !isInterrupted && !needUpdateBool) {
                canContinueBool = false

                val next = ArrayList<Pair<UUID, String>>(players.size)

                for ((uuid, name) in players) {
                    if (isInterrupted || needUpdateBool) {
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

                if (isInterrupted || needUpdateBool) {
                    return
                }

                val packet = buildPacket(uuids, table)

                if (isInterrupted || needUpdateBool) {
                    return
                }

                hypLookupContext.connectionContext.sendToClient(packet)
                players = next

                if (canContinueBool) {
                    continue
                }

                continuingLock.lock()
                try {
                    if (!canContinueBool) {
                        canContinue.await()
                    }
                } catch (exception: InterruptedException) {
                    interrupt()
                    return
                } finally {
                    continuingLock.unlock()
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

        val originalText = "${MCText.RESET}$namePrefix$name$namePostfix"
        return if (playerData is RealNamePlayer) {
            checkNotNull(hypLookupContext.currentGame).buildStatsPlayerListText(playerData, originalText)
        } else {
            arrayOf("${MCText.DARK_RED}${MCText.BOLD}NICK $originalText")
        }
    }

    private fun buildPacket(uuids: ArrayList<UUID>, table: ArrayList<Array<String>>): ByteBuf {
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
}

private val logger = LogManager.getLogger(PlayerListUpdater::class.java)
