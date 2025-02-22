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
import net.sharedwonder.lightproxy.ConnectionContext;
import net.sharedwonder.lightproxy.packet.HandleFlag;

public final class CLRequestLogin extends net.sharedwonder.lightproxy.handler.CLRequestLogin {
    @Override
    public HandleFlag handle(ConnectionContext context, ByteBuf in, ByteBuf transformed) {
        var flag = super.handle(context, in, transformed);
        if (context.getProtocolVersion() != MINECRAFT_1_8_X_PROTOCOL_VERSION) {
            throw new RuntimeException("Protocol version is not 47 (Minecraft 1.8.x), which is not supported");
        }
        return flag;
    }

    private static final int MINECRAFT_1_8_X_PROTOCOL_VERSION = 47;
}
