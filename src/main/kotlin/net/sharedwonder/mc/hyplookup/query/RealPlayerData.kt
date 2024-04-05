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

class RealPlayerData(
    val firstLogin: Long,
    val lastLogin: Long,
    val lastLogout: Long,
    val userLanguage: String,
    val achievementPoints: Int,
    val stats: PlayerStats
) : HypixelPlayerData() {
    companion object {
        fun build(map: Map<String, Any>): RealPlayerData = RealPlayerData(
            (map["firstLogin"] as Number?)?.toLong() ?: 0,
            (map["lastLogin"] as Number?)?.toLong() ?: 0,
            (map["lastLogout"] as Number?)?.toLong() ?: 0,
            map["userLanguage"] as String? ?: "?",
            (map["achievementPoints"] as Number?)?.toInt() ?: 0,
            @Suppress("UNCHECKED_CAST")
            PlayerStats.build(map["stats"] as Map<String, Any>?)
        )
    }
}
