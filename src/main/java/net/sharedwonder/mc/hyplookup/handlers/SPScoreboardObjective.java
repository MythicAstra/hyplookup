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

import io.netty.buffer.ByteBuf;
import net.sharedwonder.mc.hyplookup.utils.Constants;
import net.sharedwonder.mc.hyplookup.utils.HypLookupContext;
import net.sharedwonder.mc.ptbridge.ConnectionContext;
import net.sharedwonder.mc.ptbridge.packet.HandledFlag;
import net.sharedwonder.mc.ptbridge.packet.PacketUtils;
import net.sharedwonder.mc.ptbridge.packet.S2CPacketHandler;
import net.sharedwonder.mc.ptbridge.utils.TextUtils;
import org.jetbrains.annotations.NotNull;

public final class SPScoreboardObjective implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_SCOREBOARD_OBJECTIVE;
    }

    @Override
    public @NotNull HandledFlag handle(@NotNull ConnectionContext connectionContext, @NotNull ByteBuf in, @NotNull ByteBuf transformed) {
        var hypLookupContext = connectionContext.getExternalContext(HypLookupContext.class);

        var name = PacketUtils.readUtf8String(in);
        var action = in.readByte();

        if (action == 0 || action == 2) {
            var title = TextUtils.toPlaintext(PacketUtils.readUtf8String(in));
            hypLookupContext.scoreboardObjectives.put(name, title);
            if (name.equals(hypLookupContext.scoreboard.getSidebar())) {
                hypLookupContext.detectGame(title);
            }
        } else if (action == 1) {
            hypLookupContext.scoreboardObjectives.remove(name);
        }

        return HandledFlag.PASSED;
    }
}
