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
import java.util.HashSet;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

public final class SPUpdateTeam implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_UPDATE_TEAM;
    }

    @Override
    public @NotNull HandledFlag handle(@NotNull ConnectionContext connectionContext, @NotNull ByteBuf in, @NotNull ByteBuf transformed) {
        var hypLookupContext = connectionContext.getExternalContext(HypLookupContext.class);

        var teamName = PacketUtils.readUtf8String(in);
        var action = in.readByte();

        if (action == 0 || action == 2) {
            PacketUtils.skipChunk(in);
            hypLookupContext.teamPrefix.put(teamName, PacketUtils.readUtf8String(in));
            hypLookupContext.teamPostfix.put(teamName, PacketUtils.readUtf8String(in));
            in.skipBytes(1);
            PacketUtils.skipChunk(in);
            in.skipBytes(1);

            hypLookupContext.teamToPlayers.computeIfAbsent(teamName, key -> new HashSet<>());
        }

        if (action == 2) {
            hypLookupContext.playerListDisplay.update();
        }

        if (action == 0 || action == 3) {
            var count = PacketUtils.readVarint(in);
            for (var counter = 0; counter < count; ++counter) {
                var playerName = PacketUtils.readUtf8String(in);

                hypLookupContext.playerToTeam.put(playerName, teamName);
                hypLookupContext.teamToPlayers.get(teamName).add(playerName);
            }

            hypLookupContext.playerListDisplay.update();
        }

        if (action == 4) {
            var count = PacketUtils.readVarint(in);
            for (var counter = 0; counter < count; ++counter) {
                var playerName = PacketUtils.readUtf8String(in);

                hypLookupContext.playerToTeam.remove(playerName);
                hypLookupContext.teamToPlayers.get(teamName).remove(playerName);
            }

            hypLookupContext.playerListDisplay.update();
        }

        if (action == 1) {
            hypLookupContext.teamPrefix.remove(teamName);
            hypLookupContext.teamPostfix.remove(teamName);

            for (var playerName : hypLookupContext.teamToPlayers.get(teamName)) {
                hypLookupContext.playerToTeam.remove(playerName);
            }
            hypLookupContext.teamToPlayers.remove(teamName);

            hypLookupContext.playerListDisplay.update();
        }

        return HandledFlag.PASSED;
    }
}
