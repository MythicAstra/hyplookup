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

package net.sharedwonder.hyplookup.handler;

import io.netty.buffer.ByteBuf;
import net.sharedwonder.hyplookup.Constants;
import net.sharedwonder.hyplookup.HypLookupContext;
import net.sharedwonder.hyplookup.util.McText;
import net.sharedwonder.lightproxy.ConnectionContext;
import net.sharedwonder.lightproxy.packet.HandleFlag;
import net.sharedwonder.lightproxy.packet.PacketUtils;
import net.sharedwonder.lightproxy.packet.S2CPacketHandler;

public final class SPScoreboardObjective implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_SCOREBOARD_OBJECTIVE;
    }

    @Override
    public HandleFlag handle(ConnectionContext context, ByteBuf in, ByteBuf transformed) {
        var hypLookupContext = context.getExternalContext(HypLookupContext.class);

        var name = PacketUtils.readUtf8String(in);
        var action = in.readByte();

        if (action == Constants.SCOREBOARD_ADD || action == Constants.SCOREBOARD_UPDATE) {
            var title = McText.toPlaintext(PacketUtils.readUtf8String(in));
            hypLookupContext.scoreboardObjectives.put(name, title);
            if (name.equals(hypLookupContext.sidebarScoreboardName)) {
                hypLookupContext.whenScoreboardTitleUpdates(title);
            }
        } else if (action == Constants.SCOREBOARD_REMOVE) {
            hypLookupContext.scoreboardObjectives.remove(name);
        }

        return HandleFlag.PASSED;
    }
}
