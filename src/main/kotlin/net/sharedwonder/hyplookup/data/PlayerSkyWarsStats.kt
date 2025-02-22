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

package net.sharedwonder.hyplookup.data

import net.sharedwonder.hyplookup.util.MCText

class PlayerSkyWarsStats(map: Map<String, *>?) {
    val experience: Int

    val `formatted-level`: String

    val `formatted-level-without-brackets`: String

    val coins: Int

    val souls: Int

    val `solo-normal-mode games-played`: Int

    val `solo-normal-mode wins`: Int

    val `solo-normal-mode losses`: Int

    val `solo-normal-mode wlr`: Double

    val `solo-normal-mode win-rate`: Double

    val `solo-normal-mode kills`: Int

    val `solo-normal-mode deaths`: Int

    val `solo-normal-mode kdr`: Double

    val `solo-insane-mode games-played`: Int

    val `solo-insane-mode wins`: Int

    val `solo-insane-mode losses`: Int

    val `solo-insane-mode wlr`: Double

    val `solo-insane-mode win-rate`: Double

    val `solo-insane-mode kills`: Int

    val `solo-insane-mode deaths`: Int

    val `solo-insane-mode kdr`: Double

    val `team-normal-mode games-played`: Int

    val `team-normal-mode wins`: Int

    val `team-normal-mode losses`: Int

    val `team-normal-mode wlr`: Double

    val `team-normal-mode win-rate`: Double

    val `team-normal-mode kills`: Int

    val `team-normal-mode deaths`: Int

    val `team-normal-mode kdr`: Double

    val `team-insane-mode games-played`: Int

    val `team-insane-mode wins`: Int

    val `team-insane-mode losses`: Int

    val `team-insane-mode wlr`: Double

    val `team-insane-mode win-rate`: Double

    val `team-insane-mode kills`: Int

    val `team-insane-mode deaths`: Int

    val `team-insane-mode kdr`: Double

    val `core-modes win-streak`: Int?

    val `core-modes games-played`: Int

    val `core-modes wins`: Int

    val `core-modes losses`: Int

    val `core-modes wlr`: Double

    val `core-modes win-rate`: Double

    val `core-modes kills`: Int

    val `core-modes deaths`: Int

    val `core-modes kdr`: Double

    val `lab-modes win-streak`: Int?

    val `lab-modes games-played`: Int

    val `lab-modes wins`: Int

    val `lab-modes losses`: Int

    val `lab-modes wlr`: Double

    val `lab-modes win-rate`: Double

    val `lab-modes kills`: Int

    val `lab-modes deaths`: Int

    val `lab-modes kdr`: Double

    val `all-modes games-played`: Int

    val `all-modes wins`: Int

    val `all-modes losses`: Int

    val `all-modes wlr`: Double

    val `all-modes win-rate`: Double

    val `all-modes kills`: Int

    val `all-modes deaths`: Int

    val `all-modes kdr`: Double

