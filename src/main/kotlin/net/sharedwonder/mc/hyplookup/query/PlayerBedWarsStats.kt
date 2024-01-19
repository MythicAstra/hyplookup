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

@file:Suppress("DIVISION_BY_ZERO")

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
import kotlin.LazyThreadSafetyMode.PUBLICATION
import kotlin.math.floor
import com.google.gson.annotations.SerializedName

class PlayerBedWarsStats {
    @SerializedName("Experience")
    val experience: Int = 0

    val level: Int by lazy(PUBLICATION) { fullLevel.toInt() }

    val fullLevel: Double by lazy(PUBLICATION) { calculateLevel(experience) }

    val formattedLevel: String by lazy(PUBLICATION) { formatLevel(level, includeBrackets = true, includeOrnament = true) }

    val formattedLevelWithoutOrnament: String by lazy(PUBLICATION) { formatLevel(level, includeBrackets = true, includeOrnament = false) }

    val formattedLevelWithoutBrackets: String by lazy(PUBLICATION) { formatLevel(level, includeBrackets = false, includeOrnament = true) }

    val formattedLevelSimple: String by lazy(PUBLICATION) { formatLevel(level, includeBrackets = false, includeOrnament = false) }

    val coins: Int = 0

    // All modes

    @SerializedName("games_played_bedwars_1")
    val allModePlayed: Int = 0

    // Normal modes

    @SerializedName("games_played_bedwars")
    val normalModesPlayed: Int = 0

    @SerializedName("wins_bedwars")
    val normalModesWins: Int = 0

    @SerializedName("losses_bedwars")
    val normalModesLosses: Int = 0

    val normalModesWlr: Double by lazy(PUBLICATION) { normalModesWins.toDouble() / normalModesLosses }

    val normalModesWinRate: Double by lazy(PUBLICATION) { normalModesWins.toDouble() / normalModesPlayed }

    @SerializedName("kills_bedwars")
    val normalModesNormalKills: Int = 0

    @SerializedName("deaths_bedwars")
    val normalModesNormalDeaths: Int = 0

    val normalModesNkdr: Double by lazy(PUBLICATION) { normalModesNormalKills.toDouble() / normalModesNormalDeaths }

    @SerializedName("final_kills_bedwars")
    val normalModesFinalKills: Int = 0

    @SerializedName("final_deaths_bedwars")
    val normalModesFinalDeaths: Int = 0

    val normalModesFkdr: Double by lazy(PUBLICATION) { normalModesFinalKills.toDouble() / normalModesFinalDeaths }

    @SerializedName("beds_broken_bedwars")
    val normalModesBedsBroken: Int = 0

    @SerializedName("beds_lost_bedwars")
    val normalModesBedsLost: Int = 0

    val normalModesBblr: Double by lazy(PUBLICATION) { normalModesBedsBroken.toDouble() / normalModesBedsLost }

    val normalModesTotalKills: Int by lazy(PUBLICATION) { normalModesNormalKills + normalModesFinalKills }

    val normalModesTotalDeaths: Int by lazy(PUBLICATION) { normalModesNormalDeaths + normalModesFinalDeaths }

    val normalModesTkdr: Double by lazy(PUBLICATION) { normalModesTotalKills.toDouble() / normalModesTotalDeaths }

    // Core modes

    @SerializedName("winstreak")
    val coreModesWinStreak: Int? = null

    val coreModesPlayed: Int by lazy(PUBLICATION) { eightOneModePlayed + eightOneModePlayed + fourThreeModePlayed + fourFourModePlayed }

    val coreModesWins: Int by lazy(PUBLICATION) { eightOneModeWins + eightOneModeWins + fourThreeModeWins + fourFourModeWins }

    val coreModesLosses: Int by lazy(PUBLICATION) { eightOneModeLosses + eightOneModeLosses + fourThreeModeLosses + fourFourModeLosses }

    val coreModesWlr: Double by lazy(PUBLICATION) { coreModesWins.toDouble() / coreModesLosses }

    val coreModesWinRate: Double by lazy(PUBLICATION) { coreModesWins.toDouble() / coreModesPlayed }

    val coreModesNormalKills: Int by lazy(PUBLICATION) { eightOneModeNormalKills + eightOneModeNormalKills + fourThreeModeNormalKills + fourFourModeNormalKills }

    val coreModesNormalDeaths: Int by lazy(PUBLICATION) { eightOneModeNormalDeaths + eightOneModeNormalDeaths + fourThreeModeNormalDeaths + fourFourModeNormalDeaths }

