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

import kotlin.LazyThreadSafetyMode.PUBLICATION
import com.google.gson.annotations.SerializedName

class PlayerSkyWarsStats {
    val formattedLevel: String by lazy(PUBLICATION) { "${formattedLevelWithoutBrackets.take(2)}[${formattedLevelWithoutBrackets.substring(2)}]" }

    val formattedLevelWithoutOrnament: String by lazy(PUBLICATION) { formattedLevel.take(formattedLevel.length - 2) + ']' }

    @SerializedName("levelFormatted")
    val formattedLevelWithoutBrackets: String = "§f1⋆"

    val formattedLevelSimple: String by lazy(PUBLICATION) { formattedLevelWithoutOrnament.take(formattedLevelWithoutOrnament.lastIndex) }

    val coins: Int = 0

    @SerializedName("games_played_skywars")
    val allModesPlayed: Int = 0

    @SerializedName("win_streak")
    val mainModesWinStreak: Int? = null

    @SerializedName("kills")
    val mainModesKills: Int = 0

    @SerializedName("deaths")
    val mainModesDeaths: Int = 0

    val mainModesKdr: Double by lazy(PUBLICATION) { mainModesKills.toDouble() / mainModesDeaths }

    @SerializedName("wins")
    val mainModesWins: Int = 0

    @SerializedName("losses")
    val mainModesLosses: Int = 0

    val mainModesWlr: Double by lazy(PUBLICATION) { mainModesWins.toDouble() / mainModesLosses }

    val mainModesWinRate: Double by lazy(PUBLICATION) { mainModesWins.toDouble() / (mainModesWins + mainModesLosses) }

    @SerializedName("kills_solo_normal")
    val soloNormalModeKills: Int = 0

    @SerializedName("deaths_solo_normal")
    val soloNormalModeDeaths: Int = 0

    val soloNormalModeKdr: Double by lazy(PUBLICATION) { soloNormalModeKills.toDouble() / soloNormalModeDeaths }

    @SerializedName("wins_solo_normal")
    val soloNormalModeWins: Int = 0

    @SerializedName("losses_solo_normal")
    val soloNormalModeLosses: Int = 0

    val soloNormalModeWlr: Double by lazy(PUBLICATION) { soloNormalModeWins.toDouble() / soloNormalModeLosses }

    val soloNormalModeWinRate: Double by lazy(PUBLICATION) { soloNormalModeWins.toDouble() / (soloNormalModeWins + soloNormalModeLosses) }

    @SerializedName("kills_solo_insane")
    val soloInsaneModeKills: Int = 0

    @SerializedName("deaths_solo_insane")
    val soloInsaneModeDeaths: Int = 0

    val soloInsaneModeKdr: Double by lazy(PUBLICATION) { soloInsaneModeKills.toDouble() / soloInsaneModeDeaths }

    @SerializedName("wins_solo_insane")
    val soloInsaneModeWins: Int = 0

    @SerializedName("losses_solo_insane")
    val soloInsaneModeLosses: Int = 0

    val soloInsaneModeWlr: Double by lazy(PUBLICATION) { soloInsaneModeWins.toDouble() / soloInsaneModeLosses }

    val soloInsaneModeWinRate: Double by lazy(PUBLICATION) { soloInsaneModeWins.toDouble() / (soloInsaneModeWins + soloInsaneModeLosses) }

    @SerializedName("kills_team_normal")
    val teamNormalModeKills: Int = 0

    @SerializedName("deaths_team_normal")
    val teamNormalModeDeaths: Int = 0

    val teamNormalModeKdr: Double by lazy(PUBLICATION) { teamNormalModeKills.toDouble() / teamNormalModeDeaths }

    @SerializedName("wins_team_normal")
    val teamNormalModeWins: Int = 0

    @SerializedName("losses_team_normal")
    val teamNormalModeLosses: Int = 0

    val teamNormalModeWlr: Double by lazy(PUBLICATION) { teamNormalModeWins.toDouble() / teamNormalModeLosses }

    val teamNormalModeWinRate: Double by lazy(PUBLICATION) { teamNormalModeWins.toDouble() / (teamNormalModeWins + teamNormalModeLosses) }

    @SerializedName("kills_team_insane")
    val teamInsaneModeKills: Int = 0

    @SerializedName("deaths_team_insane")
    val teamInsaneModeDeaths: Int = 0

    val teamInsaneModeKdr: Double by lazy(PUBLICATION) { teamInsaneModeKills.toDouble() / teamInsaneModeDeaths }

    @SerializedName("wins_team_insane")
    val teamInsaneModeWins: Int = 0

    @SerializedName("losses_team_insane")
    val teamInsaneModeLosses: Int = 0

    val teamInsaneModeWlr: Double by lazy(PUBLICATION) { teamInsaneModeWins.toDouble() / teamInsaneModeLosses }

    val teamInsaneModeWinRate: Double by lazy(PUBLICATION) { teamInsaneModeWins.toDouble() / (teamInsaneModeWins + teamInsaneModeLosses) }
}
