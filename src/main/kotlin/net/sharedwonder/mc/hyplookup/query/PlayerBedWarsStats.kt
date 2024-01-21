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

import net.sharedwonder.mc.ptbridge.utils.FormattedText.AQUA
import net.sharedwonder.mc.ptbridge.utils.FormattedText.BLACK
import net.sharedwonder.mc.ptbridge.utils.FormattedText.BLUE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_AQUA
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_BLUE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_GRAY
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_GREEN
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_PURPLE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_RED
import net.sharedwonder.mc.ptbridge.utils.FormattedText.GOLD
import net.sharedwonder.mc.ptbridge.utils.FormattedText.GRAY
import net.sharedwonder.mc.ptbridge.utils.FormattedText.GREEN
import net.sharedwonder.mc.ptbridge.utils.FormattedText.LIGHT_PURPLE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.RED
import net.sharedwonder.mc.ptbridge.utils.FormattedText.WHITE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.YELLOW
import kotlin.math.floor
import com.google.gson.JsonObject

class PlayerBedWarsStats(delegate: Map<String, Any?>) : Map<String, Any?> by delegate {
    val experience: Int get() = get("experience") as Int

    val level: Double get() = get("level") as Double

    val `level-rounded`: Int get() = get("level-rounded") as Int

    val `level-formatted`: String get() = get("level-formatted") as String

    val `level-formatted-without-brackets`: String get() = get("level-formatted-without-brackets") as String

    val `level-formatted-without-ornament`: String get() = get("level-formatted-without-ornament") as String

    val `level-formatted-simple`: String get() = get("level-formatted-simple") as String

    val coins: Int get() = get("coins") as Int

    val `solo-mode win-streak`: Int? get() = get("solo-mode win-streak") as Int?

    val `solo-mode games-played`: Int get() = get("solo-mode games-played") as Int

    val `solo-mode wins`: Int get() = get("solo-mode wins") as Int

    val `solo-mode losses`: Int get() = get("solo-mode losses") as Int

    val `solo-mode wlr`: Double get() = get("solo-mode wlr") as Double

    val `solo-mode win-rate`: Double get() = get("solo-mode win-rate") as Double

    val `solo-mode normal-kills`: Int get() = get("solo-mode normal-kills") as Int

    val `solo-mode normal-deaths`: Int get() = get("solo-mode normal-deaths") as Int

    val `solo-mode normal-kdr`: Double get() = get("solo-mode normal-kdr") as Double

    val `solo-mode final-kills`: Int get() = get("solo-mode final-kills") as Int

    val `solo-mode final-deaths`: Int get() = get("solo-mode final-deaths") as Int

    val `solo-mode final-kdr`: Double get() = get("solo-mode final-kdr") as Double

    val `solo-mode total-kills`: Int get() = get("solo-mode total-kills") as Int

    val `solo-mode total-deaths`: Int get() = get("solo-mode total-deaths") as Int

    val `solo-mode total-kdr`: Double get() = get("solo-mode total-kdr") as Double

    val `solo-mode beds-broken`: Int get() = get("solo-mode beds-broken") as Int

    val `solo-mode beds-lost`: Int get() = get("solo-mode beds-lost") as Int

    val `solo-mode bblr`: Double get() = get("solo-mode bblr") as Double

    val `doubles-mode win-streak`: Int? get() = get("doubles-mode win-streak") as Int?

    val `doubles-mode games-played`: Int get() = get("doubles-mode games-played") as Int

    val `doubles-mode wins`: Int get() = get("doubles-mode wins") as Int

    val `doubles-mode losses`: Int get() = get("doubles-mode losses") as Int

    val `doubles-mode wlr`: Double get() = get("doubles-mode wlr") as Double

    val `doubles-mode win-rate`: Double get() = get("doubles-mode win-rate") as Double

    val `doubles-mode normal-kills`: Int get() = get("doubles-mode normal-kills") as Int

    val `doubles-mode normal-deaths`: Int get() = get("doubles-mode normal-deaths") as Int

    val `doubles-mode normal-kdr`: Double get() = get("doubles-mode normal-kdr") as Double

    val `doubles-mode final-kills`: Int get() = get("doubles-mode final-kills") as Int

    val `doubles-mode final-deaths`: Int get() = get("doubles-mode final-deaths") as Int

    val `doubles-mode final-kdr`: Double get() = get("doubles-mode final-kdr") as Double

    val `doubles-mode total-kills`: Int get() = get("doubles-mode total-kills") as Int

    val `doubles-mode total-deaths`: Int get() = get("doubles-mode total-deaths") as Int

    val `doubles-mode total-kdr`: Double get() = get("doubles-mode total-kdr") as Double

    val `doubles-mode beds-broken`: Int get() = get("doubles-mode beds-broken") as Int

    val `doubles-mode beds-lost`: Int get() = get("doubles-mode beds-lost") as Int

    val `doubles-mode bblr`: Double get() = get("doubles-mode bblr") as Double

    val `3v3v3v3-mode win-streak`: Int? get() = get("3v3v3v3-mode win-streak") as Int?

    val `3v3v3v3-mode games-played`: Int get() = get("3v3v3v3-mode games-played") as Int

    val `3v3v3v3-mode wins`: Int get() = get("3v3v3v3-mode wins") as Int

    val `3v3v3v3-mode losses`: Int get() = get("3v3v3v3-mode losses") as Int

    val `3v3v3v3-mode wlr`: Double get() = get("3v3v3v3-mode wlr") as Double

    val `3v3v3v3-mode win-rate`: Double get() = get("3v3v3v3-mode win-rate") as Double

    val `3v3v3v3-mode normal-kills`: Int get() = get("3v3v3v3-mode normal-kills") as Int

    val `3v3v3v3-mode normal-deaths`: Int get() = get("3v3v3v3-mode normal-deaths") as Int

    val `3v3v3v3-mode normal-kdr`: Double get() = get("3v3v3v3-mode normal-kdr") as Double

    val `3v3v3v3-mode final-kills`: Int get() = get("3v3v3v3-mode final-kills") as Int

    val `3v3v3v3-mode final-deaths`: Int get() = get("3v3v3v3-mode final-deaths") as Int

