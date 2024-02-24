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

import net.sharedwonder.mc.hyplookup.HypLookup
import net.sharedwonder.mc.ptbridge.utils.GSON
import net.sharedwonder.mc.ptbridge.utils.HTTPRequestUtils
import java.io.IOException
import java.util.UUID
import com.google.gson.JsonObject

object HypixelAPI {
    private var baseUrl: String = "https://api.hypixel.net/v2"

    private var key: String? = null

    private var userAgent: String? = null

    @JvmStatic
    fun init() {
        HypLookup.config().hypixelApiBaseUrl?.let { baseUrl = it }
        key = HypLookup.config().hypixelApiKey
        userAgent = HypLookup.config().hypixelApiUserAgent
    }

    @JvmStatic
    @Throws(IOException::class)
    fun queryPlayerData(uuid: UUID): HypixelPlayerData {
        val json = (HTTPRequestUtils.request("$baseUrl/player?uuid=$uuid") {
            if (key != null) {
                setRequestProperty("API-Key", key)
            }
            if (userAgent != null) {
                setRequestProperty("User-Agent", userAgent)
            }
        }.ifErrorResponse {
            throw buildException("Failed to access Hypixel API")
        }.ifInterruptedByException {
            throw exception
        }.response).let {
            GSON.fromJson(it.contentAsUtf8String, JsonObject::class.java)["player"]
        }

        return if (json.isJsonNull) NicknamePlayer else RealPlayerData.build(json.asJsonObject)
    }
}
