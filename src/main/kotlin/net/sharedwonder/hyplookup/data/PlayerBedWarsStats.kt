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

package net.sharedwonder.hyplookup.data

import kotlin.math.floor
import net.sharedwonder.lightproxy.util.MCText.AQUA
import net.sharedwonder.lightproxy.util.MCText.BLACK
import net.sharedwonder.lightproxy.util.MCText.BLUE
import net.sharedwonder.lightproxy.util.MCText.DARK_AQUA
import net.sharedwonder.lightproxy.util.MCText.DARK_BLUE
import net.sharedwonder.lightproxy.util.MCText.DARK_GRAY
import net.sharedwonder.lightproxy.util.MCText.DARK_GREEN
import net.sharedwonder.lightproxy.util.MCText.DARK_PURPLE
import net.sharedwonder.lightproxy.util.MCText.DARK_RED
import net.sharedwonder.lightproxy.util.MCText.GOLD
import net.sharedwonder.lightproxy.util.MCText.GRAY
import net.sharedwonder.lightproxy.util.MCText.GREEN
import net.sharedwonder.lightproxy.util.MCText.LIGHT_PURPLE
import net.sharedwonder.lightproxy.util.MCText.RED
import net.sharedwonder.lightproxy.util.MCText.WHITE
import net.sharedwonder.lightproxy.util.MCText.YELLOW

class PlayerBedWarsStats(map: Map<String, *>?) {
    val experience: Int

    val level: Double

    val `level-rounded`: Int

    val `level-formatted`: String

    val `level-formatted-without-brackets`: String

    val `level-formatted-without-ornament`: String

    val `level-formatted-simple`: String

    val coins: Int

    val `solo-mode win-streak`: Int?

    val `solo-mode games-played`: Int

    val `solo-mode wins`: Int

    val `solo-mode losses`: Int

    val `solo-mode wlr`: Double

    val `solo-mode win-rate`: Double

    val `solo-mode normal-kills`: Int

    val `solo-mode normal-deaths`: Int

    val `solo-mode normal-kdr`: Double

    val `solo-mode final-kills`: Int

    val `solo-mode final-deaths`: Int

    val `solo-mode final-kdr`: Double

    val `solo-mode total-kills`: Int

    val `solo-mode total-deaths`: Int

    val `solo-mode total-kdr`: Double

    val `solo-mode beds-broken`: Int

    val `solo-mode beds-lost`: Int

    val `solo-mode bblr`: Double

    val `doubles-mode win-streak`: Int?

    val `doubles-mode games-played`: Int

    val `doubles-mode wins`: Int

    val `doubles-mode losses`: Int

    val `doubles-mode wlr`: Double

    val `doubles-mode win-rate`: Double

    val `doubles-mode normal-kills`: Int

    val `doubles-mode normal-deaths`: Int

    val `doubles-mode normal-kdr`: Double

    val `doubles-mode final-kills`: Int

    val `doubles-mode final-deaths`: Int

    val `doubles-mode final-kdr`: Double

    val `doubles-mode total-kills`: Int

    val `doubles-mode total-deaths`: Int

    val `doubles-mode total-kdr`: Double

    val `doubles-mode beds-broken`: Int

    val `doubles-mode beds-lost`: Int

    val `doubles-mode bblr`: Double

    val `3v3v3v3-mode win-streak`: Int?

    val `3v3v3v3-mode games-played`: Int

    val `3v3v3v3-mode wins`: Int

    val `3v3v3v3-mode losses`: Int

    val `3v3v3v3-mode wlr`: Double

    val `3v3v3v3-mode win-rate`: Double

    val `3v3v3v3-mode normal-kills`: Int

    val `3v3v3v3-mode normal-deaths`: Int

    val `3v3v3v3-mode normal-kdr`: Double

    val `3v3v3v3-mode final-kills`: Int

    val `3v3v3v3-mode final-deaths`: Int

    val `3v3v3v3-mode final-kdr`: Double

    val `3v3v3v3-mode total-kills`: Int

    val `3v3v3v3-mode total-deaths`: Int

    val `3v3v3v3-mode total-kdr`: Double

    val `3v3v3v3-mode beds-broken`: Int

    val `3v3v3v3-mode beds-lost`: Int

    val `3v3v3v3-mode bblr`: Double

    val `4v4v4v4-mode win-streak`: Int?

    val `4v4v4v4-mode games-played`: Int

    val `4v4v4v4-mode wins`: Int

    val `4v4v4v4-mode losses`: Int

    val `4v4v4v4-mode wlr`: Double

