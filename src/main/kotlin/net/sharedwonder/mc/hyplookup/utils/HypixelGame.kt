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

import net.sharedwonder.mc.hyplookup.query.RealPlayerData
import net.sharedwonder.mc.ptbridge.utils.FormattedText
import net.sharedwonder.mc.ptbridge.utils.FormattedText.AQUA
import net.sharedwonder.mc.ptbridge.utils.FormattedText.BLACK
import net.sharedwonder.mc.ptbridge.utils.FormattedText.BLUE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_GRAY
import net.sharedwonder.mc.ptbridge.utils.FormattedText.DARK_RED
import net.sharedwonder.mc.ptbridge.utils.FormattedText.GOLD
import net.sharedwonder.mc.ptbridge.utils.FormattedText.GRAY
import net.sharedwonder.mc.ptbridge.utils.FormattedText.GREEN
import net.sharedwonder.mc.ptbridge.utils.FormattedText.LIGHT_PURPLE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.RED
import net.sharedwonder.mc.ptbridge.utils.FormattedText.WHITE
import net.sharedwonder.mc.ptbridge.utils.FormattedText.YELLOW
import java.text.DecimalFormat

enum class HypixelGame(val gameName: String) {
    BED_WARS("Bed Wars") {
        override fun queryStatsMessage(data: RealPlayerData, modifier: String?): String = data.stats.bedWars.run {
            "${YELLOW}Level: $formattedLevelWithoutBrackets\n" +
                "${YELLOW}Coins: $GREEN$coins\n\n" +
                "${RED}Normal Modes:\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$normalModesPlayed\n" +
                " $DARK_GRAY- ${YELLOW}Normal Kills/Deaths: $GREEN$normalModesNormalKills/$normalModesNormalDeaths $AQUA(${DECIMAL_FORMAT_1.format(normalModesNkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Final Kills/Deaths: $GREEN$normalModesFinalKills/$normalModesFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(normalModesFkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Total Kills/Deaths: $GREEN$normalModesTotalKills/$normalModesTotalDeaths $AQUA(${DECIMAL_FORMAT_1.format(normalModesTkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Beds Broken/Lost: $GREEN$normalModesBedsBroken/$normalModesBedsLost $AQUA(${DECIMAL_FORMAT_1.format(normalModesBblr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$normalModesWins/$normalModesLosses $AQUA(${DECIMAL_FORMAT_1.format(normalModesWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(normalModesWinRate)}\n\n" +
                "${RED}Core Modes:\n" +
                " $DARK_GRAY- ${YELLOW}Win-streak: $GREEN${coreModesWinStreak ?: UNKNOWN}\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$coreModesPlayed\n" +
                " $DARK_GRAY- ${YELLOW}Normal Kills/Deaths: $GREEN$coreModesNormalKills/$coreModesNormalDeaths $AQUA(${DECIMAL_FORMAT_1.format(coreModesNkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Final Kills/Deaths: $GREEN$coreModesFinalKills/$coreModesFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(coreModesFkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Total Kills/Deaths: $GREEN$coreModesTotalKills/$coreModesTotalDeaths $AQUA(${DECIMAL_FORMAT_1.format(coreModesTkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Beds Broken/Lost: $GREEN$coreModesBedsBroken/$coreModesBedsLost $AQUA(${DECIMAL_FORMAT_1.format(coreModesBblr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$coreModesWins/$coreModesLosses $AQUA(${DECIMAL_FORMAT_1.format(coreModesWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(coreModesWinRate)}\n\n" +
                "${RED}Solo Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Win-streak: $GREEN${eightOneModeWinStreak ?: UNKNOWN}\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$eightOneModePlayed\n" +
                " $DARK_GRAY- ${YELLOW}Normal Kills/Deaths: $GREEN$eightOneModeNormalKills/$eightOneModeNormalDeaths $AQUA(${DECIMAL_FORMAT_1.format(eightOneModeNkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Final Kills/Deaths: $GREEN$eightOneModeFinalKills/$eightOneModeFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(eightOneModeFkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Total Kills/Deaths: $GREEN$eightOneModeTotalKills/$eightOneModeTotalDeaths $AQUA(${DECIMAL_FORMAT_1.format(eightOneModeTkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Beds Broken/Lost: $GREEN$eightOneModeBedsBroken/$eightOneModeBedsLost $AQUA(${DECIMAL_FORMAT_1.format(eightOneModeBblr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$eightOneModeWins/$eightOneModeLosses $AQUA(${DECIMAL_FORMAT_1.format(eightOneModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(eightOneModeWinRate)}\n\n" +
                "${RED}Doubles Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Win-streak: $GREEN${eightTwoModeWinStreak ?: UNKNOWN}\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$eightTwoModePlayed\n" +
                " $DARK_GRAY- ${YELLOW}Normal Kills/Deaths: $GREEN$eightTwoModeNormalKills/$eightTwoModeNormalDeaths $AQUA(${DECIMAL_FORMAT_1.format(eightTwoModeNkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Final Kills/Deaths: $GREEN$eightTwoModeFinalKills/$eightTwoModeFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(eightTwoModeFkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Total Kills/Deaths: $GREEN$eightTwoModeTotalKills/$eightTwoModeTotalDeaths $AQUA(${DECIMAL_FORMAT_1.format(eightTwoModeTkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Beds Broken/Lost: $GREEN$eightTwoModeBedsBroken/$eightTwoModeBedsLost $AQUA(${DECIMAL_FORMAT_1.format(eightTwoModeBblr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$eightTwoModeWins/$eightTwoModeLosses $AQUA(${DECIMAL_FORMAT_1.format(eightTwoModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(eightTwoModeWinRate)}\n\n" +
                "${RED}3v3v3v3 Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Win-streak: $GREEN${fourThreeModeWinStreak ?: UNKNOWN}\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$fourThreeModePlayed\n" +
                " $DARK_GRAY- ${YELLOW}Normal Kills/Deaths: $GREEN$fourThreeModeNormalKills/$fourThreeModeNormalDeaths $AQUA(${DECIMAL_FORMAT_1.format(fourThreeModeNkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Final Kills/Deaths: $GREEN$fourThreeModeFinalKills/$fourThreeModeFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(fourThreeModeFkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Total Kills/Deaths: $GREEN$fourThreeModeTotalKills/$fourThreeModeTotalDeaths $AQUA(${DECIMAL_FORMAT_1.format(fourThreeModeTkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Beds Broken/Lost: $GREEN$fourThreeModeBedsBroken/$fourThreeModeBedsLost $AQUA(${DECIMAL_FORMAT_1.format(fourThreeModeBblr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$fourThreeModeWins/$fourThreeModeLosses $AQUA(${DECIMAL_FORMAT_1.format(fourThreeModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(fourThreeModeWinRate)}\n\n" +
                "${RED}4v4v4v4 Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Win-streak: $GREEN${fourFourModeWinStreak ?: UNKNOWN}\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$fourFourModePlayed\n" +
                " $DARK_GRAY- ${YELLOW}Normal Kills/Deaths: $GREEN$fourFourModeNormalKills/$fourFourModeNormalDeaths $AQUA(${DECIMAL_FORMAT_1.format(fourFourModeNkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Final Kills/Deaths: $GREEN$fourFourModeFinalKills/$fourFourModeFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(fourFourModeFkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Total Kills/Deaths: $GREEN$fourFourModeTotalKills/$fourFourModeTotalDeaths $AQUA(${DECIMAL_FORMAT_1.format(fourFourModeTkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Beds Broken/Lost: $GREEN$fourFourModeBedsBroken/$fourFourModeBedsLost $AQUA(${DECIMAL_FORMAT_1.format(fourFourModeBblr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$fourFourModeWins/$fourFourModeLosses $AQUA(${DECIMAL_FORMAT_1.format(fourFourModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(fourFourModeWinRate)}\n\n" +
                "${RED}4v4 Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Win-streak: $GREEN${twoFourModeWinStreak ?: UNKNOWN}\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$twoFourModePlayed\n" +
                " $DARK_GRAY- ${YELLOW}Normal Kills/Deaths: $GREEN$twoFourModeFinalKills/$twoFourModeFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(twoFourModeNkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Final Kills/Deaths: $GREEN$twoFourModeFinalKills/$twoFourModeFinalDeaths $AQUA(${DECIMAL_FORMAT_1.format(twoFourModeFkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Total Kills/Deaths: $GREEN$twoFourModeTotalKills/$twoFourModeTotalDeaths $AQUA(${DECIMAL_FORMAT_1.format(twoFourModeTkdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Beds Broken/Lost: $GREEN$twoFourModeBedsBroken/$twoFourModeBedsLost $AQUA(${DECIMAL_FORMAT_1.format(twoFourModeBblr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$twoFourModeWins/$twoFourModeLosses $AQUA(${DECIMAL_FORMAT_1.format(twoFourModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(twoFourModeWinRate)}\n"
        }

        override fun displayText(data: RealPlayerData, originalText: String): Array<String> = data.stats.bedWars.run {
            arrayOf(
                "$formattedLevel $originalText",
                coreModesWinStreak?.let { getNumberColor(it, 5, 10, 20, 35, 50, 75, 100) + it } ?: UNKNOWN,
                getNumberColor(normalModesFkdr, 1.0, 5.0, 10.0, 25.0, 50.0, 75.0, 100.0) + FormattedText.BOLD + DECIMAL_FORMAT_1.format(normalModesFkdr),
                getNumberColor(normalModesFinalKills, 1000, 3500, 7500, 10000, 15000, 20000, 30000) + normalModesFinalKills,
                getNumberColor(normalModesWinRate, 0.25, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9) + FormattedText.BOLD + DECIMAL_FORMAT_2.format(normalModesWinRate),
                getNumberColor(normalModesWins, 250, 500, 1000, 2000, 4000, 8000, 10000) + normalModesWins
            )
        }
    },

