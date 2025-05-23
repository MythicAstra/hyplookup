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

import java.util.Collections
import net.sharedwonder.hyplookup.HypLookupContext
import net.sharedwonder.hyplookup.util.McText

class CommandParser(val input: String, private val hypLookupContext: HypLookupContext) {
    val isMatched: Boolean

    private val commandLine: String

    init {
        when {
            input.startsWith(COMMAND_EXPR_1, ignoreCase = true) &&
                (input.length == COMMAND_EXPR_1.length || input[COMMAND_EXPR_1.length] == ' ') -> {
                isMatched = true
                commandLine = if (input.length > COMMAND_EXPR_1.length + 1) input.drop(COMMAND_EXPR_1.length + 1) else ""
            }
            input.startsWith(COMMAND_EXPR_2, ignoreCase = true) &&
                (input.length == COMMAND_EXPR_2.length || input[COMMAND_EXPR_2.length] == ' ') -> {
                isMatched = true
                commandLine = if (input.length > COMMAND_EXPR_2.length + 1) input.drop(COMMAND_EXPR_2.length + 1) else ""
            }
            input.startsWith(COMMAND_EXPR_3, ignoreCase = true) -> {
                isMatched = true
                commandLine = if (input.length > COMMAND_EXPR_3.length) input.drop(COMMAND_EXPR_3.length) else ""
            }
            else -> {
                isMatched = false
                commandLine = ""
            }
        }
    }

    fun run(): String? {
        check(isMatched) { "Not a HypLookup command" }

        try {
            val args = splitCommandLine(commandLine)
            val command = if (args.isNotEmpty()) keywords[args[0]] else HelpCommand
            return if (command != null) command.run(hypLookupContext, args.drop(1)) else "${McText.RED}Unknown command: ${args[0]}"
        } catch (exception: CommandException) {
            return McText.RED + (exception.message ?: "Unknown error while executing command")
        }
    }

    private fun splitCommandLine(commandLine: String): List<String> {
        if (commandLine.isBlank()) {
            return emptyList()
        }

        val result = ArrayList<String>()
        val builder = StringBuilder()
        var escape = false
        var betweenQuotes = false

        for (char in commandLine) {
            when {
                escape -> {
                    builder.append(char)
                    escape = false
                }

                char == '\\' -> {
                    escape = true
                }

                char == '"' -> {
                    betweenQuotes = !betweenQuotes
                }

                char.isWhitespace() && !betweenQuotes -> {
                    if (builder.isNotEmpty()) {
                        result.add(builder.toString())
                        builder.clear()
                    }
                }

                else -> builder.append(char)
            }
        }

        if (escape) {
            throw CommandException("Invalid command line: end with '\\'")
        }

        if (betweenQuotes) {
            throw CommandException("Invalid command line: missing closing quote")
        }

        if (builder.isNotEmpty()) {
            result.add(builder.toString())
        }

        return result
    }

    companion object {
        private const val COMMAND_EXPR_1 = "/hyplookup"

        private const val COMMAND_EXPR_2 = "/hl"

        private const val COMMAND_EXPR_3 = "/."

        val commands: List<Command> = Collections.synchronizedList(ArrayList())

        private val keywords: MutableMap<String, Command> = HashMap()

        @JvmStatic
        fun registerCommand(command: Command) {
            (commands as MutableList).add(command)
            command.keywords.forEach {
                keywords.compute(it) { key, value ->
                    if (value != null) {
                        throw Error("The keyword '$key' is also used by the command: ${value.javaClass.name}")
                    }
                    command
                }
            }
        }

        init {
            registerCommand(HelpCommand)
            registerCommand(QueryCommand)
            registerCommand(DisplayCommand)
            registerCommand(StopCommand)
        }
    }
}
