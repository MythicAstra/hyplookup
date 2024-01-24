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

import net.sharedwonder.mc.ptbridge.utils.FormattedText.WHITE
import com.google.gson.JsonObject

class PlayerSkyWarsStats(delegate: Map<String, Any?>) : Map<String, Any?> by delegate {
    val experience: Int get() = get("experience") as Int

    val `level-rounded`: Int get() = get("level-rounded") as Int

    val `level-formatted`: String get() = get("level-formatted") as String

    val `level-formatted-without-brackets`: String get() = get("level-formatted-without-brackets") as String

    val `level-formatted-without-ornament`: String get() = get("level-formatted-without-ornament") as String

    val `level-formatted-simple`: String get() = get("level-formatted-simple") as String

    val coins: Int get() = get("coins") as Int

    val souls: Int get() = get("souls") as Int

    val `solo-normal-mode games-played`: Int get() = get("solo-normal-mode games-played") as Int

    val `solo-normal-mode wins`: Int get() = get("solo-normal-mode wins") as Int

    val `solo-normal-mode losses`: Int get() = get("solo-normal-mode losses") as Int

    val `solo-normal-mode wlr`: Double get() = get("solo-normal-mode wlr") as Double

    val `solo-normal-mode win-rate`: Double get() = get("solo-normal-mode win-rate") as Double

    val `solo-normal-mode kills`: Int get() = get("solo-normal-mode kills") as Int

    val `solo-normal-mode deaths`: Int get() = get("solo-normal-mode deaths") as Int

    val `solo-normal-mode kdr`: Double get() = get("solo-normal-mode kdr") as Double

    val `solo-insane-mode games-played`: Int get() = get("solo-insane-mode games-played") as Int

    val `solo-insane-mode wins`: Int get() = get("solo-insane-mode wins") as Int

    val `solo-insane-mode losses`: Int get() = get("solo-insane-mode losses") as Int

    val `solo-insane-mode wlr`: Double get() = get("solo-insane-mode wlr") as Double

    val `solo-insane-mode win-rate`: Double get() = get("solo-insane-mode win-rate") as Double

    val `solo-insane-mode kills`: Int get() = get("solo-insane-mode kills") as Int

    val `solo-insane-mode deaths`: Int get() = get("solo-insane-mode deaths") as Int

    val `solo-insane-mode kdr`: Double get() = get("solo-insane-mode kdr") as Double

    val `team-normal-mode games-played`: Int get() = get("team-normal-mode games-played") as Int

    val `team-normal-mode wins`: Int get() = get("team-normal-mode wins") as Int

    val `team-normal-mode losses`: Int get() = get("team-normal-mode losses") as Int

    val `team-normal-mode wlr`: Double get() = get("team-normal-mode wlr") as Double

    val `team-normal-mode win-rate`: Double get() = get("team-normal-mode win-rate") as Double

    val `team-normal-mode kills`: Int get() = get("team-normal-mode kills") as Int

    val `team-normal-mode deaths`: Int get() = get("team-normal-mode deaths") as Int

    val `team-normal-mode kdr`: Double get() = get("team-normal-mode kdr") as Double

    val `team-insane-mode games-played`: Int get() = get("team-insane-mode games-played") as Int

    val `team-insane-mode wins`: Int get() = get("team-insane-mode wins") as Int

    val `team-insane-mode losses`: Int get() = get("team-insane-mode losses") as Int

    val `team-insane-mode wlr`: Double get() = get("team-insane-mode wlr") as Double

    val `team-insane-mode win-rate`: Double get() = get("team-insane-mode win-rate") as Double

    val `team-insane-mode kills`: Int get() = get("team-insane-mode kills") as Int

    val `team-insane-mode deaths`: Int get() = get("team-insane-mode deaths") as Int

    val `team-insane-mode kdr`: Double get() = get("team-insane-mode kdr") as Double

    val `core-modes win-streak`: Int? get() = get("core-modes win-streak") as Int?

    val `core-modes games-played`: Int get() = get("core-modes games-played") as Int

    val `core-modes wins`: Int get() = get("core-modes wins") as Int

