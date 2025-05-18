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
import net.sharedwonder.hyplookup.util.GameType

object DisplayCommand : Command {
    override val keywords: Array<String> = arrayOf("display", "d")

    override val description: String = "Starts displaying player stats in the player list"

    override fun run(hypLookupContext: HypLookupContext, args: List<String>): String {
        val gameType = when (args.size) {
            0 -> hypLookupContext.currentGame
            1 -> GameType.getById(args[0])
            else -> throw CommandException("Too many arguments")
        }

        if (gameType == null) {
            throw CommandException("Unsupported/unknown game")
        }

        hypLookupContext.userTriggeredDisplayingStats(gameType)
        return "Started displaying player stats (${gameType.gameName}) in the player list"
    }
}
