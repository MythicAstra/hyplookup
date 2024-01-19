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

class Scoreboard {
    var playerList: String? = null

    var sidebar: String? = null

    var belowName: String? = null

    operator fun get(id: Byte): String? {
        return when (id.toInt()) {
            0 -> playerList
            1 -> sidebar
            2 -> belowName
            else -> null
        }
    }

    operator fun set(id: Byte, scoreboard: String?) {
        when (id.toInt()) {
            0 -> playerList = scoreboard
            1 -> sidebar = scoreboard
            2 -> belowName = scoreboard
        }
    }
}