    val `core-modes losses`: Int get() = get("core-modes losses") as Int

    val `core-modes wlr`: Double get() = get("core-modes wlr") as Double

    val `core-modes win-rate`: Double get() = get("core-modes win-rate") as Double

    val `core-modes kills`: Int get() = get("core-modes kills") as Int

    val `core-modes deaths`: Int get() = get("core-modes deaths") as Int

    val `core-modes kdr`: Double get() = get("core-modes kdr") as Double

    val `lab-modes win-streak`: Int? get() = get("lab-modes win-streak") as Int?

    val `lab-modes games-played`: Int get() = get("lab-modes games-played") as Int

    val `lab-modes wins`: Int get() = get("lab-modes wins") as Int

    val `lab-modes losses`: Int get() = get("lab-modes losses") as Int

    val `lab-modes wlr`: Double get() = get("lab-modes wlr") as Double

    val `lab-modes win-rate`: Double get() = get("lab-modes win-rate") as Double

    val `lab-modes kills`: Int get() = get("lab-modes kills") as Int

    val `lab-modes deaths`: Int get() = get("lab-modes deaths") as Int

    val `lab-modes kdr`: Double get() = get("lab-modes kdr") as Double

    val `all-modes games-played`: Int get() = get("all-modes games-played") as Int

    val `all-modes wins`: Int get() = get("all-modes wins") as Int

    val `all-modes losses`: Int get() = get("all-modes losses") as Int

    val `all-modes wlr`: Double get() = get("all-modes wlr") as Double

    val `all-modes win-rate`: Double get() = get("all-modes win-rate") as Double

    val `all-modes kills`: Int get() = get("all-modes kills") as Int

    val `all-modes deaths`: Int get() = get("all-modes deaths") as Int

    val `all-modes kdr`: Double get() = get("all-modes kdr") as Double

