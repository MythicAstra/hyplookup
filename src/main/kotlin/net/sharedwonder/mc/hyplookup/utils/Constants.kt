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

@file:JvmName("Constants")

package net.sharedwonder.mc.hyplookup.utils

import net.sharedwonder.mc.ptbridge.utils.FormattedText

const val PID_CP_SEND_CHAT_MESSAGE: Int = 0x1

const val PID_SP_UPDATE_CHAT_MESSAGE: Int = 0x2

const val PID_SP_CHANGE_HELD_ITEM: Int = 0x9

const val PID_SP_UPDATE_PLAYER_LIST: Int = 0x38

const val PID_SP_SCOREBOARD_OBJECTIVE: Int = 0x3b

const val PID_SP_DISPLAY_SCOREBOARD: Int = 0x3d

const val PID_SP_UPDATE_TEAM: Int = 0x3e

const val COMMAND_PREFIX = '/'

const val CHAT_MESSAGE_ID = 1

const val HYPLOOKUP_MESSAGE_PREFIX: String = "${FormattedText.BLUE}[HypLookup]${FormattedText.RESET} "