    val coreModesNkdr: Double by lazy(PUBLICATION) { coreModesNormalKills.toDouble() / coreModesNormalDeaths }

    val coreModesFinalKills: Int by lazy(PUBLICATION) { eightOneModeFinalKills + eightOneModeFinalKills + fourThreeModeFinalKills + fourFourModeFinalKills }

    val coreModesFinalDeaths: Int by lazy(PUBLICATION) { eightOneModeFinalDeaths + eightOneModeFinalDeaths + fourThreeModeFinalDeaths + fourFourModeFinalDeaths }

    val coreModesFkdr: Double by lazy(PUBLICATION) { coreModesFinalKills.toDouble() / coreModesFinalDeaths }

    val coreModesTotalKills: Int by lazy(PUBLICATION) { coreModesNormalKills + coreModesFinalKills }

    val coreModesTotalDeaths: Int by lazy(PUBLICATION) { coreModesNormalDeaths + coreModesFinalDeaths }

    val coreModesTkdr: Double by lazy(PUBLICATION) { coreModesTotalKills.toDouble() / coreModesTotalDeaths }

    val coreModesBedsBroken: Int by lazy(PUBLICATION) { eightOneModeBedsBroken + eightOneModeBedsBroken + fourThreeModeBedsBroken + fourFourModeBedsBroken }

    val coreModesBedsLost: Int by lazy(PUBLICATION) { eightOneModeBedsLost + eightOneModeBedsLost + fourThreeModeBedsLost + fourFourModeBedsLost }

    val coreModesBblr: Double by lazy(PUBLICATION) { coreModesBedsBroken.toDouble() / coreModesBedsLost }

    // Solo mode

    @SerializedName("eight_one_winstreak")
    val eightOneModeWinStreak: Int? = null

    @SerializedName("eight_one_games_played_bedwars")
    val eightOneModePlayed: Int = 0

    @SerializedName("eight_one_wins_bedwars")
    val eightOneModeWins: Int = 0

    @SerializedName("eight_one_losses_bedwars")
    val eightOneModeLosses: Int = 0

    val eightOneModeWlr: Double by lazy(PUBLICATION) { eightOneModeWins.toDouble() / eightOneModeLosses }

    val eightOneModeWinRate: Double by lazy(PUBLICATION) { eightOneModeWins.toDouble() / eightOneModePlayed }

    @SerializedName("eight_one_kills_bedwars")
    val eightOneModeNormalKills: Int = 0

    @SerializedName("eight_one_deaths_bedwars")
    val eightOneModeNormalDeaths: Int = 0

    val eightOneModeNkdr: Double by lazy(PUBLICATION) { eightOneModeNormalKills.toDouble() / eightOneModeNormalDeaths }

    @SerializedName("eight_one_final_kills_bedwars")
    val eightOneModeFinalKills: Int = 0

    @SerializedName("eight_one_final_deaths_bedwars")
    val eightOneModeFinalDeaths: Int = 0

    val eightOneModeFkdr: Double by lazy(PUBLICATION) { eightOneModeFinalKills.toDouble() / eightOneModeFinalDeaths }

    val eightOneModeTotalKills: Int by lazy(PUBLICATION) { eightOneModeNormalKills + eightOneModeFinalKills }

    val eightOneModeTotalDeaths: Int by lazy(PUBLICATION) { eightOneModeNormalDeaths + eightOneModeFinalDeaths }

    val eightOneModeTkdr: Double by lazy(PUBLICATION) { eightOneModeTotalKills.toDouble() / eightOneModeTotalDeaths }

    @SerializedName("eight_one_beds_broken_bedwars")
    val eightOneModeBedsBroken: Int = 0

    @SerializedName("eight_one_beds_lost_bedwars")
    val eightOneModeBedsLost: Int = 0

    val eightOneModeBblr: Double by lazy(PUBLICATION) { eightOneModeBedsBroken.toDouble() / eightOneModeBedsLost }

    // Doubles mode

    @SerializedName("eight_two_winstreak")
    val eightTwoModeWinStreak: Int? = null

    @SerializedName("eight_two_games_played_bedwars")
    val eightTwoModePlayed: Int = 0

    @SerializedName("eight_two_wins_bedwars")
    val eightTwoModeWins: Int = 0

    @SerializedName("eight_two_losses_bedwars")
    val eightTwoModeLosses: Int = 0

