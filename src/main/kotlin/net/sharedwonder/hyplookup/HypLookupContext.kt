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
import net.sharedwonder.hyplookup.command.CommandParser
import net.sharedwonder.hyplookup.command.CommandRunner
import net.sharedwonder.hyplookup.util.HypixelGame
import net.sharedwonder.hyplookup.util.PlayerListUpdater
import net.sharedwonder.lightproxy.ConnectionContext
import net.sharedwonder.lightproxy.addon.ExternalContext

class HypLookupContext(val connectionContext: ConnectionContext) : ExternalContext {
    @JvmField val players: MutableMap<UUID, String> = ConcurrentHashMap()

    @JvmField val teamToPlayers: MutableMap<String, MutableSet<String>> = HashMap()

    @JvmField val playerToTeam: MutableMap<String, String> = HashMap()

    @JvmField val teamPrefix: MutableMap<String, String> = ConcurrentHashMap()

    @JvmField val teamPostfix: MutableMap<String, String> = ConcurrentHashMap()

    @JvmField val scoreboardObjectives: MutableMap<String, String> = HashMap()

    @JvmField var currentGame: HypixelGame? = null

    @JvmField var sidebarScoreboardName: String? = null

    private var playerListUpdater: PlayerListUpdater? = null

    private var commandRunner: CommandRunner? = null

    override fun afterLogin() {
        commandRunner = CommandRunner(connectionContext).apply { start() }
    }

    override fun onDisconnect() {
        commandRunner?.interrupt()
        stopDisplayingStats()
    }

    fun startDisplayingStats() {
        synchronized(this) {
            if (playerListUpdater == null) {
                playerListUpdater = PlayerListUpdater(this).apply { start() }
            }
        }
    }

    fun stopDisplayingStats() {
        synchronized(this) {
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

    fun updateDisplaying() {
        playerListUpdater?.triggerUpdating()
    }

    fun runCommand(parser: CommandParser) {
        checkNotNull(commandRunner) { "commandRunner is null" }.commandQueue.add(parser)
    }
}
