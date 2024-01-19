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

import net.sharedwonder.mc.hyplookup.utils.Constants;
import net.sharedwonder.mc.hyplookup.utils.HypLookupContext;
import net.sharedwonder.mc.ptbridge.ConnectionContext;
import net.sharedwonder.mc.ptbridge.packet.HandledFlag;
import net.sharedwonder.mc.ptbridge.packet.PacketUtils;
import net.sharedwonder.mc.ptbridge.packet.S2CPacketHandler;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

public final class SPDisplayScoreboard implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_DISPLAY_SCOREBOARD;
    }

    @Override
    public @NotNull HandledFlag handle(@NotNull ConnectionContext connectionContext, @NotNull ByteBuf in, @NotNull ByteBuf transformed) {
        var hypLookupContext = connectionContext.getExternalContext(HypLookupContext.class);

        var position = in.readByte();
        var objectiveName = PacketUtils.readUtf8String(in);

        if (objectiveName.isEmpty()) {
            hypLookupContext.scoreboard.set(position, null);
        } else {
            hypLookupContext.scoreboard.set(position, objectiveName);
        }

        if (position == 1) {
            var title = hypLookupContext.scoreboardObjectives.get(objectiveName);
            if (title != null) {
                hypLookupContext.detectGame(title);
            }
        }

        return HandledFlag.PASSED;
    }
}