    val eightTwoModeWlr: Double by lazy(PUBLICATION) { eightTwoModeWins.toDouble() / eightTwoModeLosses }

    val eightTwoModeWinRate: Double by lazy(PUBLICATION) { eightTwoModeWins.toDouble() / eightTwoModePlayed }

    @SerializedName("eight_two_kills_bedwars")
    val eightTwoModeNormalKills: Int = 0

    @SerializedName("eight_two_deaths_bedwars")
    val eightTwoModeNormalDeaths: Int = 0

    val eightTwoModeNkdr: Double by lazy(PUBLICATION) { eightTwoModeNormalKills.toDouble() / eightTwoModeNormalDeaths }

    @SerializedName("eight_two_final_kills_bedwars")
    val eightTwoModeFinalKills: Int = 0

    @SerializedName("eight_two_final_deaths_bedwars")
    val eightTwoModeFinalDeaths: Int = 0

    val eightTwoModeFkdr: Double by lazy(PUBLICATION) { eightTwoModeFinalKills.toDouble() / eightTwoModeFinalDeaths }

    val eightTwoModeTotalKills: Int by lazy(PUBLICATION) { eightTwoModeNormalKills + eightTwoModeFinalKills }

    val eightTwoModeTotalDeaths: Int by lazy(PUBLICATION) { eightTwoModeNormalDeaths + eightTwoModeFinalDeaths }

    val eightTwoModeTkdr: Double by lazy(PUBLICATION) { eightTwoModeTotalKills.toDouble() / eightTwoModeTotalDeaths }

    @SerializedName("eight_two_beds_broken_bedwars")
    val eightTwoModeBedsBroken: Int = 0

    @SerializedName("eight_two_beds_lost_bedwars")
    val eightTwoModeBedsLost: Int = 0

    val eightTwoModeBblr: Double by lazy(PUBLICATION) { eightTwoModeBedsBroken.toDouble() / eightTwoModeBedsLost }

    // 3v3v3v3 mode

    @SerializedName("four_three_winstreak")
    val fourThreeModeWinStreak: Int? = null

    @SerializedName("four_three_games_played_bedwars")
    val fourThreeModePlayed: Int = 0

    @SerializedName("four_three_wins_bedwars")
    val fourThreeModeWins: Int = 0

    @SerializedName("four_three_losses_bedwars")
    val fourThreeModeLosses: Int = 0

    val fourThreeModeWlr: Double by lazy(PUBLICATION) { fourThreeModeWins.toDouble() / fourThreeModeLosses }

    val fourThreeModeWinRate: Double by lazy(PUBLICATION) { fourThreeModeWins.toDouble() / fourThreeModePlayed }

    @SerializedName("four_three_kills_bedwars")
    val fourThreeModeNormalKills: Int = 0

    @SerializedName("four_three_deaths_bedwars")
    val fourThreeModeNormalDeaths: Int = 0

    val fourThreeModeNkdr: Double by lazy(PUBLICATION) { fourThreeModeNormalKills.toDouble() / fourThreeModeNormalDeaths }

    @SerializedName("four_three_final_kills_bedwars")
    val fourThreeModeFinalKills: Int = 0

    @SerializedName("four_three_final_deaths_bedwars")
    val fourThreeModeFinalDeaths: Int = 0

    val fourThreeModeFkdr: Double by lazy(PUBLICATION) { fourThreeModeFinalKills.toDouble() / fourThreeModeFinalDeaths }

    val fourThreeModeTotalKills: Int by lazy(PUBLICATION) { fourThreeModeNormalKills + fourThreeModeFinalKills }

    val fourThreeModeTotalDeaths: Int by lazy(PUBLICATION) { fourThreeModeNormalDeaths + fourThreeModeFinalDeaths }

    val fourThreeModeTkdr: Double by lazy(PUBLICATION) { fourThreeModeTotalKills.toDouble() / fourThreeModeTotalDeaths }

    @SerializedName("four_three_beds_broken_bedwars")
    val fourThreeModeBedsBroken: Int = 0

    @SerializedName("four_three_beds_lost_bedwars")
    val fourThreeModeBedsLost: Int = 0

    val fourThreeModeBblr: Double by lazy(PUBLICATION) { fourThreeModeBedsBroken.toDouble() / fourThreeModeBedsLost }

    // 4v4v4v4 mode

    @SerializedName("four_four_winstreak")
    val fourFourModeWinStreak: Int? = null