    SKY_WARS("Sky Wars") {
        override fun queryStatsMessage(data: RealPlayerData, modifier: String?): String = data.stats.skyWars.run {
            "${YELLOW}Level: $formattedLevelWithoutBrackets\n" +
                "${YELLOW}Coins: $coins\n\n" +
                "${RED}All Modes:\n" +
                " $DARK_GRAY- ${YELLOW}Games Played: $GREEN$allModesPlayed\n\n" +
                "${RED}Main Modes:\n" +
                " $DARK_GRAY- ${YELLOW}Kills/Deaths: $GREEN$mainModesKills/$mainModesDeaths $AQUA(${DECIMAL_FORMAT_1.format(mainModesKdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$mainModesWins/$mainModesLosses $AQUA(${DECIMAL_FORMAT_1.format(mainModesWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(mainModesWinRate)}\n\n" +
                "${RED}Solo Normal Modes:\n" +
                " $DARK_GRAY- ${YELLOW}Kills/Deaths: $GREEN$soloNormalModeKills/$soloNormalModeDeaths $AQUA(${DECIMAL_FORMAT_1.format(soloNormalModeKdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$soloNormalModeWins/$soloNormalModeLosses $AQUA(${DECIMAL_FORMAT_1.format(soloNormalModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(soloNormalModeWinRate)}\n\n" +
                "${RED}Solo Insane Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Kills/Deaths: $GREEN$soloInsaneModeKills/$soloInsaneModeDeaths $AQUA(${DECIMAL_FORMAT_1.format(soloInsaneModeKdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$soloInsaneModeWins/$soloInsaneModeLosses $AQUA(${DECIMAL_FORMAT_1.format(soloInsaneModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(soloInsaneModeWinRate)}\n\n" +
                "${RED}Doubles Normal Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Kills/Deaths: $GREEN$teamNormalModeKills/$teamNormalModeDeaths $AQUA(${DECIMAL_FORMAT_1.format(teamNormalModeKdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$teamNormalModeWins/$teamNormalModeLosses $AQUA(${DECIMAL_FORMAT_1.format(teamNormalModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(teamNormalModeWinRate)}\n\n" +
                "${RED}Doubles Insane Mode:\n" +
                " $DARK_GRAY- ${YELLOW}Kills/Deaths: $GREEN$teamInsaneModeKills/$teamInsaneModeDeaths $AQUA(${DECIMAL_FORMAT_1.format(teamInsaneModeKdr)})\n" +
                " $DARK_GRAY- ${YELLOW}Wins/Losses: $GREEN$teamInsaneModeWins/$teamInsaneModeLosses $AQUA(${DECIMAL_FORMAT_1.format(teamInsaneModeWlr)})\n" +
                " $DARK_GRAY- ${YELLOW}Win Rate: $GREEN${DECIMAL_FORMAT_2.format(teamInsaneModeWinRate)}\n"
        }

        override fun displayText(data: RealPlayerData, originalText: String): Array<String> = data.stats.skyWars.run {
            arrayOf(
                "$formattedLevel $originalText",
                mainModesWinStreak?.let { getNumberColor(it, 5, 10, 20, 35, 50, 75, 100) + it } ?: UNKNOWN,
                getNumberColor(mainModesKdr, 1.0, 5.0, 10.0, 25.0, 50.0, 75.0, 100.0) + FormattedText.BOLD + DECIMAL_FORMAT_1.format(mainModesKdr),
                getNumberColor(mainModesKills, 1000, 3500, 7500, 10000, 25000, 30000, 50000) + mainModesKills,
                getNumberColor(mainModesWinRate, 0.1, 0.2, 0.3, 0.4, 0.5, 0.65, 0.8) + FormattedText.BOLD + DECIMAL_FORMAT_2.format(mainModesWinRate),
                getNumberColor(mainModesWins, 100, 500, 1000, 2000, 3000, 6000, 8000) + mainModesWins
            )
        }
    };