    val `3v3v3v3-mode final-kdr`: Double get() = get("3v3v3v3-mode final-kdr") as Double

    val `3v3v3v3-mode total-kills`: Int get() = get("3v3v3v3-mode total-kills") as Int

    val `3v3v3v3-mode total-deaths`: Int get() = get("3v3v3v3-mode total-deaths") as Int

    val `3v3v3v3-mode total-kdr`: Double get() = get("3v3v3v3-mode total-kdr") as Double

    val `3v3v3v3-mode beds-broken`: Int get() = get("3v3v3v3-mode beds-broken") as Int

    val `3v3v3v3-mode beds-lost`: Int get() = get("3v3v3v3-mode beds-lost") as Int

    val `3v3v3v3-mode bblr`: Double get() = get("3v3v3v3-mode bblr") as Double

    val `4v4v4v4-mode win-streak`: Int? get() = get("4v4v4v4-mode win-streak") as Int?

    val `4v4v4v4-mode games-played`: Int get() = get("4v4v4v4-mode games-played") as Int

    val `4v4v4v4-mode wins`: Int get() = get("4v4v4v4-mode wins") as Int

    val `4v4v4v4-mode losses`: Int get() = get("4v4v4v4-mode losses") as Int

    val `4v4v4v4-mode wlr`: Double get() = get("4v4v4v4-mode wlr") as Double

    val `4v4v4v4-mode win-rate`: Double get() = get("4v4v4v4-mode win-rate") as Double

    val `4v4v4v4-mode normal-kills`: Int get() = get("4v4v4v4-mode normal-kills") as Int

    val `4v4v4v4-mode normal-deaths`: Int get() = get("4v4v4v4-mode normal-deaths") as Int

    val `4v4v4v4-mode normal-kdr`: Double get() = get("4v4v4v4-mode normal-kdr") as Double

    val `4v4v4v4-mode final-kills`: Int get() = get("4v4v4v4-mode final-kills") as Int

    val `4v4v4v4-mode final-deaths`: Int get() = get("4v4v4v4-mode final-deaths") as Int

    val `4v4v4v4-mode final-kdr`: Double get() = get("4v4v4v4-mode final-kdr") as Double

    val `4v4v4v4-mode total-kills`: Int get() = get("4v4v4v4-mode total-kills") as Int

    val `4v4v4v4-mode total-deaths`: Int get() = get("4v4v4v4-mode total-deaths") as Int

    val `4v4v4v4-mode total-kdr`: Double get() = get("4v4v4v4-mode total-kdr") as Double

    val `4v4v4v4-mode beds-broken`: Int get() = get("4v4v4v4-mode beds-broken") as Int

    val `4v4v4v4-mode beds-lost`: Int get() = get("4v4v4v4-mode beds-lost") as Int

    val `4v4v4v4-mode bblr`: Double get() = get("4v4v4v4-mode bblr") as Double

    val `4v4-mode win-streak`: Int? get() = get("4v4-mode win-streak") as Int?

    val `4v4-mode games-played`: Int get() = get("4v4-mode games-played") as Int

    val `4v4-mode wins`: Int get() = get("4v4-mode wins") as Int

    val `4v4-mode losses`: Int get() = get("4v4-mode losses") as Int

    val `4v4-mode wlr`: Double get() = get("4v4-mode wlr") as Double

    val `4v4-mode win-rate`: Double get() = get("4v4-mode win-rate") as Double

    val `4v4-mode normal-kills`: Int get() = get("4v4-mode normal-kills") as Int

    val `4v4-mode normal-deaths`: Int get() = get("4v4-mode normal-deaths") as Int

    val `4v4-mode normal-kdr`: Double get() = get("4v4-mode normal-kdr") as Double

    val `4v4-mode final-kills`: Int get() = get("4v4-mode final-kills") as Int

    val `4v4-mode final-deaths`: Int get() = get("4v4-mode final-deaths") as Int

    val `4v4-mode final-kdr`: Double get() = get("4v4-mode final-kdr") as Double

    val `4v4-mode total-kills`: Int get() = get("4v4-mode total-kills") as Int

    val `4v4-mode total-deaths`: Int get() = get("4v4-mode total-deaths") as Int

    val `4v4-mode total-kdr`: Double get() = get("4v4-mode total-kdr") as Double

    val `4v4-mode beds-broken`: Int get() = get("4v4-mode beds-broken") as Int

    val `4v4-mode beds-lost`: Int get() = get("4v4-mode beds-lost") as Int

    val `4v4-mode bblr`: Double get() = get("4v4-mode bblr") as Double

    val `core-modes win-streak`: Int? get() = get("core-modes win-streak") as Int?

    val `core-modes games-played`: Int get() = get("core-modes games-played") as Int

    val `core-modes wins`: Int get() = get("core-modes wins") as Int

    val `core-modes losses`: Int get() = get("core-modes losses") as Int

    val `core-modes wlr`: Double get() = get("core-modes wlr") as Double

    val `core-modes win-rate`: Double get() = get("core-modes win-rate") as Double

    val `core-modes normal-kills`: Int get() = get("core-modes normal-kills") as Int

    val `core-modes normal-deaths`: Int get() = get("core-modes normal-deaths") as Int

    val `core-modes normal-kdr`: Double get() = get("core-modes normal-kdr") as Double

    val `core-modes final-kills`: Int get() = get("core-modes final-kills") as Int

    val `core-modes final-deaths`: Int get() = get("core-modes final-deaths") as Int

    val `core-modes final-kdr`: Double get() = get("core-modes final-kdr") as Double

    val `core-modes total-kills`: Int get() = get("core-modes total-kills") as Int

    val `core-modes total-deaths`: Int get() = get("core-modes total-deaths") as Int

    val `core-modes total-kdr`: Double get() = get("core-modes total-kdr") as Double

    val `core-modes beds-broken`: Int get() = get("core-modes beds-broken") as Int

    val `core-modes beds-lost`: Int get() = get("core-modes beds-lost") as Int

    val `core-modes bblr`: Double get() = get("core-modes bblr") as Double

    val `normal-modes games-played`: Int get() = get("normal-modes games-played") as Int

    val `normal-modes wins`: Int get() = get("normal-modes wins") as Int

