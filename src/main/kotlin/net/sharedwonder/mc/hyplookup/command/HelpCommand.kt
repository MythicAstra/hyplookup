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

import net.sharedwonder.mc.hyplookup.utils.HypLookupContext
import net.sharedwonder.mc.ptbridge.ConnectionContext
import net.sharedwonder.mc.ptbridge.utils.FormattedText
import net.sharedwonder.mc.ptbridge.utils.TextUtils

object HelpCommand : Command {
    override val expressions: Array<String> = arrayOf("help", "h")

    override val description: String = "Shows the help of HypLookup commands"

    override fun run(connectionContext: ConnectionContext, hypLookupContext: HypLookupContext, args: List<String>): String {
        val table = ArrayList<Array<String>>()
        for (command in CommandParser.COMMANDS) {
            table.add(arrayOf(command.expressions.joinToString("${FormattedText.GRAY}/${FormattedText.AQUA}", FormattedText.AQUA), FormattedText.YELLOW + command.description))
        }
        return "Help:\n" + TextUtils.alignTextTable(table, "${FormattedText.DARK_GRAY} - ").joinToString("\n")
    }
}