    val `4v4v4v4-mode win-rate`: Double

    val `4v4v4v4-mode normal-kills`: Int

    val `4v4v4v4-mode normal-deaths`: Int

    val `4v4v4v4-mode normal-kdr`: Double

    val `4v4v4v4-mode final-kills`: Int

    val `4v4v4v4-mode final-deaths`: Int

    val `4v4v4v4-mode final-kdr`: Double

    val `4v4v4v4-mode total-kills`: Int

    val `4v4v4v4-mode total-deaths`: Int

    val `4v4v4v4-mode total-kdr`: Double

    val `4v4v4v4-mode beds-broken`: Int

    val `4v4v4v4-mode beds-lost`: Int

    val `4v4v4v4-mode bblr`: Double

    val `4v4-mode win-streak`: Int?

    val `4v4-mode games-played`: Int

    val `4v4-mode wins`: Int

    val `4v4-mode losses`: Int

    val `4v4-mode wlr`: Double

    val `4v4-mode win-rate`: Double

    val `4v4-mode normal-kills`: Int

    val `4v4-mode normal-deaths`: Int

    val `4v4-mode normal-kdr`: Double

    val `4v4-mode final-kills`: Int

    val `4v4-mode final-deaths`: Int

    val `4v4-mode final-kdr`: Double

    val `4v4-mode total-kills`: Int

    val `4v4-mode total-deaths`: Int

    val `4v4-mode total-kdr`: Double

    val `4v4-mode beds-broken`: Int

    val `4v4-mode beds-lost`: Int

    val `4v4-mode bblr`: Double

    val `core-modes win-streak`: Int?

    val `core-modes games-played`: Int

    val `core-modes wins`: Int

    val `core-modes losses`: Int

    val `core-modes wlr`: Double

    val `core-modes win-rate`: Double

    val `core-modes normal-kills`: Int

    val `core-modes normal-deaths`: Int

    val `core-modes normal-kdr`: Double

    val `core-modes final-kills`: Int

    val `core-modes final-deaths`: Int

    val `core-modes final-kdr`: Double

    val `core-modes total-kills`: Int

    val `core-modes total-deaths`: Int

    val `core-modes total-kdr`: Double

    val `core-modes beds-broken`: Int

    val `core-modes beds-lost`: Int

    val `core-modes bblr`: Double

    val `normal-modes games-played`: Int

    val `normal-modes wins`: Int

    val `normal-modes losses`: Int

    val `normal-modes wlr`: Double

    val `normal-modes win-rate`: Double

    val `normal-modes normal-kills`: Int

    val `normal-modes normal-deaths`: Int

    val `normal-modes normal-kdr`: Double

    val `normal-modes final-kills`: Int

    val `normal-modes final-deaths`: Int

    val `normal-modes final-kdr`: Double

    val `normal-modes total-kills`: Int

    val `normal-modes total-deaths`: Int

    val `normal-modes total-kdr`: Double

    val `normal-modes beds-broken`: Int

    val `normal-modes beds-lost`: Int

    val `normal-modes bblr`: Double

    val `all-modes games-played`: Int