    abstract fun queryStatsMessage(data: RealPlayerData, modifier: String?): String

    abstract fun displayText(data: RealPlayerData, originalText: String): Array<String>

    companion object {
        private const val UNKNOWN = "$DARK_RED?"

        private val NAME_MAPPINGS: Map<String, HypixelGame> = mapOf(
            "bw" to BED_WARS,
            "bedwars" to BED_WARS,
            "bed_wars" to BED_WARS,
            "sw" to SKY_WARS,
            "skywars" to SKY_WARS,
            "sky_wars" to SKY_WARS
        )

        private val SCOREBOARD_TITLE_MAPPINGS: Map<String, HypixelGame> = mapOf(
            "BED WARS" to BED_WARS,
            "SKYWARS" to SKY_WARS
        )

        private val DECIMAL_FORMAT_1 = DecimalFormat("0.00")

        private val DECIMAL_FORMAT_2 = DecimalFormat("0.0%")

        @JvmStatic
        fun getByName(name: String): HypixelGame? = NAME_MAPPINGS[name]

        @JvmStatic
        fun getByScoreboardTitle(scoreboardTitle: String): HypixelGame? = SCOREBOARD_TITLE_MAPPINGS[scoreboardTitle]

        private fun getNumberColor(number: Number, t1: Number, t2: Number, t3: Number, t4: Number, t5: Number, t6: Number, t7: Number): String {
            if (number.javaClass == java.lang.Float::class.java || number.javaClass == java.lang.Double::class.java) {
                val doubleNumber = number.toDouble()
                return when {
                    doubleNumber.isNaN() -> DARK_RED
                    doubleNumber.isInfinite() -> BLACK
                    doubleNumber < t1.toDouble() -> GRAY
                    doubleNumber < t2.toDouble() -> WHITE
                    doubleNumber < t3.toDouble() -> GREEN
                    doubleNumber < t4.toDouble() -> BLUE
                    doubleNumber < t5.toDouble() -> YELLOW
                    doubleNumber < t6.toDouble() -> RED
                    doubleNumber < t7.toDouble() -> LIGHT_PURPLE
                    else -> GOLD
                }
            } else {
                val longNumber = number.toLong()
                return when {
                    longNumber < t1.toLong() -> GRAY
                    longNumber < t2.toLong() -> WHITE
                    longNumber < t3.toLong() -> GREEN
                    longNumber < t4.toLong() -> BLUE
                    longNumber < t5.toLong() -> YELLOW
                    longNumber < t6.toLong() -> RED
                    longNumber < t7.toLong() -> LIGHT_PURPLE
                    else -> GOLD
                }
            }
        }
    }
}