    val `normal-modes losses`: Int get() = get("normal-modes losses") as Int

    val `normal-modes wlr`: Double get() = get("normal-modes wlr") as Double

    val `normal-modes win-rate`: Double get() = get("normal-modes win-rate") as Double

    val `normal-modes normal-kills`: Int get() = get("normal-modes normal-kills") as Int

    val `normal-modes normal-deaths`: Int get() = get("normal-modes normal-deaths") as Int

    val `normal-modes normal-kdr`: Double get() = get("normal-modes normal-kdr") as Double

    val `normal-modes final-kills`: Int get() = get("normal-modes final-kills") as Int

    val `normal-modes final-deaths`: Int get() = get("normal-modes final-deaths") as Int

    val `normal-modes final-kdr`: Double get() = get("normal-modes final-kdr") as Double

    val `normal-modes total-kills`: Int get() = get("normal-modes total-kills") as Int

    val `normal-modes total-deaths`: Int get() = get("normal-modes total-deaths") as Int

    val `normal-modes total-kdr`: Double get() = get("normal-modes total-kdr") as Double

    val `normal-modes beds-broken`: Int get() = get("normal-modes beds-broken") as Int

    val `normal-modes beds-lost`: Int get() = get("normal-modes beds-lost") as Int

    val `normal-modes bblr`: Double get() = get("normal-modes bblr") as Double

    val `all-modes games-played`: Int get() = get("all-modes games-played") as Int

