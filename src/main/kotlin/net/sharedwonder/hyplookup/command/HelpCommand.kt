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
import net.sharedwonder.hyplookup.util.MCText

object HelpCommand : Command {
    override val expressions: Array<String> = arrayOf("help", "h")

    override val description: String = "Shows the help of HypLookup commands"

    override fun run(hypLookupContext: HypLookupContext, args: List<String>): String {
        if (args.isNotEmpty()) {
            throw CommandException("Too many arguments")
        }

        val table = ArrayList<Array<String>>()
        for (command in CommandParser.commands) {
            table.add(arrayOf(command.expressions.joinToString("${MCText.GRAY}/${MCText.AQUA}", MCText.AQUA), MCText.YELLOW + command.description))
        }
        return "Help:\n" + MCText.alignTextTable(table, "${MCText.DARK_GRAY} - ").joinToString("\n")
    }
}
