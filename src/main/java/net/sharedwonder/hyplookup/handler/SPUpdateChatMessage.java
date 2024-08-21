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

package net.sharedwonder.hyplookup.handler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import com.google.gson.stream.JsonReader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.sharedwonder.hyplookup.Constants;
import net.sharedwonder.hyplookup.HypLookup;
import net.sharedwonder.hyplookup.HypLookupContext;
import net.sharedwonder.lightproxy.ConnectionContext;
import net.sharedwonder.lightproxy.packet.HandledFlag;
import net.sharedwonder.lightproxy.packet.PacketUtils;
import net.sharedwonder.lightproxy.packet.S2CPacketHandler;

public final class SPUpdateChatMessage implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_UPDATE_CHAT_MESSAGE;
    }

    @Override
    public HandledFlag handle(ConnectionContext context, ByteBuf in, ByteBuf transformed) {
        var hypLookupContext = context.getExternalContext(HypLookupContext.class);
        if (hypLookupContext.currentGame == null) {
            return HandledFlag.PASSED;
        }

        var size = PacketUtils.readVarint(in);
        try (var reader = new JsonReader(new InputStreamReader(new ByteBufInputStream(in, size), StandardCharsets.UTF_8))) {
            reader.beginObject();
            var flag = false;
            while (reader.hasNext()) {
                if (reader.nextName().equals("extra")) {
                    reader.beginArray();
                    flag = check(reader);
                    break;
                }
                reader.skipValue();
            }

            if (flag) {
                hypLookupContext.startDisplayingStats();
            }
        } catch (Exception ignored) {
        }

        return HandledFlag.PASSED;
    }

    private static boolean check(JsonReader reader) throws IOException {
        var index = 0;
        while (reader.hasNext()) {
            if (index > 4) {
                return false;
            }

            if (index == 1) {
                if (checkMessage(reader, HypLookup.CONFIG.getHypixelRejoinedGameMessage(), "reconnected", "重新连接")) {
                    return true;
                }
            } else if (index == 4) {
                if (checkMessage(reader, HypLookup.CONFIG.getHypixelJoinedGameMessage(), "has joined", "加入了游戏")) {
                    return true;
                }
            } else {
                reader.skipValue();
            }

            ++index;
        }
        return false;
    }

    private static boolean checkMessage(JsonReader reader, @Nullable String userDefined, String en, String chs) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            if (!reader.nextName().equals("text")) {
                reader.skipValue();
                continue;
            }

            var message = reader.nextString();
            if (userDefined != null ? message.contains(userDefined) : message.contains(en) || message.contains(chs)) {
                return true;
            }
        }
        reader.endObject();
        return false;
    }
}
