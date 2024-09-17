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

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import io.netty.buffer.ByteBuf;
import net.sharedwonder.hyplookup.Constants;
import net.sharedwonder.hyplookup.HypLookup;
import net.sharedwonder.hyplookup.HypLookupContext;
import net.sharedwonder.lightproxy.ConnectionContext;
import net.sharedwonder.lightproxy.packet.HandledFlag;
import net.sharedwonder.lightproxy.packet.PacketUtils;
import net.sharedwonder.lightproxy.packet.S2CPacketHandler;
import net.sharedwonder.lightproxy.util.JsonUtils;

public final class SPUpdateMessage implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_UPDATE_MESSAGE;
    }

    @Override
    public HandledFlag handle(ConnectionContext context, ByteBuf in, ByteBuf transformed) {
        var hypLookupContext = context.getExternalContext(HypLookupContext.class);
        if (hypLookupContext.currentGame == null) {
            return HandledFlag.PASSED;
        }

        var message = PacketUtils.readUtf8String(in);
        var type = in.readByte();

        if (type != Constants.PROMPT_MESSAGE_TYPE) {
            try {
                var map = JsonUtils.fromJson(message, Map.class);
                checkExtra((List<?>) map.get("extra"), hypLookupContext);
            } catch (RuntimeException exception) {
                return HandledFlag.PASSED;
            }
        }
        return HandledFlag.PASSED;
    }

    private static void checkExtra(@Nullable List<?> extra, HypLookupContext hypLookupContext) {
        if (extra == null) {
            return;
        }

        if (extra.size() >= 2 && messageContains(extra.get(1), HypLookup.CONFIG.getHypixelRejoinedGameMessage(), "reconnected", "重新连接")) {
            hypLookupContext.startDisplayingStats();
        }
        if (extra.size() >= 5 && messageContains(extra.get(4), HypLookup.CONFIG.getHypixelJoinedGameMessage(), "has joined", "加入了游戏")) {
            hypLookupContext.startDisplayingStats();
        }
        if (extra.size() >= 6 && messageContains(extra.get(5), HypLookup.CONFIG.getHypixelJoinedGameMessage(), "has joined", "加入了游戏")) {
            hypLookupContext.startDisplayingStats();
        }
    }

    private static boolean messageContains(Object message, @Nullable String userDefined, String... fallbacks) {
        if (message instanceof Map<?, ?> map) {
            var text = (String) map.get("text");
            if (userDefined != null) {
                return text.contains(userDefined);
            }
            for (var string : fallbacks) {
                if (text.contains(string)) {
                    return true;
                }
            }
        }
        return false;
    }
}
