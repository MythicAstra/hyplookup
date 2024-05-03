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

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import net.sharedwonder.mc.ptbridge.ConnectionContext
import net.sharedwonder.mc.ptbridge.addon.ExternalContext

class HypLookupContext(val connectionContext: ConnectionContext) : ExternalContext {
    @JvmField val playerUuidToName: MutableMap<UUID, String> = HashMap()

    @JvmField val playerNameToUuid: MutableMap<String, UUID> = HashMap()

    @JvmField val teamToPlayers: MutableMap<String, MutableSet<String>> = HashMap()

    @JvmField val playerToTeam: MutableMap<String, String> = HashMap()

    @JvmField val teamPrefix: MutableMap<String, String> = ConcurrentHashMap()

    @JvmField val teamPostfix: MutableMap<String, String> = ConcurrentHashMap()

    @JvmField val scoreboard: Scoreboard = Scoreboard()

    @JvmField val scoreboardObjectives: MutableMap<String, String> = HashMap()

    @JvmField val playerListDisplay: PlayerListDisplay = PlayerListDisplay(this)

    var detectedGame: HypixelGame? = null
        private set

    override fun onDisconnect() {
        playerListDisplay.stopOverwriting()
    }

    fun detectGame(scoreboardTitle: String) {
        detectedGame = HypixelGame.getByScoreboardTitle(scoreboardTitle)
        if (detectedGame == null) {
            playerListDisplay.stopOverwriting()
        }
    }
}
