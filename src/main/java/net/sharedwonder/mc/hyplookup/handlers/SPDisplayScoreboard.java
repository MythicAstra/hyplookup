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
import net.sharedwonder.mc.hyplookup.Constants;
import net.sharedwonder.mc.hyplookup.HypLookupContext;
import net.sharedwonder.mc.hyplookup.utils.HypixelGame;
import net.sharedwonder.mc.ptbridge.ConnectionContext;
import net.sharedwonder.mc.ptbridge.packet.HandledFlag;
import net.sharedwonder.mc.ptbridge.packet.PacketUtils;
import net.sharedwonder.mc.ptbridge.packet.S2CPacketHandler;

public final class SPDisplayScoreboard implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_DISPLAY_SCOREBOARD;
    }

    @Override
    public HandledFlag handle(ConnectionContext context, ByteBuf in, ByteBuf transformed) {
        var hypLookupContext = context.getExternalContext(HypLookupContext.class);

        var position = in.readByte();
        var objectiveName = PacketUtils.readUtf8String(in);

        if (position == Constants.SIDEBAR_SCOREBOARD) {
            hypLookupContext.sidebarScoreboardName = objectiveName;
            var title = hypLookupContext.scoreboardObjectives.get(objectiveName);
            if (title != null) {
                hypLookupContext.currentGame = HypixelGame.getByScoreboardTitle(title);
                if (hypLookupContext.currentGame == null) {
                    hypLookupContext.stopDisplayingStats();
                }
            }
        }

        return HandledFlag.PASSED;
    }
}
