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

package net.sharedwonder.hyplookup.command

import net.sharedwonder.hyplookup.HypLookupContext
import net.sharedwonder.hyplookup.data.RealNamePlayer
import net.sharedwonder.hyplookup.util.HypixelGame
import net.sharedwonder.hyplookup.util.MojangAPI
import net.sharedwonder.hyplookup.util.PlayerDataFetcher
import net.sharedwonder.lightproxy.util.MCText
import net.sharedwonder.lightproxy.util.UUIDUtils
import org.apache.logging.log4j.LogManager

object QueryCommand : Command {
    override val expressions: Array<String> = arrayOf("query", "q")

    override val description: String = "Queries a player's stats in the Hypixel"

    private val logger = LogManager.getLogger(QueryCommand::class.java)

    override fun run(hypLookupContext: HypLookupContext, args: List<String>): String {
        val clientPlayerName = hypLookupContext.connectionContext.playerUsername
        val name = args.getOrElse(0) { clientPlayerName }.let { if (it == ".") clientPlayerName else it }
        val game = if (args.size > 1) HypixelGame.getByName(args[1]) else hypLookupContext.currentGame
        if (game == null) {
            throw CommandException("Unsupported/unknown game")
        }
        val modifier = if (args.size > 2) args[2] else null

        val (_, uuid) = try {
            MojangAPI.fetchPlayerProfile(name)
        } catch (exception: RuntimeException) {
            logger.warn(exception)
            throw CommandException(exception.message)
        } ?: throw CommandException("Player not found: $name")

        val data = PlayerDataFetcher.fetch(uuid, name, attempts = 5) ?: throw CommandException("Failed to query the player's stats: $name/${UUIDUtils.uuidToString(uuid)}")

        return "${MCText.GREEN}$name${MCText.RESET}'s ${MCText.YELLOW}${game.gameName}${MCText.RESET} stats:\n${game.buildStatsMessage(data as RealNamePlayer, modifier)}"
    }
}
