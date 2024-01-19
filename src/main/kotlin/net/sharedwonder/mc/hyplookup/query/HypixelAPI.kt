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
import java.io.IOException
import java.util.UUID
import com.google.gson.JsonObject

object HypixelAPI {
    private const val HYPIXEL_API_BASE_URL = "https://api.hypixel.net"

    private var isInitialized = false

    private lateinit var apiKey: String

    @JvmStatic
    fun init(apiKey: String) {
        check(!isInitialized) { "Already initialized" }
        isInitialized = true
        this.apiKey = apiKey
    }

    @JvmStatic
    @Throws(IOException::class)
    fun queryPlayerData(uuid: UUID): HypixelPlayerData {
        check(isInitialized) { "Not initialized" }

        val json = (HTTPRequestUtils.request("$HYPIXEL_API_BASE_URL/v2/player?uuid=$uuid") {
            connectTimeout = 5000
            readTimeout = 5000
            setRequestProperty("API-Key", apiKey)
        }.onErrorResponse {
            throw buildException("Failed to access Hypixel API")
        }.onExceptionThrown {
            throw exception
        }.response).let { GSON.fromJson(it.contentAsString, JsonObject::class.java).get("player") }

        return if (json.isJsonNull) NicknamePlayer else GSON.fromJson(json, RealPlayerData::class.java)
    }
}