    companion object {
        fun buildFromJson(json: JsonObject?): PlayerSkyWarsStats {
            fun getStringOrNull(key: String) = json?.get(key)?.asString
            fun getIntOrNull(key: String) = json?.get(key)?.asInt
            fun getIntOrZero(key: String) = json?.get(key)?.asInt ?: 0

            val experience = getIntOrZero("skywars_experience")
            val `level-formatted-without-brackets` = getStringOrNull("levelFormatted") ?: "${WHITE}1⋆"
            val `level-formatted` = getStringOrNull("levelFormattedWithBrackets")?.dropLast(1)
                ?: (`level-formatted-without-brackets`.take(2) + '[' + `level-formatted-without-brackets`.drop(2) + ']')
            val `level-formatted-without-ornament` = `level-formatted`.dropLast(2) + `level-formatted`.take(2) + ']'
            val `level-formatted-simple` = `level-formatted-without-brackets`.dropLast(1)
            val `level-rounded` = `level-formatted-simple`.drop(2).toInt()
            val coins = getIntOrZero("coins")
            val souls = getIntOrZero("souls")

            val `solo-normal-mode wins` = getIntOrZero("wins_solo_normal")
            val `solo-normal-mode losses` = getIntOrZero("losses_solo_normal")
            val `solo-normal-mode games-played` = `solo-normal-mode wins` + `solo-normal-mode losses`
            val `solo-normal-mode wlr` = `solo-normal-mode wins`.toDouble() / `solo-normal-mode losses`
            val `solo-normal-mode win-rate` = `solo-normal-mode wins`.toDouble() / `solo-normal-mode games-played`
            val `solo-normal-mode kills` = getIntOrZero("kills_solo_normal")
            val `solo-normal-mode deaths` = getIntOrZero("deaths_solo_normal")
            val `solo-normal-mode kdr` = `solo-normal-mode kills`.toDouble() / `solo-normal-mode deaths`

            val `solo-insane-mode wins` = getIntOrZero("wins_solo_insane")
            val `solo-insane-mode losses` = getIntOrZero("losses_solo_insane")
            val `solo-insane-mode games-played` = `solo-insane-mode wins` + `solo-insane-mode losses`
            val `solo-insane-mode wlr` = `solo-insane-mode wins`.toDouble() / `solo-insane-mode losses`
            val `solo-insane-mode win-rate` = `solo-insane-mode wins`.toDouble() / `solo-insane-mode games-played`
            val `solo-insane-mode kills` = getIntOrZero("kills_solo_insane")
            val `solo-insane-mode deaths` = getIntOrZero("deaths_solo_insane")
            val `solo-insane-mode kdr` = `solo-insane-mode kills`.toDouble() / `solo-insane-mode deaths`

            val `team-normal-mode wins` = getIntOrZero("wins_team_normal")
            val `team-normal-mode losses` = getIntOrZero("losses_team_normal")
            val `team-normal-mode games-played` = `team-normal-mode wins` + `team-normal-mode losses`
            val `team-normal-mode wlr` = `team-normal-mode wins`.toDouble() / `team-normal-mode losses`
            val `team-normal-mode win-rate` = `team-normal-mode wins`.toDouble() / `team-normal-mode games-played`
            val `team-normal-mode kills` = getIntOrZero("kills_team_normal")
            val `team-normal-mode deaths` = getIntOrZero("deaths_team_normal")
            val `team-normal-mode kdr` = `team-normal-mode kills`.toDouble() / `team-normal-mode deaths`

            val `team-insane-mode wins` = getIntOrZero("wins_team_insane")
            val `team-insane-mode losses` = getIntOrZero("losses_team_insane")
            val `team-insane-mode games-played` = `team-insane-mode wins` + `team-insane-mode losses`
            val `team-insane-mode wlr` = `team-insane-mode wins`.toDouble() / `team-insane-mode losses`
            val `team-insane-mode win-rate` = `team-insane-mode wins`.toDouble() / `team-insane-mode games-played`
            val `team-insane-mode kills` = getIntOrZero("kills_team_insane")
            val `team-insane-mode deaths` = getIntOrZero("deaths_team_insane")
            val `team-insane-mode kdr` = `team-insane-mode kills`.toDouble() / `team-insane-mode deaths`

            val `core-modes win-streak` = getIntOrNull("win_streak")
            val `core-modes wins` = getIntOrZero("wins")
            val `core-modes losses` = getIntOrZero("losses")
            val `core-modes games-played` = `core-modes wins` + `core-modes losses`
            val `core-modes wlr` = `core-modes wins`.toDouble() / `core-modes losses`
            val `core-modes win-rate` = `core-modes wins`.toDouble() / `core-modes games-played`
            val `core-modes kills` = getIntOrZero("kills")
            val `core-modes deaths` = getIntOrZero("deaths")
            val `core-modes kdr` = `core-modes kills`.toDouble() / `core-modes deaths`

            val `lab-modes win-streak` = getIntOrNull("win_streak_lab")
            val `lab-modes wins` = getIntOrZero("wins_lab")
            val `lab-modes losses` = getIntOrZero("losses_lab")
            val `lab-modes games-played` = `lab-modes wins` + `lab-modes losses`
            val `lab-modes wlr` = `lab-modes wins`.toDouble() / `lab-modes losses`
            val `lab-modes win-rate` = `lab-modes wins`.toDouble() / `lab-modes games-played`
            val `lab-modes kills` = getIntOrZero("kills_lab")
            val `lab-modes deaths` = getIntOrZero("deaths_lab")
            val `lab-modes kdr` = `lab-modes kills`.toDouble() / `lab-modes deaths`

            val `all-modes games-played` = getIntOrZero("games_played_skywars")
            val `all-modes wins` = `core-modes wins` + `lab-modes wins`
            val `all-modes losses` = `core-modes losses` + `lab-modes losses`
            val `all-modes wlr` = `all-modes wins`.toDouble() / `all-modes losses`
            val `all-modes win-rate` = `all-modes wins`.toDouble() / `all-modes games-played`
            val `all-modes kills` = `core-modes kills` + `lab-modes kills`
            val `all-modes deaths` = `core-modes deaths` + `lab-modes deaths`
            val `all-modes kdr` = `all-modes kills`.toDouble() / `all-modes deaths`

            return PlayerSkyWarsStats(mapOf(
                "experience" to experience,
                "level-rounded" to `level-rounded`,
                "level-formatted" to `level-formatted`,
                "level-formatted-without-brackets" to `level-formatted-without-brackets`,
                "level-formatted-without-ornament" to `level-formatted-without-ornament`,
                "level-formatted-simple" to `level-formatted-simple`,
                "coins" to coins,
                "souls" to souls,

                "solo-normal-mode games-played" to `solo-normal-mode games-played`,
                "solo-normal-mode wins" to `solo-normal-mode wins`,
                "solo-normal-mode losses" to `solo-normal-mode losses`,
                "solo-normal-mode wlr" to `solo-normal-mode wlr`,
                "solo-normal-mode win-rate" to `solo-normal-mode win-rate`,
                "solo-normal-mode kills" to `solo-normal-mode kills`,
                "solo-normal-mode deaths" to `solo-normal-mode deaths`,
                "solo-normal-mode kdr" to `solo-normal-mode kdr`,

                "solo-insane-mode games-played" to `solo-insane-mode games-played`,
                "solo-insane-mode wins" to `solo-insane-mode wins`,
                "solo-insane-mode losses" to `solo-insane-mode losses`,
                "solo-insane-mode wlr" to `solo-insane-mode wlr`,
                "solo-insane-mode win-rate" to `solo-insane-mode win-rate`,
                "solo-insane-mode kills" to `solo-insane-mode kills`,
                "solo-insane-mode deaths" to `solo-insane-mode deaths`,
                "solo-insane-mode kdr" to `solo-insane-mode kdr`,

                "team-normal-mode games-played" to `team-normal-mode games-played`,
                "team-normal-mode wins" to `team-normal-mode wins`,
                "team-normal-mode losses" to `team-normal-mode losses`,
                "team-normal-mode wlr" to `team-normal-mode wlr`,
                "team-normal-mode win-rate" to `team-normal-mode win-rate`,
                "team-normal-mode kills" to `team-normal-mode kills`,
                "team-normal-mode deaths" to `team-normal-mode deaths`,
                "team-normal-mode kdr" to `team-normal-mode kdr`,

                "team-insane-mode games-played" to `team-insane-mode games-played`,
                "team-insane-mode wins" to `team-insane-mode wins`,
                "team-insane-mode losses" to `team-insane-mode losses`,
                "team-insane-mode wlr" to `team-insane-mode wlr`,
                "team-insane-mode win-rate" to `team-insane-mode win-rate`,
                "team-insane-mode kills" to `team-insane-mode kills`,
                "team-insane-mode deaths" to `team-insane-mode deaths`,
                "team-insane-mode kdr" to `team-insane-mode kdr`,

                "core-modes win-streak" to `core-modes win-streak`,
                "core-modes games-played" to `core-modes games-played`,
                "core-modes wins" to `core-modes wins`,
                "core-modes losses" to `core-modes losses`,
                "core-modes wlr" to `core-modes wlr`,
                "core-modes win-rate" to `core-modes win-rate`,
                "core-modes kills" to `core-modes kills`,
                "core-modes deaths" to `core-modes deaths`,
                "core-modes kdr" to `core-modes kdr`,

                "lab-modes win-streak" to `lab-modes win-streak`,
                "lab-modes games-played" to `lab-modes games-played`,
                "lab-modes wins" to `lab-modes wins`,
                "lab-modes losses" to `lab-modes losses`,
                "lab-modes wlr" to `lab-modes wlr`,
                "lab-modes win-rate" to `lab-modes win-rate`,
                "lab-modes kills" to `lab-modes kills`,
                "lab-modes deaths" to `lab-modes deaths`,
                "lab-modes kdr" to `lab-modes kdr`,

                "all-modes games-played" to `all-modes games-played`,
                "all-modes wins" to `all-modes wins`,
                "all-modes losses" to `all-modes losses`,
                "all-modes wlr" to `all-modes wlr`,
                "all-modes win-rate" to `all-modes win-rate`,
                "all-modes kills" to `all-modes kills`,
                "all-modes deaths" to `all-modes deaths`,
                "all-modes kdr" to `all-modes kdr`
            ))
        }
    }
}