    companion object {
        private const val LEVEL_ORNAMENT_1 = "✫"

        private const val LEVEL_ORNAMENT_2 = "✪"

        private const val LEVEL_ORNAMENT_3 = "⚝"

        private const val LEVEL_ORNAMENT_4 = "✥"

        fun buildFromJson(json: JsonObject?): PlayerBedWarsStats {
            val getIntOrNull = if (json != null) { key: String -> json[key]?.asInt } else { _ -> null }
            val getIntOrZero = if (json != null) { key: String -> json[key]?.asInt ?: 0 } else { _ -> 0 }

            val experience = getIntOrZero("Experience")
            val level = calculateLevel(experience)
            val `level-rounded` = calculateLevel(experience).toInt()
            val `level-formatted` = formatLevel(`level-rounded`, includeBrackets = true, includeOrnament = true)
            val `level-formatted-without-brackets` = formatLevel(`level-rounded`, includeBrackets = false, includeOrnament = true)
            val `level-formatted-without-ornament` = formatLevel(`level-rounded`, includeBrackets = true, includeOrnament = false)
            val `level-formatted-simple` = formatLevel(`level-rounded`, includeBrackets = false, includeOrnament = false)
            val coins = getIntOrZero("coins")

            val `solo-mode win-streak` = getIntOrNull("eight_one_winstreak")
            val `solo-mode games-played` = getIntOrZero("eight_one_games_played_bedwars")
            val `solo-mode wins` = getIntOrZero("eight_one_wins_bedwars")
            val `solo-mode losses` = getIntOrZero("eight_one_losses_bedwars")
            val `solo-mode wlr` = `solo-mode wins`.toDouble() / `solo-mode losses`
            val `solo-mode win-rate` = `solo-mode wins`.toDouble() / `solo-mode games-played`
            val `solo-mode normal-kills` = getIntOrZero("eight_one_kills_bedwars")
            val `solo-mode normal-deaths` = getIntOrZero("eight_one_deaths_bedwars")
            val `solo-mode normal-kdr` = `solo-mode normal-kills`.toDouble() / `solo-mode normal-deaths`
            val `solo-mode final-kills` = getIntOrZero("eight_one_final_kills_bedwars")
            val `solo-mode final-deaths` = getIntOrZero("eight_one_final_deaths_bedwars")
            val `solo-mode final-kdr` = `solo-mode final-kills`.toDouble() / `solo-mode final-deaths`
            val `solo-mode total-kills` = `solo-mode normal-kills` + `solo-mode final-kills`
            val `solo-mode total-deaths` = `solo-mode normal-deaths` + `solo-mode final-deaths`
            val `solo-mode total-kdr` = `solo-mode total-kills`.toDouble() / `solo-mode total-deaths`
            val `solo-mode beds-broken` = getIntOrZero("eight_one_beds_broken_bedwars")
            val `solo-mode beds-lost` = getIntOrZero("eight_one_beds_lost_bedwars")
            val `solo-mode bblr` = `solo-mode beds-broken`.toDouble() / `solo-mode beds-lost`

            val `doubles-mode win-streak` = getIntOrNull("eight_two_winstreak")
            val `doubles-mode games-played` = getIntOrZero("eight_two_games_played_bedwars")
            val `doubles-mode wins` = getIntOrZero("eight_two_wins_bedwars")
            val `doubles-mode losses` = getIntOrZero("eight_two_losses_bedwars")
            val `doubles-mode wlr` = `doubles-mode wins`.toDouble() / `doubles-mode losses`
            val `doubles-mode win-rate` = `doubles-mode wins`.toDouble() / `doubles-mode games-played`
            val `doubles-mode normal-kills` = getIntOrZero("eight_two_kills_bedwars")
            val `doubles-mode normal-deaths` = getIntOrZero("eight_two_deaths_bedwars")
            val `doubles-mode normal-kdr` = `doubles-mode normal-kills`.toDouble() / `doubles-mode normal-deaths`
            val `doubles-mode final-kills` = getIntOrZero("eight_two_final_kills_bedwars")
            val `doubles-mode final-deaths` = getIntOrZero("eight_two_final_deaths_bedwars")
            val `doubles-mode final-kdr` = `doubles-mode final-kills`.toDouble() / `doubles-mode final-deaths`
            val `doubles-mode total-kills` = `doubles-mode normal-kills` + `doubles-mode final-kills`
            val `doubles-mode total-deaths` = `doubles-mode normal-deaths` + `doubles-mode final-deaths`
            val `doubles-mode total-kdr` = `doubles-mode total-kills`.toDouble() / `doubles-mode total-deaths`
            val `doubles-mode beds-broken` = getIntOrZero("eight_two_beds_broken_bedwars")
            val `doubles-mode beds-lost` = getIntOrZero("eight_two_beds_lost_bedwars")
            val `doubles-mode bblr` = `doubles-mode beds-broken`.toDouble() / `doubles-mode beds-lost`

            val `3v3v3v3-mode win-streak` = getIntOrNull("four_three_winstreak")
            val `3v3v3v3-mode games-played` = getIntOrZero("four_three_games_played_bedwars")
            val `3v3v3v3-mode wins` = getIntOrZero("four_three_wins_bedwars")
            val `3v3v3v3-mode losses` = getIntOrZero("four_three_losses_bedwars")
            val `3v3v3v3-mode wlr` = `3v3v3v3-mode wins`.toDouble() / `3v3v3v3-mode losses`
            val `3v3v3v3-mode win-rate` = `3v3v3v3-mode wins`.toDouble() / `3v3v3v3-mode games-played`
            val `3v3v3v3-mode normal-kills` = getIntOrZero("four_three_kills_bedwars")
            val `3v3v3v3-mode normal-deaths` = getIntOrZero("four_three_deaths_bedwars")
            val `3v3v3v3-mode normal-kdr` = `3v3v3v3-mode normal-kills`.toDouble() / `3v3v3v3-mode normal-deaths`
            val `3v3v3v3-mode final-kills` = getIntOrZero("four_three_final_kills_bedwars")
            val `3v3v3v3-mode final-deaths` = getIntOrZero("four_three_final_deaths_bedwars")
            val `3v3v3v3-mode final-kdr` = `3v3v3v3-mode final-kills`.toDouble() / `3v3v3v3-mode final-deaths`
            val `3v3v3v3-mode total-kills` = `3v3v3v3-mode normal-kills` + `3v3v3v3-mode final-kills`
            val `3v3v3v3-mode total-deaths` = `3v3v3v3-mode normal-deaths` + `3v3v3v3-mode final-deaths`
            val `3v3v3v3-mode total-kdr` = `3v3v3v3-mode total-kills`.toDouble() / `3v3v3v3-mode total-deaths`
            val `3v3v3v3-mode beds-broken` = getIntOrZero("four_three_beds_broken_bedwars")
            val `3v3v3v3-mode beds-lost` = getIntOrZero("four_three_beds_lost_bedwars")
            val `3v3v3v3-mode bblr` = `3v3v3v3-mode beds-broken`.toDouble() / `3v3v3v3-mode beds-lost`

            val `4v4v4v4-mode win-streak` = getIntOrNull("four_four_winstreak")
            val `4v4v4v4-mode games-played` = getIntOrZero("four_four_games_played_bedwars")
            val `4v4v4v4-mode wins` = getIntOrZero("four_four_wins_bedwars")
            val `4v4v4v4-mode losses` = getIntOrZero("four_four_losses_bedwars")
            val `4v4v4v4-mode wlr` = `4v4v4v4-mode wins`.toDouble() / `4v4v4v4-mode losses`
            val `4v4v4v4-mode win-rate` = `4v4v4v4-mode wins`.toDouble() / `4v4v4v4-mode games-played`
            val `4v4v4v4-mode normal-kills` = getIntOrZero("four_four_kills_bedwars")
            val `4v4v4v4-mode normal-deaths` = getIntOrZero("four_four_deaths_bedwars")
            val `4v4v4v4-mode normal-kdr` = `4v4v4v4-mode normal-kills`.toDouble() / `4v4v4v4-mode normal-deaths`
            val `4v4v4v4-mode final-kills` = getIntOrZero("four_four_final_kills_bedwars")
            val `4v4v4v4-mode final-deaths` = getIntOrZero("four_four_final_deaths_bedwars")
            val `4v4v4v4-mode final-kdr` = `4v4v4v4-mode final-kills`.toDouble() / `4v4v4v4-mode final-deaths`
            val `4v4v4v4-mode total-kills` = `4v4v4v4-mode normal-kills` + `4v4v4v4-mode final-kills`
            val `4v4v4v4-mode total-deaths` = `4v4v4v4-mode normal-deaths` + `4v4v4v4-mode final-deaths`
            val `4v4v4v4-mode total-kdr` = `4v4v4v4-mode total-kills`.toDouble() / `4v4v4v4-mode total-deaths`
            val `4v4v4v4-mode beds-broken` = getIntOrZero("four_four_beds_broken_bedwars")
            val `4v4v4v4-mode beds-lost` = getIntOrZero("four_four_beds_lost_bedwars")
            val `4v4v4v4-mode bblr` = `4v4v4v4-mode beds-broken`.toDouble() / `4v4v4v4-mode beds-lost`

            val `4v4-mode win-streak` = getIntOrNull("two_four_winstreak")
            val `4v4-mode games-played` = getIntOrZero("two_four_games_played_bedwars")
            val `4v4-mode wins` = getIntOrZero("two_four_wins_bedwars")
            val `4v4-mode losses` = getIntOrZero("two_four_losses_bedwars")
            val `4v4-mode wlr` = `4v4-mode wins`.toDouble() / `4v4-mode losses`
            val `4v4-mode win-rate` = `4v4-mode wins`.toDouble() / `4v4-mode games-played`
            val `4v4-mode normal-kills` = getIntOrZero("two_four_kills_bedwars")
            val `4v4-mode normal-deaths` = getIntOrZero("two_four_deaths_bedwars")
            val `4v4-mode normal-kdr` = `4v4-mode normal-kills`.toDouble() / `4v4-mode normal-deaths`
            val `4v4-mode final-kills` = getIntOrZero("two_four_final_kills_bedwars")
            val `4v4-mode final-deaths` = getIntOrZero("two_four_final_deaths_bedwars")
            val `4v4-mode final-kdr` = `4v4-mode final-kills`.toDouble() / `4v4-mode final-deaths`
            val `4v4-mode total-kills` = `4v4-mode normal-kills` + `4v4-mode final-kills`
            val `4v4-mode total-deaths` = `4v4-mode normal-deaths` + `4v4-mode final-deaths`
            val `4v4-mode total-kdr` = `4v4-mode total-kills`.toDouble() / `4v4-mode total-deaths`
            val `4v4-mode beds-broken` = getIntOrZero("two_four_beds_broken_bedwars")
            val `4v4-mode beds-lost` = getIntOrZero("two_four_beds_lost_bedwars")
            val `4v4-mode bblr` = `4v4-mode beds-broken`.toDouble() / `4v4-mode beds-lost`

            val `core-modes win-streak` = getIntOrNull("winstreak")
            val `core-modes games-played` = `solo-mode games-played` + `doubles-mode games-played` + `3v3v3v3-mode games-played` + `4v4v4v4-mode games-played`
            val `core-modes wins` = `solo-mode wins` + `doubles-mode wins` + `3v3v3v3-mode wins` + `4v4v4v4-mode wins`
            val `core-modes losses` = `solo-mode losses` + `doubles-mode losses` + `3v3v3v3-mode losses` + `4v4v4v4-mode losses`
            val `core-modes wlr` = `core-modes wins`.toDouble() / `core-modes losses`
            val `core-modes win-rate` = `core-modes wins`.toDouble() / `core-modes games-played`
            val `core-modes normal-kills` = `solo-mode normal-kills` + `doubles-mode normal-kills` + `3v3v3v3-mode normal-kills` + `4v4v4v4-mode normal-kills`
            val `core-modes normal-deaths` = `solo-mode normal-deaths` + `doubles-mode normal-deaths` + `3v3v3v3-mode normal-deaths` + `4v4v4v4-mode normal-deaths`
            val `core-modes normal-kdr` = `core-modes normal-kills`.toDouble() / `core-modes normal-deaths`
            val `core-modes final-kills` = `solo-mode final-kills` + `doubles-mode final-kills` + `3v3v3v3-mode final-kills` + `4v4v4v4-mode final-kills`
            val `core-modes final-deaths` = `solo-mode final-deaths` + `doubles-mode final-deaths` + `3v3v3v3-mode final-deaths` + `4v4v4v4-mode final-deaths`
            val `core-modes final-kdr` = `core-modes final-kills`.toDouble() / `core-modes final-deaths`
            val `core-modes total-kills` = `core-modes normal-kills` + `core-modes final-kills`
            val `core-modes total-deaths` = `core-modes normal-deaths` + `core-modes final-deaths`
            val `core-modes total-kdr` = `core-modes total-kills`.toDouble() / `core-modes total-deaths`
            val `core-modes beds-broken` = `solo-mode beds-broken` + `doubles-mode beds-broken` + `3v3v3v3-mode beds-broken` + `4v4v4v4-mode beds-broken`
            val `core-modes beds-lost` = `solo-mode beds-lost` + `doubles-mode beds-lost` + `3v3v3v3-mode beds-lost` + `4v4v4v4-mode beds-lost`
            val `core-modes bblr` = `core-modes beds-broken`.toDouble() / `core-modes beds-lost`

            val `normal-modes games-played` = getIntOrZero("games_played_bedwars")
            val `normal-modes wins` = getIntOrZero("wins_bedwars")
            val `normal-modes losses` = getIntOrZero("losses_bedwars")
            val `normal-modes wlr` = `normal-modes wins`.toDouble() / `normal-modes losses`
            val `normal-modes win-rate` = `normal-modes wins`.toDouble() / `normal-modes games-played`
            val `normal-modes normal-kills` = getIntOrZero("kills_bedwars")
            val `normal-modes normal-deaths` = getIntOrZero("deaths_bedwars")
            val `normal-modes normal-kdr` = `normal-modes normal-kills`.toDouble() / `normal-modes normal-deaths`
            val `normal-modes final-kills` = getIntOrZero("final_kills_bedwars")
            val `normal-modes final-deaths` = getIntOrZero("final_deaths_bedwars")
            val `normal-modes final-kdr` = `normal-modes final-kills`.toDouble() / `normal-modes final-deaths`
            val `normal-modes total-kills` = `normal-modes normal-kills` + `normal-modes final-kills`
            val `normal-modes total-deaths` = `normal-modes normal-deaths` + `normal-modes final-deaths`
            val `normal-modes total-kdr` = `normal-modes total-kills`.toDouble() / `normal-modes total-deaths`
            val `normal-modes beds-broken` = getIntOrZero("beds_broken_bedwars")
            val `normal-modes beds-lost` = getIntOrZero("beds_lost_bedwars")
            val `normal-modes bblr` = `normal-modes beds-broken`.toDouble() / `normal-modes beds-lost`

            val `all-modes games-played` = getIntOrZero("games_played_bedwars_1")

            return PlayerBedWarsStats(mapOf(
                "experience" to experience,
                "level" to level,
                "level-formatted" to `level-formatted`,
                "level-formatted-without-brackets" to `level-formatted-without-brackets`,
                "level-formatted-without-ornament" to `level-formatted-without-ornament`,
                "level-formatted-simple" to `level-formatted-simple`,
                "coins" to coins,

                "solo-mode win-streak" to `solo-mode win-streak`,
                "solo-mode games-played" to `solo-mode games-played`,
                "solo-mode wins" to `solo-mode wins`,
                "solo-mode losses" to `solo-mode losses`,
                "solo-mode wlr" to `solo-mode wlr`,
                "solo-mode win-rate" to `solo-mode win-rate`,
                "solo-mode normal-kills" to `solo-mode normal-kills`,
                "solo-mode normal-deaths" to `solo-mode normal-deaths`,
                "solo-mode normal-kdr" to `solo-mode normal-kdr`,
                "solo-mode final-kills" to `solo-mode final-kills`,
                "solo-mode final-deaths" to `solo-mode final-deaths`,
                "solo-mode final-kdr" to `solo-mode final-kdr`,
                "solo-mode total-kills" to `solo-mode total-kills`,
                "solo-mode total-deaths" to `solo-mode total-deaths`,
                "solo-mode total-kdr" to `solo-mode total-kdr`,
                "solo-mode beds-broken" to `solo-mode beds-broken`,
                "solo-mode beds-lost" to `solo-mode beds-lost`,
                "solo-mode bblr" to `solo-mode bblr`,

                "doubles-mode win-streak" to `doubles-mode win-streak`,
                "doubles-mode games-played" to `doubles-mode games-played`,
                "doubles-mode wins" to `doubles-mode wins`,
                "doubles-mode losses" to `doubles-mode losses`,
                "doubles-mode wlr" to `doubles-mode wlr`,
                "doubles-mode win-rate" to `doubles-mode win-rate`,
                "doubles-mode normal-kills" to `doubles-mode normal-kills`,
                "doubles-mode normal-deaths" to `doubles-mode normal-deaths`,
                "doubles-mode normal-kdr" to `doubles-mode normal-kdr`,
                "doubles-mode final-kills" to `doubles-mode final-kills`,
                "doubles-mode final-deaths" to `doubles-mode final-deaths`,
                "doubles-mode final-kdr" to `doubles-mode final-kdr`,
                "doubles-mode total-kills" to `doubles-mode total-kills`,
                "doubles-mode total-deaths" to `doubles-mode total-deaths`,
                "doubles-mode total-kdr" to `doubles-mode total-kdr`,
                "doubles-mode beds-broken" to `doubles-mode beds-broken`,
                "doubles-mode beds-lost" to `doubles-mode beds-lost`,
                "doubles-mode bblr" to `doubles-mode bblr`,

                "3v3v3v3-mode win-streak" to `3v3v3v3-mode win-streak`,
                "3v3v3v3-mode games-played" to `3v3v3v3-mode games-played`,
                "3v3v3v3-mode wins" to `3v3v3v3-mode wins`,
                "3v3v3v3-mode losses" to `3v3v3v3-mode losses`,
                "3v3v3v3-mode wlr" to `3v3v3v3-mode wlr`,
                "3v3v3v3-mode win-rate" to `3v3v3v3-mode win-rate`,
                "3v3v3v3-mode normal-kills" to `3v3v3v3-mode normal-kills`,
                "3v3v3v3-mode normal-deaths" to `3v3v3v3-mode normal-deaths`,
                "3v3v3v3-mode normal-kdr" to `3v3v3v3-mode normal-kdr`,
                "3v3v3v3-mode final-kills" to `3v3v3v3-mode final-kills`,
                "3v3v3v3-mode final-deaths" to `3v3v3v3-mode final-deaths`,
                "3v3v3v3-mode final-kdr" to `3v3v3v3-mode final-kdr`,
                "3v3v3v3-mode total-kills" to `3v3v3v3-mode total-kills`,
                "3v3v3v3-mode total-deaths" to `3v3v3v3-mode total-deaths`,
                "3v3v3v3-mode total-kdr" to `3v3v3v3-mode total-kdr`,
                "3v3v3v3-mode beds-broken" to `3v3v3v3-mode beds-broken`,
                "3v3v3v3-mode beds-lost" to `3v3v3v3-mode beds-lost`,
                "3v3v3v3-mode bblr" to `3v3v3v3-mode bblr`,

                "4v4v4v4-mode win-streak" to `4v4v4v4-mode win-streak`,
                "4v4v4v4-mode games-played" to `4v4v4v4-mode games-played`,
                "4v4v4v4-mode wins" to `4v4v4v4-mode wins`,
                "4v4v4v4-mode losses" to `4v4v4v4-mode losses`,
                "4v4v4v4-mode wlr" to `4v4v4v4-mode wlr`,
                "4v4v4v4-mode win-rate" to `4v4v4v4-mode win-rate`,
                "4v4v4v4-mode normal-kills" to `4v4v4v4-mode normal-kills`,
                "4v4v4v4-mode normal-deaths" to `4v4v4v4-mode normal-deaths`,
                "4v4v4v4-mode normal-kdr" to `4v4v4v4-mode normal-kdr`,
                "4v4v4v4-mode final-kills" to `4v4v4v4-mode final-kills`,
                "4v4v4v4-mode final-deaths" to `4v4v4v4-mode final-deaths`,
                "4v4v4v4-mode final-kdr" to `4v4v4v4-mode final-kdr`,
                "4v4v4v4-mode total-kills" to `4v4v4v4-mode total-kills`,
                "4v4v4v4-mode total-deaths" to `4v4v4v4-mode total-deaths`,
                "4v4v4v4-mode total-kdr" to `4v4v4v4-mode total-kdr`,
                "4v4v4v4-mode beds-broken" to `4v4v4v4-mode beds-broken`,
                "4v4v4v4-mode beds-lost" to `4v4v4v4-mode beds-lost`,
                "4v4v4v4-mode bblr" to `4v4v4v4-mode bblr`,

                "4v4-mode win-streak" to `4v4-mode win-streak`,
                "4v4-mode games-played" to `4v4-mode games-played`,
                "4v4-mode wins" to `4v4-mode wins`,
                "4v4-mode losses" to `4v4-mode losses`,
                "4v4-mode wlr" to `4v4-mode wlr`,
                "4v4-mode win-rate" to `4v4-mode win-rate`,
                "4v4-mode normal-kills" to `4v4-mode normal-kills`,
                "4v4-mode normal-deaths" to `4v4-mode normal-deaths`,
                "4v4-mode normal-kdr" to `4v4-mode normal-kdr`,
                "4v4-mode final-kills" to `4v4-mode final-kills`,
                "4v4-mode final-deaths" to `4v4-mode final-deaths`,
                "4v4-mode final-kdr" to `4v4-mode final-kdr`,
                "4v4-mode total-kills" to `4v4-mode total-kills`,
                "4v4-mode total-deaths" to `4v4-mode total-deaths`,
                "4v4-mode total-kdr" to `4v4-mode total-kdr`,
                "4v4-mode beds-broken" to `4v4-mode beds-broken`,
                "4v4-mode beds-lost" to `4v4-mode beds-lost`,
                "4v4-mode bblr" to `4v4-mode bblr`,

                "core-modes win-streak" to `core-modes win-streak`,
                "core-modes games-played" to `core-modes games-played`,
                "core-modes wins" to `core-modes wins`,
                "core-modes losses" to `core-modes losses`,
                "core-modes wlr" to `core-modes wlr`,
                "core-modes win-rate" to `core-modes win-rate`,
                "core-modes normal-kills" to `core-modes normal-kills`,
                "core-modes normal-deaths" to `core-modes normal-deaths`,
                "core-modes normal-kdr" to `core-modes normal-kdr`,
                "core-modes final-kills" to `core-modes final-kills`,
                "core-modes final-deaths" to `core-modes final-deaths`,
                "core-modes final-kdr" to `core-modes final-kdr`,
                "core-modes total-kills" to `core-modes total-kills`,
                "core-modes total-deaths" to `core-modes total-deaths`,
                "core-modes total-kdr" to `core-modes total-kdr`,
                "core-modes beds-broken" to `core-modes beds-broken`,
                "core-modes beds-lost" to `core-modes beds-lost`,
                "core-modes bblr" to `core-modes bblr`,

                "normal-modes games-played" to `normal-modes games-played`,
                "normal-modes wins" to `normal-modes wins`,
                "normal-modes losses" to `normal-modes losses`,
                "normal-modes wlr" to `normal-modes wlr`,
                "normal-modes win-rate" to `normal-modes win-rate`,
                "normal-modes normal-kills" to `normal-modes normal-kills`,
                "normal-modes normal-deaths" to `normal-modes normal-deaths`,
                "normal-modes normal-kdr" to `normal-modes normal-kdr`,
                "normal-modes final-kills" to `normal-modes final-kills`,
                "normal-modes final-deaths" to `normal-modes final-deaths`,
                "normal-modes final-kdr" to `normal-modes final-kdr`,
                "normal-modes total-kills" to `normal-modes total-kills`,
                "normal-modes total-deaths" to `normal-modes total-deaths`,
                "normal-modes total-kdr" to `normal-modes total-kdr`,
                "normal-modes beds-broken" to `normal-modes beds-broken`,
                "normal-modes beds-lost" to `normal-modes beds-lost`,
                "normal-modes bblr" to `normal-modes bblr`,

                "all-modes games-played" to `all-modes games-played`
            ))
        }

        private fun calculateLevel(experience: Int): Double {
            val x = 100 * floor(experience.toDouble() / 487000)
            val y = experience.toDouble() % 487000
            return when {
                y < 500 -> x + y / 500
                y < 1500 -> x + 1 + (y - 500) / 1000
                y < 3500 -> x + 2 + (y - 1500) / 2000
                y < 7000 -> x + 3 + (y - 3500) / 3500
                else -> x + 4 + (y - 7000) / 5000
            }
        }

        private fun formatLevel(level: Int, includeBrackets: Boolean, includeOrnament: Boolean): String {
            return when {
                level <= 99 -> format0To999Level(level, includeBrackets, includeOrnament, GRAY)
                level <= 199 -> format0To999Level(level, includeBrackets, includeOrnament, WHITE)
                level <= 299 -> format0To999Level(level, includeBrackets, includeOrnament, GOLD)
                level <= 399 -> format0To999Level(level, includeBrackets, includeOrnament, AQUA)
                level <= 499 -> format0To999Level(level, includeBrackets, includeOrnament, DARK_GREEN)
                level <= 599 -> format0To999Level(level, includeBrackets, includeOrnament, DARK_AQUA)
                level <= 699 -> format0To999Level(level, includeBrackets, includeOrnament, DARK_RED)
                level <= 799 -> format0To999Level(level, includeBrackets, includeOrnament, LIGHT_PURPLE)
                level <= 899 -> format0To999Level(level, includeBrackets, includeOrnament, BLUE)
                level <= 999 -> format0To999Level(level, includeBrackets, includeOrnament, DARK_PURPLE)

                level <= 1099 -> formatThousandLevel(level, includeBrackets, includeOrnament, LEVEL_ORNAMENT_1, RED, GOLD, YELLOW, GREEN, AQUA, LIGHT_PURPLE, DARK_PURPLE)
                level <= 1199 -> format1100To1999Level(level, includeBrackets, includeOrnament, WHITE, GRAY)
                level <= 1299 -> format1100To1999Level(level, includeBrackets, includeOrnament, YELLOW, GOLD)
                level <= 1399 -> format1100To1999Level(level, includeBrackets, includeOrnament, AQUA, DARK_AQUA)
                level <= 1499 -> format1100To1999Level(level, includeBrackets, includeOrnament, GREEN, DARK_GREEN)
                level <= 1599 -> format1100To1999Level(level, includeBrackets, includeOrnament, DARK_AQUA, BLUE)
                level <= 1699 -> format1100To1999Level(level, includeBrackets, includeOrnament, RED, DARK_RED)
                level <= 1799 -> format1100To1999Level(level, includeBrackets, includeOrnament, LIGHT_PURPLE, DARK_PURPLE)
                level <= 1899 -> format1100To1999Level(level, includeBrackets, includeOrnament, BLUE, DARK_BLUE)
                level <= 1999 -> format1100To1999Level(level, includeBrackets, includeOrnament, DARK_PURPLE, DARK_GRAY)

                level <= 2099 -> formatThousandLevel(level, includeBrackets, includeOrnament, LEVEL_ORNAMENT_2, DARK_GRAY, GRAY, WHITE, WHITE, GRAY, GRAY, DARK_GRAY)
                level <= 2199 -> format2100To3099Level(level, includeBrackets, includeOrnament, WHITE, YELLOW, GOLD, GOLD, GOLD)
                level <= 2299 -> format2100To3099Level(level, includeBrackets, includeOrnament, GOLD, WHITE, AQUA, DARK_AQUA, DARK_AQUA)
                level <= 2399 -> format2100To3099Level(level, includeBrackets, includeOrnament, DARK_PURPLE, LIGHT_PURPLE, GOLD, YELLOW, DARK_AQUA)
                level <= 2499 -> format2100To3099Level(level, includeBrackets, includeOrnament, AQUA, WHITE, GRAY, GRAY, DARK_GRAY)
                level <= 2599 -> format2100To3099Level(level, includeBrackets, includeOrnament, WHITE, GREEN, DARK_GRAY, DARK_GRAY, DARK_GRAY)
                level <= 2699 -> format2100To3099Level(level, includeBrackets, includeOrnament, DARK_RED, RED, LIGHT_PURPLE, LIGHT_PURPLE, DARK_PURPLE)
                level <= 2799 -> format2100To3099Level(level, includeBrackets, includeOrnament, YELLOW, WHITE, DARK_GRAY, DARK_GRAY, DARK_GRAY)
                level <= 2899 -> format2100To3099Level(level, includeBrackets, includeOrnament, GREEN, DARK_GREEN, GOLD, GOLD, YELLOW)
                level <= 2999 -> format2100To3099Level(level, includeBrackets, includeOrnament, AQUA, DARK_AQUA, BLUE, BLUE, DARK_BLUE)

                level <= 3099 -> format2100To3099Level(level, includeBrackets, includeOrnament, YELLOW, GOLD, RED, RED, DARK_RED)
                level <= 3199 -> format3100To9999Level(level, includeBrackets, includeOrnament, BLUE, BLUE, AQUA, AQUA, GOLD, GOLD, YELLOW)
                level <= 3299 -> format3100To9999Level(level, includeBrackets, includeOrnament, RED, DARK_RED, GRAY, GRAY, DARK_RED, RED, RED)
                level <= 3399 -> format3100To9999Level(level, includeBrackets, includeOrnament, BLUE, BLUE, BLUE, LIGHT_PURPLE, RED, RED, DARK_RED)
                level <= 3499 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_GREEN, GREEN, LIGHT_PURPLE, LIGHT_PURPLE, DARK_PURPLE, DARK_PURPLE, DARK_GREEN)
                level <= 3599 -> format3100To9999Level(level, includeBrackets, includeOrnament, RED, RED, DARK_RED, DARK_RED, DARK_GREEN, GREEN, GREEN)
                level <= 3699 -> format3100To9999Level(level, includeBrackets, includeOrnament, GREEN, GREEN, GREEN, AQUA, BLUE, BLUE, DARK_BLUE)
                level <= 3799 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_RED, DARK_RED, RED, RED, AQUA, DARK_AQUA, DARK_AQUA)
                level <= 3899 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_BLUE, DARK_BLUE, BLUE, DARK_PURPLE, DARK_PURPLE, LIGHT_PURPLE, DARK_BLUE)
                level <= 3999 -> format3100To9999Level(level, includeBrackets, includeOrnament, RED, RED, GREEN, GREEN, AQUA, BLUE, BLUE)

