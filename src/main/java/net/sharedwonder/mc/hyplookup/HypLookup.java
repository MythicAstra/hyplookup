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

package net.sharedwonder.mc.hyplookup;

import net.sharedwonder.mc.hyplookup.handlers.CLRequestLogin;
import net.sharedwonder.mc.hyplookup.handlers.CPSendChatMessage;
import net.sharedwonder.mc.hyplookup.handlers.SPChangeHeldItem;
import net.sharedwonder.mc.hyplookup.handlers.SPDisplayScoreboard;
import net.sharedwonder.mc.hyplookup.handlers.SPScoreboardObjective;
import net.sharedwonder.mc.hyplookup.handlers.SPUpdateChatMessage;
import net.sharedwonder.mc.hyplookup.handlers.SPUpdatePlayerList;
import net.sharedwonder.mc.hyplookup.handlers.SPUpdateTeam;
import net.sharedwonder.mc.ptbridge.addon.AddonInitializer;
import net.sharedwonder.mc.ptbridge.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.sharedwonder.mc.ptbridge.ConnectionContext.registerExternalContextType;
import static net.sharedwonder.mc.ptbridge.packet.PacketHandlers.C2S_LOGIN_PACKET_HANDLERS;
import static net.sharedwonder.mc.ptbridge.packet.PacketHandlers.C2S_PLAY_PACKET_HANDLERS;
import static net.sharedwonder.mc.ptbridge.packet.PacketHandlers.S2C_PLAY_PACKET_HANDLERS;
import static net.sharedwonder.mc.ptbridge.packet.PacketHandlers.registerHandler;

public final class HypLookup implements AddonInitializer {
    @Override
    public void init() {
        registerExternalContextType(HypLookupContext.class, HypLookupContext::new);

        registerHandler(C2S_LOGIN_PACKET_HANDLERS, new CLRequestLogin());
        registerHandler(C2S_PLAY_PACKET_HANDLERS, new CPSendChatMessage());
        registerHandler(S2C_PLAY_PACKET_HANDLERS, new SPUpdateChatMessage());
        registerHandler(S2C_PLAY_PACKET_HANDLERS, new SPChangeHeldItem());
        registerHandler(S2C_PLAY_PACKET_HANDLERS, new SPUpdatePlayerList());
        registerHandler(S2C_PLAY_PACKET_HANDLERS, new SPScoreboardObjective());
        registerHandler(S2C_PLAY_PACKET_HANDLERS, new SPDisplayScoreboard());
        registerHandler(S2C_PLAY_PACKET_HANDLERS, new SPUpdateTeam());

        LOGGER.info("HypLookup initialized");
    }

    public static final HypLookupConfig CONFIG = ConfigManager.getConfig(HypLookupConfig.class);

    private static final Logger LOGGER = LogManager.getLogger(HypLookup.class);
}
