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

package net.sharedwonder.mc.hyplookup.command

import net.sharedwonder.mc.hyplookup.query.MojangAPI
import net.sharedwonder.mc.hyplookup.query.RealPlayerData
import net.sharedwonder.mc.hyplookup.utils.HypLookupContext
import net.sharedwonder.mc.hyplookup.utils.HypixelGame
import net.sharedwonder.mc.hyplookup.utils.PlayerDataCaches
import net.sharedwonder.mc.ptbridge.ConnectionContext
import net.sharedwonder.mc.ptbridge.utils.FormattedText
import net.sharedwonder.mc.ptbridge.utils.UUIDUtils
import org.apache.logging.log4j.LogManager

object QueryCommand : Command {
    override val expressions: Array<String> = arrayOf("query", "q")

    override val description: String = "Queries the data of a player in the Hypixel"

    private val LOGGER = LogManager.getLogger(QueryCommand::class.java)

    override fun run(connectionContext: ConnectionContext, hypLookupContext: HypLookupContext, args: List<String>): String {
        val player = args.getOrElse(0) { connectionContext.playerUsername }.let { if (it == ".") connectionContext.playerUsername else it }
        val game = if (args.size > 1) HypixelGame.getByName(args[1]) else hypLookupContext.detectedGame
        if (game == null) {
            throw CommandException("Unsupported/unknown game")
        }
        val modifier = if (args.size > 2) args[2] else null

        val (name, uuid) = try {
            MojangAPI.queryPlayerProfile(player)
        } catch (exception: RuntimeException) {
            LOGGER.warn("Failed to query player profile: $player", exception)
            throw CommandException("Failed to query player profile: $player")
        } ?: throw CommandException("Player not found: $player")

        val data = PlayerDataCaches.queryPlayerData(name, uuid, 5) ?: throw CommandException("Failed to query player data: $name/${UUIDUtils.uuidToString(uuid)}")
        return "${FormattedText.YELLOW}${game.gameName}${FormattedText.RESET} stats of ${FormattedText.GREEN}$name${FormattedText.RESET}:\n" +
            game.queryStatsMessage(data as RealPlayerData, modifier)
    }
}
