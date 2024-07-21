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

import java.util.HashSet;
import io.netty.buffer.ByteBuf;
import net.sharedwonder.mc.hyplookup.Constants;
import net.sharedwonder.mc.hyplookup.HypLookupContext;
import net.sharedwonder.mc.ptbridge.ConnectionContext;
import net.sharedwonder.mc.ptbridge.packet.HandledFlag;
import net.sharedwonder.mc.ptbridge.packet.PacketUtils;
import net.sharedwonder.mc.ptbridge.packet.S2CPacketHandler;

public final class SPUpdateTeam implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_UPDATE_TEAM;
    }

    @Override
    public HandledFlag handle(ConnectionContext context, ByteBuf in, ByteBuf transformed) {
        var hypLookupContext = context.getExternalContext(HypLookupContext.class);

        var teamName = PacketUtils.readUtf8String(in);
        var action = in.readByte();

        if (action == Constants.TEAM_CREATE_AND_ADD_PLAYER || action == Constants.TEAM_CREATE) {
            PacketUtils.skipChunk(in);
            hypLookupContext.teamPrefix.put(teamName, PacketUtils.readUtf8String(in));
            hypLookupContext.teamPostfix.put(teamName, PacketUtils.readUtf8String(in));
            in.skipBytes(1);
            PacketUtils.skipChunk(in);
            in.skipBytes(1);

            hypLookupContext.teamToPlayers.computeIfAbsent(teamName, key -> new HashSet<>());
        }

        if (action == Constants.TEAM_REMOVE) {
            hypLookupContext.teamPrefix.remove(teamName);
            hypLookupContext.teamPostfix.remove(teamName);

            for (var playerName : hypLookupContext.teamToPlayers.get(teamName)) {
                hypLookupContext.playerToTeam.remove(playerName);
            }
            hypLookupContext.teamToPlayers.remove(teamName);
        }

        if (action == Constants.TEAM_CREATE_AND_ADD_PLAYER || action == Constants.TEAM_ADD_PLAYER) {
            var count = PacketUtils.readVarint(in);
            for (var counter = 0; counter < count; ++counter) {
                var playerName = PacketUtils.readUtf8String(in);

                hypLookupContext.playerToTeam.put(playerName, teamName);
                hypLookupContext.teamToPlayers.get(teamName).add(playerName);
            }
        }

        if (action == Constants.TEAM_REMOVE_PLAYER) {
            var count = PacketUtils.readVarint(in);
            for (var counter = 0; counter < count; ++counter) {
                var playerName = PacketUtils.readUtf8String(in);

                hypLookupContext.playerToTeam.remove(playerName);
                hypLookupContext.teamToPlayers.get(teamName).remove(playerName);
            }
        }

        hypLookupContext.updateDisplaying();

        return HandledFlag.PASSED;
    }
}
