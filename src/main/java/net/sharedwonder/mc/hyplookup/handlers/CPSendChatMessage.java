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

package net.sharedwonder.mc.hyplookup.handlers;

import java.nio.charset.StandardCharsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.sharedwonder.mc.hyplookup.command.CommandParser;
import net.sharedwonder.mc.hyplookup.utils.Constants;
import net.sharedwonder.mc.ptbridge.ConnectionContext;
import net.sharedwonder.mc.ptbridge.packet.C2SPacketHandler;
import net.sharedwonder.mc.ptbridge.packet.HandledFlag;
import net.sharedwonder.mc.ptbridge.packet.PacketUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class CPSendChatMessage implements C2SPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_CP_SEND_CHAT_MESSAGE;
    }

    @Override
    public @NotNull HandledFlag handle(@NotNull ConnectionContext connectionContext, @NotNull ByteBuf in, @NotNull ByteBuf transformed) {
        var message = PacketUtils.readUtf8String(in);

        if (message.charAt(0) == Constants.COMMAND_PREFIX) {
            var parser = new CommandParser(connectionContext, message);
            if (parser.isMatched()) {
                LOGGER.info("{} issued a HypLookup command: " + connectionContext.getPlayerUsername(), message);
                new Thread(() -> {
                    try {
                        var response = parser.run();
                        if (response == null) {
                            return;
                        }
                        var bytes = response.getBytes(StandardCharsets.UTF_8);

                        var size = PacketUtils.calcVarintSize(bytes.length) + bytes.length + 2;
                        var packet = Unpooled.buffer(size + PacketUtils.VARINT_MAX_SIZE);
                        PacketUtils.writeVarint(packet, size);
                        PacketUtils.writeVarint(packet, Constants.PID_SP_UPDATE_CHAT_MESSAGE);
                        PacketUtils.writeByteArray(packet, bytes);
                        packet.writeByte(Constants.CHAT_MESSAGE_ID);

                        connectionContext.sendToClient(packet);
                    } catch (Throwable exception) {
                        LOGGER.error("An error occurred while processing user command (issued by " + connectionContext.getPlayerUsername() + "): " + message, exception);
                    }
                }, "HYPL-RunCommand-" + count++).start();

                return HandledFlag.BLOCKED;
            }
        }

        return HandledFlag.PASSED;
    }

    private static final Logger LOGGER = LogManager.getLogger(CPSendChatMessage.class);

    private static int count = 0;
}
