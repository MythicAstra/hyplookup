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

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import io.netty.buffer.Unpooled
import net.sharedwonder.hyplookup.CHAT_MESSAGE_ID
import net.sharedwonder.hyplookup.PID_SP_UPDATE_CHAT_MESSAGE
import net.sharedwonder.lightproxy.ConnectionContext
import net.sharedwonder.lightproxy.packet.PacketUtils
import org.apache.logging.log4j.LogManager

class CommandRunner(private val connectionContext: ConnectionContext) : Thread("HYPL-CommandRunner") {
    val commandQueue: BlockingQueue<CommandParser> = LinkedBlockingQueue()

    override fun run() {
        while (!isInterrupted) {
            val parser = try {
                commandQueue.take()
            } catch (exception: InterruptedException) {
                return
            }
            try {
                val response = parser.run() ?: return
                val bytes = response.toByteArray()

                if (isInterrupted) {
                    return
                }

                val size = PacketUtils.calcVarintSize(bytes.size) + bytes.size + 2
                val packet = Unpooled.buffer(size + PacketUtils.VARINT_MAX_SIZE)
                PacketUtils.writeVarint(packet, size)
                PacketUtils.writeVarint(packet, PID_SP_UPDATE_CHAT_MESSAGE)
                PacketUtils.writeByteArray(packet, bytes)
                packet.writeByte(CHAT_MESSAGE_ID)

                if (isInterrupted) {
                    return
                }

                connectionContext.sendToClient(packet)
            } catch (exception: Throwable) {
                logger.error("An error occurred while processing user command (issued by ${connectionContext.playerUsername}): " + parser.input, exception)
            }
        }
    }
}

private val logger = LogManager.getLogger(CommandRunner::class.java)