                level <= 4099 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_PURPLE, DARK_PURPLE, RED, RED, GOLD, GOLD, YELLOW)
                level <= 4199 -> format3100To9999Level(level, includeBrackets, includeOrnament, YELLOW, YELLOW, GOLD, RED, LIGHT_PURPLE, LIGHT_PURPLE, DARK_PURPLE)
                level <= 4299 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_BLUE, BLUE, DARK_AQUA, AQUA, WHITE, GRAY, GRAY)
                level <= 4399 -> format3100To9999Level(level, includeBrackets, includeOrnament, BLACK, DARK_PURPLE, DARK_GRAY, DARK_GRAY, DARK_PURPLE, DARK_PURPLE, BLACK)
                level <= 4499 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_GREEN, DARK_GREEN, GREEN, YELLOW, GOLD, DARK_PURPLE, LIGHT_PURPLE)
                level <= 4599 -> format3100To9999Level(level, includeBrackets, includeOrnament, WHITE, WHITE, AQUA, AQUA, DARK_AQUA, DARK_AQUA, DARK_AQUA)
                level <= 4699 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_AQUA, AQUA, YELLOW, YELLOW, GOLD, LIGHT_PURPLE, DARK_PURPLE)
                level <= 4799 -> format3100To9999Level(level, includeBrackets, includeOrnament, WHITE, DARK_RED, RED, RED, BLUE, DARK_BLUE, BLUE)
                level <= 4899 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_PURPLE, DARK_PURPLE, RED, GOLD, YELLOW, AQUA, DARK_AQUA)
                level <= 4999 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_GREEN, GREEN, WHITE, WHITE, GREEN, GREEN, DARK_GREEN)

                level <= 9999 -> format3100To9999Level(level, includeBrackets, includeOrnament, DARK_RED, DARK_RED, DARK_PURPLE, BLUE, BLUE, DARK_BLUE, BLACK)

                else -> {
                    val ornament = if (includeOrnament) LEVEL_ORNAMENT_4 else ""
                    if (includeBrackets) "$BLACK[$level$ornament]" else "$BLACK$level$ornament"
                }
            }
        }

        private fun format0To999Level(level: Int, includeBrackets: Boolean, includeOrnament: Boolean, color: String): String {
            val ornament = if (includeOrnament) LEVEL_ORNAMENT_1 else ""
            return if (includeBrackets) "$color[$level$ornament]" else "$color$level$ornament"
        }

        private fun format1100To1999Level(level: Int, includeBrackets: Boolean, includeOrnament: Boolean, levelColor: String, ornamentColor: String): String {
            val ornament = if (includeOrnament) ornamentColor + LEVEL_ORNAMENT_2 else ""
            return if (includeBrackets) "$GRAY[$levelColor$level$ornament$GRAY]" else "$levelColor$level$ornament"
        }

        private fun format2100To3099Level(level: Int, includeBrackets: Boolean, includeOrnament: Boolean, color1: String, color2: String, color3: String, color4: String,
                                          color5: String): String =
            formatThousandLevel(level, includeBrackets, includeOrnament, LEVEL_ORNAMENT_3, color1, color1, color2, color2, color3, color4, color5)

        private fun format3100To9999Level(level: Int, includeBrackets: Boolean, includeOrnament: Boolean, color1: String, color2: String, color3: String, color4: String,
                                          color5: String, color6: String, color7: String): String =
            formatThousandLevel(level, includeBrackets, includeOrnament, LEVEL_ORNAMENT_4, color1, color2, color3, color4, color5, color6, color7)

        private fun formatThousandLevel(level: Int, includeBrackets: Boolean, includeOrnament: Boolean, ornament: String, color1: String, color2: String, color3: String,
                                        color4: String, color5: String, color6: String, color7: String): String {
            val levelStr = level.toString()
            val formattedOrnament = if (includeOrnament) color6 + ornament else ""
            val formattedLevel = "$color2${levelStr[0]}$color3${levelStr[1]}$color4${levelStr[2]}$color5${levelStr[3]}"
            return if (includeBrackets) "$color1[$formattedLevel$formattedOrnament$color7]" else "$formattedLevel$formattedOrnament"
        }
    }
}