    @SerializedName("four_four_games_played_bedwars")
    val fourFourModePlayed: Int = 0

    @SerializedName("four_four_wins_bedwars")
    val fourFourModeWins: Int = 0

    @SerializedName("four_four_losses_bedwars")
    val fourFourModeLosses: Int = 0

    val fourFourModeWlr: Double by lazy(PUBLICATION) { fourFourModeWins.toDouble() / fourFourModeLosses }

    val fourFourModeWinRate: Double by lazy(PUBLICATION) { fourFourModeWins.toDouble() / fourFourModePlayed }

    @SerializedName("four_four_kills_bedwars")
    val fourFourModeNormalKills: Int = 0

    @SerializedName("four_four_deaths_bedwars")
    val fourFourModeNormalDeaths: Int = 0

    val fourFourModeNkdr: Double by lazy(PUBLICATION) { fourFourModeNormalKills.toDouble() / fourFourModeNormalDeaths }

    @SerializedName("four_four_final_kills_bedwars")
    val fourFourModeFinalKills: Int = 0

    @SerializedName("four_four_final_deaths_bedwars")
    val fourFourModeFinalDeaths: Int = 0

    val fourFourModeFkdr: Double by lazy(PUBLICATION) { fourFourModeFinalKills.toDouble() / fourFourModeFinalDeaths }

    val fourFourModeTotalKills: Int by lazy(PUBLICATION) { fourFourModeNormalKills + fourFourModeFinalKills }

    val fourFourModeTotalDeaths: Int by lazy(PUBLICATION) { fourFourModeNormalDeaths + fourFourModeFinalDeaths }

    val fourFourModeTkdr: Double by lazy(PUBLICATION) { fourFourModeTotalKills.toDouble() / fourFourModeTotalDeaths }

    @SerializedName("four_four_beds_broken_bedwars")
    val fourFourModeBedsBroken: Int = 0

    @SerializedName("four_four_beds_lost_bedwars")
    val fourFourModeBedsLost: Int = 0

    val fourFourModeBblr: Double by lazy(PUBLICATION) { fourFourModeBedsBroken.toDouble() / fourFourModeBedsLost }

    @SerializedName("two_four_winstreak")
    val twoFourModeWinStreak: Int? = null

    @SerializedName("two_four_games_played_bedwars")
    val twoFourModePlayed: Int = 0

    @SerializedName("two_four_wins_bedwars")
    val twoFourModeWins: Int = 0

    @SerializedName("two_four_losses_bedwars")
    val twoFourModeLosses: Int = 0

    val twoFourModeWlr: Double by lazy(PUBLICATION) { twoFourModeWins.toDouble() / twoFourModeLosses }

    val twoFourModeWinRate: Double by lazy(PUBLICATION) { twoFourModeWins.toDouble() / twoFourModePlayed }

    @SerializedName("two_four_kills_bedwars")
    val twoFourModeNormalKills: Int = 0

    @SerializedName("two_four_deaths_bedwars")
    val twoFourModeNormalDeaths: Int = 0

    val twoFourModeNkdr: Double by lazy(PUBLICATION) { twoFourModeNormalKills.toDouble() / twoFourModeNormalDeaths }

    @SerializedName("two_four_final_kills_bedwars")
    val twoFourModeFinalKills: Int = 0

    @SerializedName("two_four_final_deaths_bedwars")
    val twoFourModeFinalDeaths: Int = 0

    val twoFourModeFkdr: Double by lazy(PUBLICATION) { twoFourModeFinalKills.toDouble() / twoFourModeFinalDeaths }

    val twoFourModeTotalKills: Int by lazy(PUBLICATION) { twoFourModeNormalKills + twoFourModeFinalKills }

    val twoFourModeTotalDeaths: Int by lazy(PUBLICATION) { twoFourModeNormalDeaths + twoFourModeFinalDeaths }

    val twoFourModeTkdr: Double by lazy(PUBLICATION) { twoFourModeTotalKills.toDouble() / twoFourModeTotalDeaths }

    @SerializedName("two_four_beds_broken_bedwars")
    val twoFourModeBedsBroken: Int = 0

    @SerializedName("two_four_beds_lost_bedwars")
    val twoFourModeBedsLost: Int = 0

    val twoFourModeBblr: Double by lazy(PUBLICATION) { twoFourModeBedsBroken.toDouble() / twoFourModeBedsLost }

    private companion object {
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
    }
}
