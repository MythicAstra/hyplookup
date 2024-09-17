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
import java.util.concurrent.ConcurrentHashMap
import io.netty.buffer.Unpooled
import net.sharedwonder.hyplookup.command.CommandParser
import net.sharedwonder.hyplookup.command.CommandRunner
import net.sharedwonder.hyplookup.data.PlayerData
import net.sharedwonder.hyplookup.util.GameType
import net.sharedwonder.hyplookup.util.MCText
import net.sharedwonder.hyplookup.util.MojangAPI
import net.sharedwonder.lightproxy.ConnectionContext
import net.sharedwonder.lightproxy.addon.ExternalContext
import net.sharedwonder.lightproxy.packet.PacketUtils

class HypLookupContext(val connectionContext: ConnectionContext) : ExternalContext {
    @JvmField val players: MutableMap<UUID, String> = ConcurrentHashMap()

    @JvmField val teamToPlayers: MutableMap<String, MutableSet<String>> = HashMap()

    @JvmField val playerToTeam: MutableMap<String, String> = HashMap()

    @JvmField val teamPrefix: MutableMap<String, String> = ConcurrentHashMap()

    @JvmField val teamPostfix: MutableMap<String, String> = ConcurrentHashMap()

    @JvmField val scoreboardObjectives: MutableMap<String, String> = HashMap()

    @JvmField var sidebarScoreboardName: String? = null

    @JvmField @Volatile var currentGame: GameType? = null

    @JvmField @Volatile var displayingStats: Boolean = false

    @JvmField @Volatile var userTriggeredDisplayingStats: Boolean = false

    private var playerListUpdater: PlayerListUpdater? = null

    private var commandRunner: CommandRunner? = null

    override fun afterLogin() {
        commandRunner = CommandRunner(this).apply { start() }
    }

    override fun onDisconnect() {
        commandRunner?.interrupt()
        stopDisplayingStats()
    }

    fun startDisplayingStats() {
        synchronized(this) {
            displayingStats = true
            if (currentGame != null && playerListUpdater == null) {
                playerListUpdater = PlayerListUpdater(this).apply { start() }
            }
        }
    }

    fun stopDisplayingStats() {
        synchronized(this) {
            displayingStats = false
            userTriggeredDisplayingStats = false
            playerListUpdater?.let {
                it.interrupt()
                try {
                    it.join()
                } catch (exception: InterruptedException) {
                    throw RuntimeException(exception)
                } finally {
                    playerListUpdater = null
                }
            }
        }
    }

    fun updatePlayerList() {
        playerListUpdater?.triggerUpdating()
    }

    fun userTriggeredDisplayingStats(gameType: GameType) {
        synchronized(this) {
            userTriggeredDisplayingStats = true
            currentGame = gameType
            startDisplayingStats()
        }
    }

    fun whenScoreboardTitleUpdates(scoreboardTitle: String) {
        synchronized(this) {
            if (!userTriggeredDisplayingStats) {
                currentGame = GameType.getByScoreboardTitle(scoreboardTitle)
                if (currentGame == null) {
                    stopDisplayingStats()
                }
            }
        }
    }

    fun printStatsToChat(playerName: String, gameType: GameType) {
        Thread.ofVirtual().start {
            val (_, uuid) = try {
                MojangAPI.fetchPlayerProfile(playerName) ?: return@start
            } catch (exception: RuntimeException) {
                return@start
            }
            val player = PlayerDataFetcher.fetch(uuid, playerName)
            val text = if (player is PlayerData) {
                gameType.buildShortStatsText(player, playerName).joinToString(" ")
            } else {
                "${MCText.DARK_RED}${MCText.BOLD}NICK ${MCText.RESET}$playerName"
            }
            printMessageToChat(text)
        }
    }

    fun printMessageToChat(message: String) {
        val bytes = MCText.serialize("${MCText.BLUE}[HypLookup]${MCText.RESET} $message").toByteArray()

        val size = PacketUtils.calcVarintSize(bytes.size) + bytes.size + 2
        val packet = Unpooled.buffer(size + PacketUtils.VARINT_MAX_SIZE)
        PacketUtils.writeVarint(packet, size)
        PacketUtils.writeVarint(packet, PID_SP_UPDATE_MESSAGE)
        PacketUtils.writeByteArray(packet, bytes)
        packet.writeByte(CHAT_MESSAGE_TYPE)

        connectionContext.sendToClient(packet)
    }

    fun submitCommand(parser: CommandParser) {
        checkNotNull(commandRunner) { "this method cannot be called before the player logs in" }.offerCommand(parser)
    }
}
