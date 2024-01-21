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

package net.sharedwonder.mc.hyplookup.query

import net.sharedwonder.mc.ptbridge.utils.GSON
import net.sharedwonder.mc.ptbridge.utils.HTTPRequestUtils
import net.sharedwonder.mc.ptbridge.utils.PlayerProfile
import net.sharedwonder.mc.ptbridge.utils.UUIDUtils
import com.google.gson.JsonObject

object MojangAPI {
    fun queryPlayerProfile(name: String): PlayerProfile? {
        val json = GSON.fromJson(HTTPRequestUtils.request("https://api.mojang.com/users/profiles/minecraft/$name", "GET")
            .onErrorResponse {
                return null
            }.onExceptionThrown {
                throw buildException("Failed to query UUID for $name")
            }.response.contentAsString, JsonObject::class.java)
        return PlayerProfile(json["name"].asString, UUIDUtils.stringToUuid(json["id"].asString))
    }
}
