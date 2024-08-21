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

package net.sharedwonder.hyplookup.util

import java.io.IOException
import java.io.StringReader
import java.util.UUID
import com.google.gson.stream.JsonReader
import net.sharedwonder.lightproxy.http.HTTPRequestUtils
import net.sharedwonder.lightproxy.util.PlayerProfile
import net.sharedwonder.lightproxy.util.UUIDUtils

object MojangAPI {
    fun fetchPlayerProfile(name: String): PlayerProfile? {
        val json = HTTPRequestUtils.request("https://api.mojang.com/users/profiles/minecraft/$name")
            .ifErrorResponse {
                return null
            }.ifInterruptedByException {
                throw buildException("Failed to fetch the player profile: $name")
            }.response.contentAsUtf8String

        var uuid: UUID? = null

        try {
            JsonReader(StringReader(json)).use {
                it.beginObject()
                while (it.hasNext()) {
                    when (it.nextName()) {
                        "id" -> uuid = UUIDUtils.stringToUuid(it.nextString())
                        else -> it.skipValue()
                    }
                }
                it.endObject()
            }
        } catch (exception: IOException) {
            // StringReader never throws IOException (before closing)
            throw AssertionError()
        } catch (exception: IllegalStateException) {
            throw RuntimeException("Cannot parse the response: $json", exception)
        }

        return uuid?.let { PlayerProfile(name, it) } ?: throw RuntimeException("Cannot parse the response: $json")
    }
}