    init {
        fun getIntOrNull(key: String) = (map?.get(key) as Number?)?.toInt()
        fun getIntOrZero(key: String) = (map?.get(key) as Number?)?.toInt() ?: 0
        fun getStringOrNull(key: String) = map?.get(key) as String?

        experience = getIntOrZero("skywars_experience")
        `formatted-level-without-brackets` = getStringOrNull("levelFormatted") ?: "${MCText.GRAY}1⋆"
        `formatted-level` = (getStringOrNull("levelFormattedWithBrackets")?.substringBefore(']')
            ?: (`formatted-level-without-brackets`.take(2) + '[' + `formatted-level-without-brackets`.drop(2))) + ']'
        coins = getIntOrZero("coins")
        souls = getIntOrZero("souls")

        `solo-normal-mode wins` = getIntOrZero("wins_solo_normal")
        `solo-normal-mode losses` = getIntOrZero("losses_solo_normal")
        `solo-normal-mode games-played` = `solo-normal-mode wins` + `solo-normal-mode losses`
        `solo-normal-mode wlr` = `solo-normal-mode wins`.toDouble() / `solo-normal-mode losses`
        `solo-normal-mode win-rate` = `solo-normal-mode wins`.toDouble() / `solo-normal-mode games-played`
        `solo-normal-mode kills` = getIntOrZero("kills_solo_normal")
        `solo-normal-mode deaths` = getIntOrZero("deaths_solo_normal")
        `solo-normal-mode kdr` = `solo-normal-mode kills`.toDouble() / `solo-normal-mode deaths`

        `solo-insane-mode wins` = getIntOrZero("wins_solo_insane")
        `solo-insane-mode losses` = getIntOrZero("losses_solo_insane")
        `solo-insane-mode games-played` = `solo-insane-mode wins` + `solo-insane-mode losses`
        `solo-insane-mode wlr` = `solo-insane-mode wins`.toDouble() / `solo-insane-mode losses`
        `solo-insane-mode win-rate` = `solo-insane-mode wins`.toDouble() / `solo-insane-mode games-played`
        `solo-insane-mode kills` = getIntOrZero("kills_solo_insane")
        `solo-insane-mode deaths` = getIntOrZero("deaths_solo_insane")
        `solo-insane-mode kdr` = `solo-insane-mode kills`.toDouble() / `solo-insane-mode deaths`

        `team-normal-mode wins` = getIntOrZero("wins_team_normal")
        `team-normal-mode losses` = getIntOrZero("losses_team_normal")
        `team-normal-mode games-played` = `team-normal-mode wins` + `team-normal-mode losses`
        `team-normal-mode wlr` = `team-normal-mode wins`.toDouble() / `team-normal-mode losses`
        `team-normal-mode win-rate` = `team-normal-mode wins`.toDouble() / `team-normal-mode games-played`
        `team-normal-mode kills` = getIntOrZero("kills_team_normal")
        `team-normal-mode deaths` = getIntOrZero("deaths_team_normal")
        `team-normal-mode kdr` = `team-normal-mode kills`.toDouble() / `team-normal-mode deaths`

        `team-insane-mode wins` = getIntOrZero("wins_team_insane")
        `team-insane-mode losses` = getIntOrZero("losses_team_insane")
        `team-insane-mode games-played` = `team-insane-mode wins` + `team-insane-mode losses`
        `team-insane-mode wlr` = `team-insane-mode wins`.toDouble() / `team-insane-mode losses`
        `team-insane-mode win-rate` = `team-insane-mode wins`.toDouble() / `team-insane-mode games-played`
        `team-insane-mode kills` = getIntOrZero("kills_team_insane")
        `team-insane-mode deaths` = getIntOrZero("deaths_team_insane")
        `team-insane-mode kdr` = `team-insane-mode kills`.toDouble() / `team-insane-mode deaths`

        `core-modes win-streak` = getIntOrNull("win_streak")
        `core-modes wins` = getIntOrZero("wins")
        `core-modes losses` = getIntOrZero("losses")
        `core-modes games-played` = `core-modes wins` + `core-modes losses`
        `core-modes wlr` = `core-modes wins`.toDouble() / `core-modes losses`
        `core-modes win-rate` = `core-modes wins`.toDouble() / `core-modes games-played`
        `core-modes kills` = getIntOrZero("kills")
        `core-modes deaths` = getIntOrZero("deaths")
        `core-modes kdr` = `core-modes kills`.toDouble() / `core-modes deaths`

        `lab-modes win-streak` = getIntOrNull("win_streak_lab")
        `lab-modes wins` = getIntOrZero("wins_lab")
        `lab-modes losses` = getIntOrZero("losses_lab")
        `lab-modes games-played` = `lab-modes wins` + `lab-modes losses`
        `lab-modes wlr` = `lab-modes wins`.toDouble() / `lab-modes losses`
        `lab-modes win-rate` = `lab-modes wins`.toDouble() / `lab-modes games-played`
        `lab-modes kills` = getIntOrZero("kills_lab")
        `lab-modes deaths` = getIntOrZero("deaths_lab")
        `lab-modes kdr` = `lab-modes kills`.toDouble() / `lab-modes deaths`

        `all-modes games-played` = getIntOrZero("games_played_skywars")
        `all-modes wins` = `core-modes wins` + `lab-modes wins`
        `all-modes losses` = `core-modes losses` + `lab-modes losses`
        `all-modes wlr` = `all-modes wins`.toDouble() / `all-modes losses`
        `all-modes win-rate` = `all-modes wins`.toDouble() / `all-modes games-played`
        `all-modes kills` = `core-modes kills` + `lab-modes kills`
        `all-modes deaths` = `core-modes deaths` + `lab-modes deaths`
        `all-modes kdr` = `all-modes kills`.toDouble() / `all-modes deaths`
    }
}
