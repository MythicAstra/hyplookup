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

package net.sharedwonder.hyplookup;

import net.sharedwonder.hyplookup.handler.CLRequestLogin;
import net.sharedwonder.hyplookup.handler.CPSendChatMessage;
import net.sharedwonder.hyplookup.handler.SPChangeHeldItem;
import net.sharedwonder.hyplookup.handler.SPDisplayScoreboard;
import net.sharedwonder.hyplookup.handler.SPScoreboardObjective;
import net.sharedwonder.hyplookup.handler.SPUpdateMessage;
import net.sharedwonder.hyplookup.handler.SPUpdatePlayerList;
import net.sharedwonder.hyplookup.handler.SPUpdateTeam;
import net.sharedwonder.lightproxy.addon.AddonInitializer;
import net.sharedwonder.lightproxy.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.sharedwonder.lightproxy.ConnectionContext.registerExternalContextType;
import static net.sharedwonder.lightproxy.packet.PacketHandlers.C2S_LOGIN_PACKET_HANDLERS;
import static net.sharedwonder.lightproxy.packet.PacketHandlers.C2S_PLAY_PACKET_HANDLERS;
import static net.sharedwonder.lightproxy.packet.PacketHandlers.S2C_PLAY_PACKET_HANDLERS;
import static net.sharedwonder.lightproxy.packet.PacketHandlers.registerHandler;

public final class HypLookup implements AddonInitializer {
    @Override
    public void init() {
        registerExternalContextType(HypLookupContext.class, HypLookupContext::new);

        registerHandler(C2S_LOGIN_PACKET_HANDLERS, new CLRequestLogin());

        registerHandler(C2S_PLAY_PACKET_HANDLERS, new CPSendChatMessage());
        registerHandler(S2C_PLAY_PACKET_HANDLERS, new SPUpdateMessage());
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
