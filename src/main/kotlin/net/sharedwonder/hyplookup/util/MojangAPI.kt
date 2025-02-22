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

package net.sharedwonder.hyplookup.util

import java.net.URI
import java.net.http.HttpRequest
import java.util.UUID
import net.sharedwonder.lightproxy.http.HttpUtils
import net.sharedwonder.lightproxy.util.JsonParser
import net.sharedwonder.lightproxy.util.PlayerProfile
import net.sharedwonder.lightproxy.util.UuidUtils

object MojangAPI {
    @JvmStatic
    fun fetchPlayerProfile(name: String): PlayerProfile? {
        val json = HttpUtils.sendRequest(HttpRequest.newBuilder(URI.create("https://api.mojang.com/users/profiles/minecraft/$name")).build())
            .whenFailedByException {
                throw newException("Failed to fetch the player profile: $name")
            }.isErrorResponse {
                return null
            }.asResponse.contentAsUtf8String!!

        val uuid: UUID? = try {
            JsonParser(json).nextObject {
                while (hasNext()) {
                    if (nextName() == "id") {
                        return@nextObject UuidUtils.stringToUuid(nextString())
                    }
                    skipValue()
                }
                null
            }
        } catch (exception: IllegalStateException) {
            throw RuntimeException("Failed to fetch the player profile, invalid response: $json", exception)
        }

        if (uuid == null) {
            throw RuntimeException("Failed to fetch the player profile, missing the player UUID: $json")
        }

        return PlayerProfile(name, uuid)
    }
}
