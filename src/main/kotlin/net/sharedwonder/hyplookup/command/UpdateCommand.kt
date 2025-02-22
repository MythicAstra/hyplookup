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

object UpdateCommand : Command {
    override val expressions: Array<String> = arrayOf("update", "u", "up")

    override val description: String = "Clears cache to update player data"

    override fun run(hypLookupContext: HypLookupContext, args: List<String>): String {
        if (args.isNotEmpty()) {
            throw CommandException("Too many arguments")
        }

        PlayerDataFetcher.clearCache()
        hypLookupContext.updatePlayerList()

        return "Updating player data..."
    }
}
