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

package net.sharedwonder.hyplookup.command

import net.sharedwonder.hyplookup.HypLookupContext
import net.sharedwonder.hyplookup.PlayerDataFetcher
import net.sharedwonder.hyplookup.data.PlayerData
import net.sharedwonder.hyplookup.util.GameType
import net.sharedwonder.hyplookup.util.MCText
import net.sharedwonder.hyplookup.util.MojangAPI
import net.sharedwonder.lightproxy.util.UuidUtils
import org.apache.logging.log4j.LogManager

object QueryCommand : Command {
    override val expressions: Array<String> = arrayOf("query", "q")

    override val description: String = "Queries player stats in the Hypixel"

    private val logger = LogManager.getLogger(QueryCommand::class.java)

    override fun run(hypLookupContext: HypLookupContext, args: List<String>): String {
        if (args.size > 3) {
            throw CommandException("Too many arguments")
        }

        val name = if (args.isEmpty() || args[0] == ".") hypLookupContext.connectionContext.playerUsername else args[0]
        val game = (if (args.size > 1) GameType.getById(args[1]) else hypLookupContext.currentGame) ?: throw CommandException("Unsupported/unknown game")
        val modifier = if (args.size > 2) args[2] else null

        val (_, uuid) = try {
            MojangAPI.fetchPlayerProfile(name)
        } catch (exception: RuntimeException) {
            throw CommandException(exception.message)
        } ?: throw CommandException("Player not found: $name")

        val data = PlayerDataFetcher.fetch(uuid, name) ?: throw CommandException("Failed to query the player stats: $name/${UuidUtils.uuidToString(uuid)}")

        if (data !is PlayerData) {
            throw CommandException("Player not found: $name")
        }

        return "${MCText.GREEN}$name${MCText.RESET}'s ${MCText.YELLOW}${game.gameName}${MCText.RESET} stats:\n${game.buildStatsText(data, modifier)}"
    }
}
