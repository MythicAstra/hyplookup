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

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import net.sharedwonder.hyplookup.HypLookupContext
import org.apache.logging.log4j.LogManager

class CommandRunner(private val hypLookupContext: HypLookupContext) : Thread("HYPL-CommandRunner") {
    private val queue: BlockingQueue<CommandParser> = LinkedBlockingQueue()

    fun offerCommand(parser: CommandParser) {
        queue.add(parser)
    }

    override fun run() {
        while (!isInterrupted) {
            val parser = try {
                queue.take()
            } catch (exception: InterruptedException) {
                return
            }
            val message = try {
                parser.run()
            } catch (exception: Throwable) {
                logger.error("An error occurred while processing user command (issued by ${hypLookupContext.connectionContext.playerUsername}): " + parser.input, exception)
                continue
            }
            if (message != null) {
                hypLookupContext.printMessageToChat(message)
            }
        }
    }
}

private val logger = LogManager.getLogger(CommandRunner::class.java)
