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

package net.sharedwonder.mc.hyplookup.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import net.sharedwonder.mc.ptbridge.http.HTTPRequestUtils
import net.sharedwonder.mc.ptbridge.utils.PlayerProfile
import net.sharedwonder.mc.ptbridge.utils.UUIDUtils

object MojangAPI {
    private val GSON = Gson()

    fun fetchPlayerProfile(name: String): PlayerProfile? {
        val json = GSON.fromJson(HTTPRequestUtils.request("https://api.mojang.com/users/profiles/minecraft/$name")
            .ifErrorResponse {
                return null
            }.ifInterruptedByException {
                throw buildException("Failed to fetch the player profile: $name")
            }.response.contentAsUtf8String, JsonObject::class.java)
        return PlayerProfile(json["name"].asString, UUIDUtils.stringToUuid(json["id"].asString))
    }
}
