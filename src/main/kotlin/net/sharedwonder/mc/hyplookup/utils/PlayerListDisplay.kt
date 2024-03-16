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

package net.sharedwonder.mc.hyplookup.utils

import net.sharedwonder.mc.hyplookup.handlers.SPUpdatePlayerList
import net.sharedwonder.mc.hyplookup.query.HypixelPlayerData
import net.sharedwonder.mc.hyplookup.query.RealPlayerData
import net.sharedwonder.mc.ptbridge.packet.PacketUtils
import net.sharedwonder.mc.ptbridge.utils.FormattedText
import net.sharedwonder.mc.ptbridge.utils.TextUtils
import kotlin.concurrent.thread
import java.util.LinkedList
import java.util.UUID
import io.netty.buffer.Unpooled
import org.apache.logging.log4j.LogManager

class PlayerListDisplay(private val hypLookupContext: HypLookupContext) {
    private var game: HypixelGame? = null

    private val players = HashMap<String, UUID>()

    private var updatingThread: Thread? = null

    fun startOverwriting() {
        game = hypLookupContext.detectedGame
        for ((playerName, playerUuid) in hypLookupContext.playerNameToUuid) {
            players[playerName] = playerUuid
            PlayerDataCaches.queryPlayerDataInThread(playerName, playerUuid)
        }
        update()
    }

    fun stopOverwriting() {
        stopUpdating()
        PlayerDataCaches.stopAllQueryingThreads()
        game = null
        players.clear()
    }

    fun update() {
        if (game == null) {
            return
        }

        stopUpdating()
        val initQueue = players.mapTo(LinkedList()) { it.key to it.value }
        updatingThread = thread(name = "HYPL-UpdatePlayerListDisplay") {
            try {
                var queue = initQueue
                val passed = ArrayList<UUID>(queue.size)
                val table = ArrayList<Array<String>>(queue.size)

                while (queue.isNotEmpty()) {
                    if (Thread.currentThread().isInterrupted) {
                        return@thread
                    }

                    val waiting = LinkedList<Pair<String, UUID>>()
                    var hasNew = false

                    if (Thread.currentThread().isInterrupted) {
                        return@thread
                    }

                    while (queue.isNotEmpty()) {
                        val (playerName, playerUuid) = queue.poll()

                        if (Thread.currentThread().isInterrupted) {
                            return@thread
                        }

                        val displayText: Array<String>?
                        try {
                            displayText = getDisplayText(playerName, playerUuid)
                        } catch (exception: IllegalStateException) {
                            return@thread
                        }

                        if (Thread.currentThread().isInterrupted) {
                            return@thread
                        }

                        if (displayText == null) {
                            waiting.add(playerName to playerUuid)
                            continue
                        }
                        passed.add(playerUuid)
                        table.add(displayText)
                        hasNew = true

                        if (Thread.currentThread().isInterrupted) {
                            return@thread
                        }
                    }

                    if (Thread.currentThread().isInterrupted) {
                        return@thread
                    }

                    queue = waiting

                    if (hasNew) {
                        val chunk = Unpooled.buffer()
                        chunk.writeByte(SPUpdatePlayerList.UPDATE_DISPLAY_NAME)
                        PacketUtils.writeVarint(chunk, passed.size)

                        if (Thread.currentThread().isInterrupted) {
                            chunk.release()
                            return@thread
                        }

                        val alignedTexts = TextUtils.alignTextTable(table, "${FormattedText.DARK_GRAY} | ")
                        for ((uuid, text) in passed zip alignedTexts) {
                            if (Thread.currentThread().isInterrupted) {
                                chunk.release()
                                return@thread
                            }
                            PacketUtils.writeUuid(chunk, uuid)
                            chunk.writeBoolean(true)
                            PacketUtils.writeUtf8String(chunk, TextUtils.serialize(text))
                        }

                        if (Thread.currentThread().isInterrupted) {
                            chunk.release()
                            return@thread
                        }

                        val size = chunk.readableBytes() + 1
                        val packet = Unpooled.buffer(size + PacketUtils.VARINT_MAX_SIZE)
                        PacketUtils.writeVarint(packet, size)
                        PacketUtils.writeVarint(packet, PID_SP_UPDATE_PLAYER_LIST)
                        packet.writeBytes(chunk)
                        chunk.release()

                        if (Thread.currentThread().isInterrupted) {
                            return@thread
                        }

                        hypLookupContext.connectionContext.sendToClient(packet)
                    }
                }
            } catch (exception: Throwable) {
                LOGGER.error("An error occurred while updating player list", exception)
            } finally {
                updatingThread = null
            }
        }
    }

    fun fullUpdate() {
        stopUpdating()
        PlayerDataCaches.stopAllQueryingThreads()
        PlayerDataCaches.clearCaches()

        for ((playerName, playerUuid) in players) {
            PlayerDataCaches.queryPlayerDataInThread(playerName, playerUuid)
        }
        update()
    }

    fun addPlayer(playerName: String, playerUuid: UUID) {
        if (game != null) {
            stopUpdating()
            players[playerName] = playerUuid
            PlayerDataCaches.queryPlayerDataInThread(playerName, playerUuid)
            update()
        }
    }

    fun removePlayer(playerName: String) {
        if (game != null) {
            stopUpdating()
            players.remove(playerName)
            PlayerDataCaches.interruptQueryingThread(playerName)
            update()
        }
    }

    private fun stopUpdating() {
        updatingThread?.interrupt()
        try {
            updatingThread?.join()
        } catch (exception: InterruptedException) {
            throw RuntimeException(exception)
        }
        updatingThread = null
    }

    private fun getDisplayText(playerName: String, playerUuid: UUID): Array<String>? {
        checkNotNull(game)

        val playerData: HypixelPlayerData = PlayerDataCaches.getCachedPlayerData(playerUuid) ?: return null

        val namePrefix: String = hypLookupContext.playerToTeam[playerName]?.let { hypLookupContext.teamPrefix[it] } ?: ""
        val namePostfix: String = hypLookupContext.playerToTeam[playerName]?.let { hypLookupContext.teamPostfix[it] } ?: ""

        val originalText = "${FormattedText.RESET}$namePrefix$playerName$namePostfix"
        if (playerData is RealPlayerData) {
            return checkNotNull(game).displayText(playerData, originalText)
        } else {
            return arrayOf("${FormattedText.DARK_RED}${FormattedText.BOLD}NICK $originalText")
        }
    }

    private companion object {
        private val LOGGER = LogManager.getLogger(PlayerListDisplay::class.java)
    }
}
