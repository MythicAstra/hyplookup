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

import net.sharedwonder.mc.hyplookup.command.CommandException
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
            line("Level", `level-formatted-without-brackets`) +
                line("Tokens", coins) +
                when (modifier) {
                    "solo", "1s" -> group("Solo Mode") +
                        entry("Win-streak", `solo-mode win-streak` ?: "?") +
                        entry("Games Played", `solo-mode games-played`) +
                        entry("Wins/Losses", "$`solo-mode wins`/$`solo-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`solo-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`solo-mode win-rate`)) +
                        entry("Normal Kills/Deaths", "$`solo-mode normal-kills`/$`solo-mode normal-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`solo-mode normal-kdr`)})") +
                        entry("Final Kills/Deaths", "$`solo-mode final-kills`/$`solo-mode final-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`solo-mode final-kdr`)})") +
                        entry("Total Kills/Deaths", "$`solo-mode total-kills`/$`solo-mode total-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`solo-mode total-kdr`)})") +
                        entry("Beds Broken/Lost", "$`solo-mode beds-broken`/$`solo-mode beds-lost` $AQUA(${DECIMAL_FORMAT_1.format(`solo-mode bblr`)})")

                    "doubles", "2s" -> group("Doubles Mode") +
                        entry("Win-streak", `doubles-mode win-streak` ?: "?") +
                        entry("Games Played", `doubles-mode games-played`) +
                        entry("Wins/Losses", "$`doubles-mode wins`/$`doubles-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`doubles-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`doubles-mode win-rate`)) +
                        entry("Normal Kills/Deaths", "$`doubles-mode normal-kills`/$`doubles-mode normal-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`doubles-mode normal-kdr`)})") +
                        entry("Final Kills/Deaths", "$`doubles-mode final-kills`/$`doubles-mode final-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`doubles-mode final-kdr`)})") +
                        entry("Total Kills/Deaths", "$`doubles-mode total-kills`/$`doubles-mode total-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`doubles-mode total-kdr`)})") +
                        entry("Beds Broken/Lost", "$`doubles-mode beds-broken`/$`doubles-mode beds-lost` $AQUA(${DECIMAL_FORMAT_1.format(`doubles-mode bblr`)})")

                    "3v3v3v3", "3s" -> group("3v3v3v3 Mode") +
                        entry("Win-streak", `3v3v3v3-mode win-streak` ?: "?") +
                        entry("Games Played", `3v3v3v3-mode games-played`) +
                        entry("Wins/Losses", "$`3v3v3v3-mode wins`/$`3v3v3v3-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`3v3v3v3-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`3v3v3v3-mode win-rate`)) +
                        entry("Normal Kills/Deaths", "$`3v3v3v3-mode normal-kills`/$`3v3v3v3-mode normal-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`3v3v3v3-mode normal-kdr`)})") +
                        entry("Final Kills/Deaths", "$`3v3v3v3-mode final-kills`/$`3v3v3v3-mode final-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`3v3v3v3-mode final-kdr`)})") +
                        entry("Total Kills/Deaths", "$`3v3v3v3-mode total-kills`/$`3v3v3v3-mode total-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`3v3v3v3-mode total-kdr`)})") +
                        entry("Beds Broken/Lost", "$`3v3v3v3-mode beds-broken`/$`3v3v3v3-mode beds-lost` $AQUA(${DECIMAL_FORMAT_1.format(`3v3v3v3-mode bblr`)})")

                    "4v4v4v4", "4s" -> group("4v4v4v4 Mode") +
                        entry("Win-streak", `4v4v4v4-mode win-streak` ?: "?") +
                        entry("Games Played", `4v4v4v4-mode games-played`) +
                        entry("Wins/Losses", "$`4v4v4v4-mode wins`/$`4v4v4v4-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`4v4v4v4-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`4v4v4v4-mode win-rate`)) +
                        entry("Normal Kills/Deaths", "$`4v4v4v4-mode normal-kills`/$`4v4v4v4-mode normal-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`4v4v4v4-mode normal-kdr`)})") +
                        entry("Final Kills/Deaths", "$`4v4v4v4-mode final-kills`/$`4v4v4v4-mode final-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`4v4v4v4-mode final-kdr`)})") +
                        entry("Total Kills/Deaths", "$`4v4v4v4-mode total-kills`/$`4v4v4v4-mode total-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`4v4v4v4-mode total-kdr`)})") +
                        entry("Beds Broken/Lost", "$`4v4v4v4-mode beds-broken`/$`4v4v4v4-mode beds-lost` $AQUA(${DECIMAL_FORMAT_1.format(`4v4v4v4-mode bblr`)})")

                    "4v4" -> group("4v4 Mode") +
                        entry("Win-streak", `4v4-mode win-streak` ?: "?") +
                        entry("Games Played", `4v4-mode games-played`) +
                        entry("Wins/Losses", "$`4v4-mode wins`/$`4v4-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`4v4-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`4v4-mode win-rate`)) +
                        entry("Normal Kills/Deaths", "$`4v4-mode normal-kills`/$`4v4-mode normal-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`4v4-mode normal-kdr`)})") +
                        entry("Final Kills/Deaths", "$`4v4-mode final-kills`/$`4v4-mode final-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`4v4-mode final-kdr`)})") +
                        entry("Total Kills/Deaths", "$`4v4-mode total-kills`/$`4v4-mode total-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`4v4-mode total-kdr`)})") +
                        entry("Beds Broken/Lost", "$`4v4-mode beds-broken`/$`4v4-mode beds-lost` $AQUA(${DECIMAL_FORMAT_1.format(`4v4-mode bblr`)})")

                    "core" -> group("Core Modes") +
                        entry("Win-streak", `core-modes win-streak` ?: "?") +
                        entry("Games Played", `core-modes games-played`) +
                        entry("Wins/Losses", "$`core-modes wins`/$`core-modes losses` $AQUA(${DECIMAL_FORMAT_1.format(`core-modes wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`core-modes win-rate`)) +
                        entry("Normal Kills/Deaths", "$`core-modes normal-kills`/$`core-modes normal-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`core-modes normal-kdr`)})") +
                        entry("Final Kills/Deaths", "$`core-modes final-kills`/$`core-modes final-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`core-modes final-kdr`)})") +
                        entry("Total Kills/Deaths", "$`core-modes total-kills`/$`core-modes total-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`core-modes total-kdr`)})") +
                        entry("Beds Broken/Lost", "$`core-modes beds-broken`/$`core-modes beds-lost` $AQUA(${DECIMAL_FORMAT_1.format(`core-modes bblr`)})")

                    null, "normal" -> group("Normal Modes") +
                        entry("Games Played", `normal-modes games-played`) +
                        entry("Wins/Losses", "$`normal-modes wins`/$`normal-modes losses` $AQUA(${DECIMAL_FORMAT_1.format(`normal-modes wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`normal-modes win-rate`)) +
                        entry("Normal Kills/Deaths",
                            "$`normal-modes normal-kills`/$`normal-modes normal-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`normal-modes normal-kdr`)})") +
                        entry("Final Kills/Deaths", "$`normal-modes final-kills`/$`normal-modes final-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`normal-modes final-kdr`)})") +
                        entry("Total Kills/Deaths", "$`normal-modes total-kills`/$`normal-modes total-deaths` $AQUA(${DECIMAL_FORMAT_1.format(`normal-modes total-kdr`)})") +
                        entry("Beds Broken/Lost", "$`normal-modes beds-broken`/$`normal-modes beds-lost` $AQUA(${DECIMAL_FORMAT_1.format(`normal-modes bblr`)})")

                    "all" -> group("All Modes") +
                        entry("Games Played", `all-modes games-played`)

                    else -> throw CommandException("Unknown modifier: $modifier")
                }
        }

        override fun displayText(data: RealPlayerData, originalText: String): Array<String> = data.stats.bedWars.run {
            arrayOf(
                "$`level-formatted` $originalText",
                `core-modes win-streak`?.let { getNumberColor(it, 5, 10, 20, 35, 50, 75, 100) + it } ?: UNKNOWN,
                getNumberColor(`normal-modes final-kdr`, 1.0, 5.0, 10.0, 25.0, 50.0, 75.0, 100.0) + FormattedText.BOLD + DECIMAL_FORMAT_1.format(`normal-modes final-kdr`),
                getNumberColor(`normal-modes final-kills`, 1000, 3500, 7500, 10000, 15000, 20000, 30000) + `normal-modes final-kills`,
                getNumberColor(`normal-modes win-rate`, 0.25, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9) + FormattedText.BOLD + DECIMAL_FORMAT_2.format(`normal-modes win-rate`),
                getNumberColor(`normal-modes wins`, 250, 500, 1000, 2000, 4000, 8000, 10000) + `normal-modes wins`
            )
        }
    },

    SKY_WARS("Sky Wars") {
        override fun queryStatsMessage(data: RealPlayerData, modifier: String?): String = data.stats.skyWars.run {
            line("Level", `level-formatted-without-brackets`) +
                line("Coins", coins) +
                line("Souls", souls) +
                when (modifier) {
                    "solo_normal", "sn" -> group("Solo Normal Mode") +
                        entry("Games Played", `solo-normal-mode games-played`) +
                        entry("Wins/Losses", "$`solo-normal-mode wins`/$`solo-normal-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`solo-normal-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`solo-normal-mode win-rate`)) +
                        entry("Kills/Deaths", "$`solo-normal-mode kills`/$`solo-normal-mode deaths` $AQUA(${DECIMAL_FORMAT_1.format(`solo-normal-mode kdr`)})")

                    "solo_insane", "si" -> group("Solo Insane Mode") +
                        entry("Games Played", `solo-insane-mode games-played`) +
                        entry("Wins/Losses", "$`solo-insane-mode wins`/$`solo-insane-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`solo-insane-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`solo-insane-mode win-rate`)) +
                        entry("Kills/Deaths", "$`solo-insane-mode kills`/$`solo-insane-mode deaths` $AQUA(${DECIMAL_FORMAT_1.format(`solo-insane-mode kdr`)})")

                    "team_normal", "tn" -> group("Team Normal Mode") +
                        entry("Games Played", `team-normal-mode games-played`) +
                        entry("Wins/Losses", "$`team-normal-mode wins`/$`team-normal-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`team-normal-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`team-normal-mode win-rate`)) +
                        entry("Kills/Deaths", "$`team-normal-mode kills`/$`team-normal-mode deaths` $AQUA(${DECIMAL_FORMAT_1.format(`team-normal-mode kdr`)})")

                    "team_insane", "ti" -> group("Team Insane Mode") +
                        entry("Games Played", `team-insane-mode games-played`) +
                        entry("Wins/Losses", "$`team-insane-mode wins`/$`team-insane-mode losses` $AQUA(${DECIMAL_FORMAT_1.format(`team-insane-mode wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`team-insane-mode win-rate`)) +
                        entry("Kills/Deaths", "$`team-insane-mode kills`/$`team-insane-mode deaths` $AQUA(${DECIMAL_FORMAT_1.format(`team-insane-mode kdr`)})")

                    null, "core" -> group("Core Modes") +
                        entry("Win-streak", `core-modes win-streak` ?: "?") +
                        entry("Games Played", `core-modes games-played`) +
                        entry("Wins/Losses", "$`core-modes wins`/$`core-modes losses` $AQUA(${DECIMAL_FORMAT_1.format(`core-modes wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`core-modes win-rate`)) +
                        entry("Kills/Deaths", "$`core-modes kills`/$`core-modes deaths` $AQUA(${DECIMAL_FORMAT_1.format(`core-modes kdr`)})")

                    "lab" -> group("Lab Modes") +
                        entry("Win-streak", `lab-modes win-streak` ?: "?") +
                        entry("Games Played", `lab-modes games-played`) +
                        entry("Wins/Losses", "$`lab-modes wins`/$`lab-modes losses` $AQUA(${DECIMAL_FORMAT_1.format(`lab-modes wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`lab-modes win-rate`)) +
                        entry("Kills/Deaths", "$`lab-modes kills`/$`lab-modes deaths` $AQUA(${DECIMAL_FORMAT_1.format(`lab-modes kdr`)})")

                    "all" -> group("All Modes") +
                        entry("Games Played", `all-modes games-played`) +
                        entry("Wins/Losses", "$`all-modes wins`/$`all-modes losses` $AQUA(${DECIMAL_FORMAT_1.format(`all-modes wlr`)})") +
                        entry("Win Rate", DECIMAL_FORMAT_2.format(`all-modes win-rate`)) +
                        entry("Kills/Deaths", "$`all-modes kills`/$`all-modes deaths` $AQUA(${DECIMAL_FORMAT_1.format(`all-modes kdr`)})")

                    else -> throw CommandException("Unknown modifier: $modifier")
                }
        }

        override fun displayText(data: RealPlayerData, originalText: String): Array<String> = data.stats.skyWars.run {
            arrayOf(
                "$`level-formatted` $originalText",
                `core-modes win-streak`?.let { getNumberColor(it, 5, 10, 20, 35, 50, 75, 100) + it } ?: UNKNOWN,
                getNumberColor(`core-modes kdr`, 1.0, 5.0, 10.0, 25.0, 50.0, 75.0, 100.0) + FormattedText.BOLD + DECIMAL_FORMAT_1.format(`core-modes kdr`),
                getNumberColor(`core-modes kills`, 1000, 3500, 7500, 10000, 25000, 30000, 50000) + `core-modes kills`,
                getNumberColor(`core-modes win-rate`, 0.1, 0.2, 0.3, 0.4, 0.5, 0.65, 0.8) + FormattedText.BOLD + DECIMAL_FORMAT_2.format(`core-modes win-rate`),
                getNumberColor(`core-modes wins`, 100, 500, 1000, 2000, 3000, 6000, 8000) + `core-modes wins`
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

        private fun line(description: String, value: Any) = "$YELLOW$description: $GREEN$value\n"

        private fun group(title: String) = "\n$RED$title:\n"

        private fun entry(description: String, value: Any) = " $DARK_GRAY- $YELLOW$description: $GREEN$value\n"

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
