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

import java.io.Serial
import java.net.URI
import java.net.http.HttpRequest
import java.util.UUID
import net.sharedwonder.hyplookup.HypLookup
import net.sharedwonder.hyplookup.data.Player
import net.sharedwonder.hyplookup.data.PlayerData
import net.sharedwonder.lightproxy.http.HttpUtils
import net.sharedwonder.lightproxy.util.JsonUtils
import net.sharedwonder.lightproxy.util.UuidUtils

object HypixelAPI {
    private val baseUrl = HypLookup.CONFIG.hypixelApiBaseUrl

    private val key = HypLookup.CONFIG.hypixelApiKey

    private val userAgent = HypLookup.CONFIG.hypixelApiUserAgent

    @JvmStatic
    fun fetchPlayerData(uuid: UUID): Player {
        val requestBuilder = HttpRequest.newBuilder(URI.create("$baseUrl/player?uuid=${UuidUtils.uuidToString(uuid)}"))
        if (key != null) {
            requestBuilder.header("API-Key", key)
        }
        if (userAgent != null) {
            requestBuilder.header("User-Agent", userAgent)
        }

        val result = HttpUtils.request(requestBuilder.build())
            .whenInterrupted {
                throw InterruptedException()
            }.whenFailedByException {
                throw buildException("Failed to access Hypixel API")
            }.isErrorResponse {
                if (status == 429 && JsonUtils.fromJson(contentAsUtf8String, Map::class.java)["throttle"] == true) {
                    throw ThrottlingException()
                }
                throw buildException("Failed to access Hypixel API")
            }.asResponse.contentAsUtf8String.let {
                @Suppress("UNCHECKED_CAST")
                JsonUtils.fromJson(it, Map::class.java)["player"] as Map<String, *>?
            }

        return if (result != null) PlayerData.build(result) else Player.None
    }

    class ThrottlingException : RuntimeException() {
        private companion object {
            @Serial private const val serialVersionUID = -5299607313553938526L
        }
    }
}