    init {
        fun getIntOrNull(key: String) = (map?.get(key) as Number?)?.toInt()
        fun getIntOrZero(key: String) = (map?.get(key) as Number?)?.toInt() ?: 0

        experience = getIntOrZero("Experience")
        level = calculateLevel(experience)
        `level-rounded` = calculateLevel(experience).toInt()
        `level-formatted` = formatLevel(`level-rounded`, includeBrackets = true, includeOrnament = true)
        `level-formatted-without-brackets` = formatLevel(`level-rounded`, includeBrackets = false, includeOrnament = true)
        `level-formatted-without-ornament` = formatLevel(`level-rounded`, includeBrackets = true, includeOrnament = false)
        `level-formatted-simple` = formatLevel(`level-rounded`, includeBrackets = false, includeOrnament = false)
        coins = getIntOrZero("coins")

        `solo-mode win-streak` = getIntOrNull("eight_one_winstreak")
        `solo-mode games-played` = getIntOrZero("eight_one_games_played_bedwars")
        `solo-mode wins` = getIntOrZero("eight_one_wins_bedwars")
        `solo-mode losses` = getIntOrZero("eight_one_losses_bedwars")
        `solo-mode wlr` = `solo-mode wins`.toDouble() / `solo-mode losses`
        `solo-mode win-rate` = `solo-mode wins`.toDouble() / `solo-mode games-played`
        `solo-mode normal-kills` = getIntOrZero("eight_one_kills_bedwars")
        `solo-mode normal-deaths` = getIntOrZero("eight_one_deaths_bedwars")
        `solo-mode normal-kdr` = `solo-mode normal-kills`.toDouble() / `solo-mode normal-deaths`
        `solo-mode final-kills` = getIntOrZero("eight_one_final_kills_bedwars")
        `solo-mode final-deaths` = getIntOrZero("eight_one_final_deaths_bedwars")
        `solo-mode final-kdr` = `solo-mode final-kills`.toDouble() / `solo-mode final-deaths`
        `solo-mode total-kills` = `solo-mode normal-kills` + `solo-mode final-kills`
        `solo-mode total-deaths` = `solo-mode normal-deaths` + `solo-mode final-deaths`
        `solo-mode total-kdr` = `solo-mode total-kills`.toDouble() / `solo-mode total-deaths`
        `solo-mode beds-broken` = getIntOrZero("eight_one_beds_broken_bedwars")
        `solo-mode beds-lost` = getIntOrZero("eight_one_beds_lost_bedwars")
        `solo-mode bblr` = `solo-mode beds-broken`.toDouble() / `solo-mode beds-lost`

        `doubles-mode win-streak` = getIntOrNull("eight_two_winstreak")
        `doubles-mode games-played` = getIntOrZero("eight_two_games_played_bedwars")
        `doubles-mode wins` = getIntOrZero("eight_two_wins_bedwars")
        `doubles-mode losses` = getIntOrZero("eight_two_losses_bedwars")
        `doubles-mode wlr` = `doubles-mode wins`.toDouble() / `doubles-mode losses`
        `doubles-mode win-rate` = `doubles-mode wins`.toDouble() / `doubles-mode games-played`
        `doubles-mode normal-kills` = getIntOrZero("eight_two_kills_bedwars")
        `doubles-mode normal-deaths` = getIntOrZero("eight_two_deaths_bedwars")
        `doubles-mode normal-kdr` = `doubles-mode normal-kills`.toDouble() / `doubles-mode normal-deaths`
        `doubles-mode final-kills` = getIntOrZero("eight_two_final_kills_bedwars")
        `doubles-mode final-deaths` = getIntOrZero("eight_two_final_deaths_bedwars")
        `doubles-mode final-kdr` = `doubles-mode final-kills`.toDouble() / `doubles-mode final-deaths`
        `doubles-mode total-kills` = `doubles-mode normal-kills` + `doubles-mode final-kills`
        `doubles-mode total-deaths` = `doubles-mode normal-deaths` + `doubles-mode final-deaths`
        `doubles-mode total-kdr` = `doubles-mode total-kills`.toDouble() / `doubles-mode total-deaths`
        `doubles-mode beds-broken` = getIntOrZero("eight_two_beds_broken_bedwars")
        `doubles-mode beds-lost` = getIntOrZero("eight_two_beds_lost_bedwars")
        `doubles-mode bblr` = `doubles-mode beds-broken`.toDouble() / `doubles-mode beds-lost`

        `3v3v3v3-mode win-streak` = getIntOrNull("four_three_winstreak")
        `3v3v3v3-mode games-played` = getIntOrZero("four_three_games_played_bedwars")
        `3v3v3v3-mode wins` = getIntOrZero("four_three_wins_bedwars")
        `3v3v3v3-mode losses` = getIntOrZero("four_three_losses_bedwars")
        `3v3v3v3-mode wlr` = `3v3v3v3-mode wins`.toDouble() / `3v3v3v3-mode losses`
        `3v3v3v3-mode win-rate` = `3v3v3v3-mode wins`.toDouble() / `3v3v3v3-mode games-played`
        `3v3v3v3-mode normal-kills` = getIntOrZero("four_three_kills_bedwars")
        `3v3v3v3-mode normal-deaths` = getIntOrZero("four_three_deaths_bedwars")
        `3v3v3v3-mode normal-kdr` = `3v3v3v3-mode normal-kills`.toDouble() / `3v3v3v3-mode normal-deaths`
        `3v3v3v3-mode final-kills` = getIntOrZero("four_three_final_kills_bedwars")
        `3v3v3v3-mode final-deaths` = getIntOrZero("four_three_final_deaths_bedwars")
        `3v3v3v3-mode final-kdr` = `3v3v3v3-mode final-kills`.toDouble() / `3v3v3v3-mode final-deaths`
        `3v3v3v3-mode total-kills` = `3v3v3v3-mode normal-kills` + `3v3v3v3-mode final-kills`
        `3v3v3v3-mode total-deaths` = `3v3v3v3-mode normal-deaths` + `3v3v3v3-mode final-deaths`
        `3v3v3v3-mode total-kdr` = `3v3v3v3-mode total-kills`.toDouble() / `3v3v3v3-mode total-deaths`
        `3v3v3v3-mode beds-broken` = getIntOrZero("four_three_beds_broken_bedwars")
        `3v3v3v3-mode beds-lost` = getIntOrZero("four_three_beds_lost_bedwars")
        `3v3v3v3-mode bblr` = `3v3v3v3-mode beds-broken`.toDouble() / `3v3v3v3-mode beds-lost`

        `4v4v4v4-mode win-streak` = getIntOrNull("four_four_winstreak")
        `4v4v4v4-mode games-played` = getIntOrZero("four_four_games_played_bedwars")
        `4v4v4v4-mode wins` = getIntOrZero("four_four_wins_bedwars")
        `4v4v4v4-mode losses` = getIntOrZero("four_four_losses_bedwars")
        `4v4v4v4-mode wlr` = `4v4v4v4-mode wins`.toDouble() / `4v4v4v4-mode losses`
        `4v4v4v4-mode win-rate` = `4v4v4v4-mode wins`.toDouble() / `4v4v4v4-mode games-played`
        `4v4v4v4-mode normal-kills` = getIntOrZero("four_four_kills_bedwars")
        `4v4v4v4-mode normal-deaths` = getIntOrZero("four_four_deaths_bedwars")
        `4v4v4v4-mode normal-kdr` = `4v4v4v4-mode normal-kills`.toDouble() / `4v4v4v4-mode normal-deaths`
        `4v4v4v4-mode final-kills` = getIntOrZero("four_four_final_kills_bedwars")
        `4v4v4v4-mode final-deaths` = getIntOrZero("four_four_final_deaths_bedwars")
        `4v4v4v4-mode final-kdr` = `4v4v4v4-mode final-kills`.toDouble() / `4v4v4v4-mode final-deaths`
        `4v4v4v4-mode total-kills` = `4v4v4v4-mode normal-kills` + `4v4v4v4-mode final-kills`
        `4v4v4v4-mode total-deaths` = `4v4v4v4-mode normal-deaths` + `4v4v4v4-mode final-deaths`
        `4v4v4v4-mode total-kdr` = `4v4v4v4-mode total-kills`.toDouble() / `4v4v4v4-mode total-deaths`
        `4v4v4v4-mode beds-broken` = getIntOrZero("four_four_beds_broken_bedwars")
        `4v4v4v4-mode beds-lost` = getIntOrZero("four_four_beds_lost_bedwars")
        `4v4v4v4-mode bblr` = `4v4v4v4-mode beds-broken`.toDouble() / `4v4v4v4-mode beds-lost`

        `4v4-mode win-streak` = getIntOrNull("two_four_winstreak")
        `4v4-mode games-played` = getIntOrZero("two_four_games_played_bedwars")
        `4v4-mode wins` = getIntOrZero("two_four_wins_bedwars")
        `4v4-mode losses` = getIntOrZero("two_four_losses_bedwars")
        `4v4-mode wlr` = `4v4-mode wins`.toDouble() / `4v4-mode losses`
        `4v4-mode win-rate` = `4v4-mode wins`.toDouble() / `4v4-mode games-played`
        `4v4-mode normal-kills` = getIntOrZero("two_four_kills_bedwars")
        `4v4-mode normal-deaths` = getIntOrZero("two_four_deaths_bedwars")
        `4v4-mode normal-kdr` = `4v4-mode normal-kills`.toDouble() / `4v4-mode normal-deaths`
        `4v4-mode final-kills` = getIntOrZero("two_four_final_kills_bedwars")
        `4v4-mode final-deaths` = getIntOrZero("two_four_final_deaths_bedwars")
        `4v4-mode final-kdr` = `4v4-mode final-kills`.toDouble() / `4v4-mode final-deaths`
        `4v4-mode total-kills` = `4v4-mode normal-kills` + `4v4-mode final-kills`
        `4v4-mode total-deaths` = `4v4-mode normal-deaths` + `4v4-mode final-deaths`
        `4v4-mode total-kdr` = `4v4-mode total-kills`.toDouble() / `4v4-mode total-deaths`
        `4v4-mode beds-broken` = getIntOrZero("two_four_beds_broken_bedwars")
        `4v4-mode beds-lost` = getIntOrZero("two_four_beds_lost_bedwars")
        `4v4-mode bblr` = `4v4-mode beds-broken`.toDouble() / `4v4-mode beds-lost`

        `core-modes win-streak` = getIntOrNull("winstreak")
        `core-modes games-played` = `solo-mode games-played` + `doubles-mode games-played` + `3v3v3v3-mode games-played` + `4v4v4v4-mode games-played`
        `core-modes wins` = `solo-mode wins` + `doubles-mode wins` + `3v3v3v3-mode wins` + `4v4v4v4-mode wins`
        `core-modes losses` = `solo-mode losses` + `doubles-mode losses` + `3v3v3v3-mode losses` + `4v4v4v4-mode losses`
        `core-modes wlr` = `core-modes wins`.toDouble() / `core-modes losses`
        `core-modes win-rate` = `core-modes wins`.toDouble() / `core-modes games-played`
        `core-modes normal-kills` = `solo-mode normal-kills` + `doubles-mode normal-kills` + `3v3v3v3-mode normal-kills` + `4v4v4v4-mode normal-kills`
        `core-modes normal-deaths` = `solo-mode normal-deaths` + `doubles-mode normal-deaths` + `3v3v3v3-mode normal-deaths` + `4v4v4v4-mode normal-deaths`
        `core-modes normal-kdr` = `core-modes normal-kills`.toDouble() / `core-modes normal-deaths`
        `core-modes final-kills` = `solo-mode final-kills` + `doubles-mode final-kills` + `3v3v3v3-mode final-kills` + `4v4v4v4-mode final-kills`
        `core-modes final-deaths` = `solo-mode final-deaths` + `doubles-mode final-deaths` + `3v3v3v3-mode final-deaths` + `4v4v4v4-mode final-deaths`
        `core-modes final-kdr` = `core-modes final-kills`.toDouble() / `core-modes final-deaths`
        `core-modes total-kills` = `core-modes normal-kills` + `core-modes final-kills`
        `core-modes total-deaths` = `core-modes normal-deaths` + `core-modes final-deaths`
        `core-modes total-kdr` = `core-modes total-kills`.toDouble() / `core-modes total-deaths`
        `core-modes beds-broken` = `solo-mode beds-broken` + `doubles-mode beds-broken` + `3v3v3v3-mode beds-broken` + `4v4v4v4-mode beds-broken`
        `core-modes beds-lost` = `solo-mode beds-lost` + `doubles-mode beds-lost` + `3v3v3v3-mode beds-lost` + `4v4v4v4-mode beds-lost`
        `core-modes bblr` = `core-modes beds-broken`.toDouble() / `core-modes beds-lost`

        `normal-modes games-played` = getIntOrZero("games_played_bedwars")
        `normal-modes wins` = getIntOrZero("wins_bedwars")
        `normal-modes losses` = getIntOrZero("losses_bedwars")
        `normal-modes wlr` = `normal-modes wins`.toDouble() / `normal-modes losses`
        `normal-modes win-rate` = `normal-modes wins`.toDouble() / `normal-modes games-played`
        `normal-modes normal-kills` = getIntOrZero("kills_bedwars")
        `normal-modes normal-deaths` = getIntOrZero("deaths_bedwars")
        `normal-modes normal-kdr` = `normal-modes normal-kills`.toDouble() / `normal-modes normal-deaths`
        `normal-modes final-kills` = getIntOrZero("final_kills_bedwars")
        `normal-modes final-deaths` = getIntOrZero("final_deaths_bedwars")
        `normal-modes final-kdr` = `normal-modes final-kills`.toDouble() / `normal-modes final-deaths`
        `normal-modes total-kills` = `normal-modes normal-kills` + `normal-modes final-kills`
        `normal-modes total-deaths` = `normal-modes normal-deaths` + `normal-modes final-deaths`
        `normal-modes total-kdr` = `normal-modes total-kills`.toDouble() / `normal-modes total-deaths`
        `normal-modes beds-broken` = getIntOrZero("beds_broken_bedwars")
        `normal-modes beds-lost` = getIntOrZero("beds_lost_bedwars")
        `normal-modes bblr` = `normal-modes beds-broken`.toDouble() / `normal-modes beds-lost`

        `all-modes games-played` = getIntOrZero("games_played_bedwars_1")
    }
}

private const val LEVEL_ORNAMENT_1 = "✫"

private const val LEVEL_ORNAMENT_2 = "✪"

private const val LEVEL_ORNAMENT_3 = "⚝"

private const val LEVEL_ORNAMENT_4 = "✥"

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
