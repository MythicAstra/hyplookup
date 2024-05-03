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
import org.jetbrains.annotations.NotNull;

public final class SPUpdatePlayerList implements S2CPacketHandler {
    @Override
    public int getId() {
        return Constants.PID_SP_UPDATE_PLAYER_LIST;
    }

    @Override
    public @NotNull HandledFlag handle(@NotNull ConnectionContext connectionContext, @NotNull ByteBuf in, @NotNull ByteBuf transformed) {
        var hypLookupContext = connectionContext.getExternalContext(HypLookupContext.class);

        var action = in.readByte();
        var count = PacketUtils.readVarint(in);

        for (var index = 0; index < count; ++index) {
            switch (action) {
                case ADD_PLAYER -> {
                    var playerUuid = PacketUtils.readUuid(in);
                    var playerName = PacketUtils.readUtf8String(in);

                    hypLookupContext.playerUuidToName.put(playerUuid, playerName);
                    hypLookupContext.playerNameToUuid.put(playerName, playerUuid);
                    hypLookupContext.playerListDisplay.addPlayer(playerName, playerUuid);
                }

                case UPDATE_DISPLAY_NAME -> hypLookupContext.playerListDisplay.update();

                case REMOVE_PLAYER -> {
                    var playerUuid = PacketUtils.readUuid(in);
                    var playerName = hypLookupContext.playerUuidToName.remove(playerUuid);
                    if (playerName != null) {
                        hypLookupContext.playerNameToUuid.remove(playerName);
                        hypLookupContext.playerListDisplay.removePlayer(playerName);
                    }
                }
            }
        }

        return HandledFlag.PASSED;
    }

    public static final int ADD_PLAYER = 0;

    public static final int UPDATE_DISPLAY_NAME = 3;

    public static final int REMOVE_PLAYER = 4;
}
