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

import net.sharedwonder.mc.hyplookup.utils.HYPLOOKUP_MESSAGE_PREFIX
import net.sharedwonder.mc.hyplookup.utils.HypLookupContext
import net.sharedwonder.mc.ptbridge.ConnectionContext
import net.sharedwonder.mc.ptbridge.utils.FormattedText
import net.sharedwonder.mc.ptbridge.utils.TextUtils

class CommandParser(private val connectionContext: ConnectionContext, input: String) {
    val isMatched: Boolean

    private val commandLine: String

    init {
        when {
            input.startsWith(COMMAND_EXPR_1, ignoreCase = true) &&
                (input.length == COMMAND_EXPR_1.length || input[COMMAND_EXPR_1.length] == ' ') -> {
                isMatched = true
                commandLine = if (input.length > COMMAND_EXPR_1.length + 1) input.substring(COMMAND_EXPR_1.length + 1) else ""
            }
            input.startsWith(COMMAND_EXPR_2, ignoreCase = true) &&
                (input.length == COMMAND_EXPR_2.length || input[COMMAND_EXPR_2.length] == ' ') -> {
                isMatched = true
                commandLine = if (input.length > COMMAND_EXPR_2.length + 1) input.substring(COMMAND_EXPR_2.length + 1) else ""
            }
            input.startsWith(COMMAND_EXPR_3, ignoreCase = true) -> {
                isMatched = true
                commandLine = if (input.length > COMMAND_EXPR_3.length) input.substring(COMMAND_EXPR_3.length) else ""
            }
            else -> {
                isMatched = false
                commandLine = ""
            }
        }
    }

    fun run(): String? {
        check(isMatched) { "Not a HypLookup command" }

        return try {
            val args = splitCommandLine(commandLine)
            val command = if (commandLine != "") EXPRESSION_TO_COMMAND[args[0]] else HelpCommand
            if (command != null) {
                val hypLookupContext = connectionContext.getExternalContext(HypLookupContext::class.java)
                val responseText = command.run(connectionContext, hypLookupContext, args.subList(1, args.size)) ?: return null
                TextUtils.serialize(HYPLOOKUP_MESSAGE_PREFIX + responseText)
            } else {
                TextUtils.serialize("$HYPLOOKUP_MESSAGE_PREFIX${FormattedText.RED}Unknown command: ${args[0]}")
            }
        } catch (exception: CommandException) {
            TextUtils.serialize(HYPLOOKUP_MESSAGE_PREFIX + FormattedText.RED + exception.message)
        }
    }

    private fun splitCommandLine(commandLine: String): List<String> {
        if (commandLine.isEmpty()) {
            return emptyList()
        }

        val result = ArrayList<String>()
        val builder = StringBuilder()
        var escape = false
        var inString = false

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
                    inString = !inString
                }

                char.isWhitespace() && !inString -> {
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

        if (inString) {
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

        @JvmField val COMMANDS: MutableList<Command> = ArrayList()

        @JvmField val EXPRESSION_TO_COMMAND: MutableMap<String, Command> = HashMap()

        private fun registerCommand(command: Command) {
            COMMANDS.add(command)
            command.expressions.forEach {
                EXPRESSION_TO_COMMAND.compute(it) { key, value ->
                    if (value != null) {
                        throw RuntimeException("The expression '$key' is also used by the command: ${value.javaClass.name}")
                    } else command
                }
            }
        }

        init {
            registerCommand(HelpCommand)
            registerCommand(QueryCommand)
            registerCommand(DisplayCommand)
            registerCommand(UpdateCommand)
            registerCommand(JvmGCCommand)
        }
    }
}
