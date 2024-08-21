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

package net.sharedwonder.hyplookup

import com.google.gson.annotations.SerializedName
import net.sharedwonder.lightproxy.config.Config
import net.sharedwonder.lightproxy.config.ConfigFileType

@Config(name = "hyplookup", type = ConfigFileType.JSON)
class HypLookupConfig {
    @SerializedName("hypixel-api-base-url")
    val hypixelApiBaseUrl: String = "https://api.hypixel.net/v2"

    @SerializedName("hypixel-api-key")
    val hypixelApiKey: String? = null

    @SerializedName("hypixel-api-user-agent")
    val hypixelApiUserAgent: String? = null

    @SerializedName("hypixel-api-throttling-timeout")
    val hypixelApiThrottlingTimeout: Long = 300000L

    @SerializedName("hypixel-joined-game-message")
    val hypixelJoinedGameMessage: String? = null

    @SerializedName("hypixel-rejoined-game-message")
    val hypixelRejoinedGameMessage: String? = null

    @SerializedName("player-data-cache-limit")
    val playerDataCacheLimit: Int = 300
}
