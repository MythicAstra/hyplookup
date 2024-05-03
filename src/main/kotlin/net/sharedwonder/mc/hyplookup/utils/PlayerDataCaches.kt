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

import kotlin.concurrent.thread
import java.io.IOException
import java.io.Serial
import java.net.SocketTimeoutException
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import net.sharedwonder.mc.hyplookup.query.HypixelAPI
import net.sharedwonder.mc.hyplookup.query.HypixelPlayerData
import net.sharedwonder.mc.ptbridge.utils.UUIDUtils
import org.apache.logging.log4j.LogManager

object PlayerDataCaches {
    private const val DEFAULT_CACHE_SIZE_LIMIT = 100

    private val LOGGER = LogManager.getLogger(PlayerDataCaches::class.java)

    private val queryStatsThreads = ConcurrentHashMap<String, Thread>()

    private val caches = object : LinkedHashMap<UUID, HypixelPlayerData>() {
        @Serial private val serialVersionUID: Long = -984844544700582720L

        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<UUID, HypixelPlayerData>): Boolean = size > DEFAULT_CACHE_SIZE_LIMIT
    }

    fun getCachedPlayerData(playerUuid: UUID): HypixelPlayerData? = synchronized(this) { caches[playerUuid] }

    fun queryPlayerData(playerName: String, playerUuid: UUID, attempts: Int): HypixelPlayerData? {
        for (counter in 0..<attempts) {
            try {
                return HypixelAPI.queryPlayerData(playerUuid).also { addToCache(playerUuid, it) }
            } catch (exception: SocketTimeoutException) {
                logTimeout(playerName, playerUuid)
            } catch (exception: IOException) {
                logIoException(playerName, playerUuid, exception)
            } catch (exception: Throwable) {
                logError(playerName, playerUuid, exception)
                return null
            }
        }
        return null
    }

    fun queryPlayerDataInThread(playerName: String, playerUuid: UUID) {
        synchronized(this) {
            if (caches.containsKey(playerUuid)) {
                return
            }
        }

        queryStatsThreads.computeIfAbsent(playerName) {
            thread(name = "HYPL-QueryPlayerData-${queryStatsThreads.size}") {
                while (true) {
                    if (Thread.currentThread().isInterrupted) {
                        return@thread
                    }
                    try {
                        addToCache(playerUuid, HypixelAPI.queryPlayerData(playerUuid))
                        return@thread
                    } catch (exception: SocketTimeoutException) {
                        logTimeout(playerName, playerUuid)
                    } catch (exception: IOException) {
                        logIoException(playerName, playerUuid, exception)
                    } catch (exception: Throwable) {
                        logError(playerName, playerUuid, exception)
                        return@thread
                    } finally {
                        queryStatsThreads.remove(playerName)
                    }
                }
            }
        }
    }

    fun interruptQueryingThread(playerName: String) {
        queryStatsThreads[playerName]?.interrupt()
    }

    fun stopAllQueryingThreads() {
        for (thread in queryStatsThreads.values) {
            thread.interrupt()
        }
        for (thread in queryStatsThreads.values) {
            try {
                thread.join()
            } catch (exception: InterruptedException) {
                throw RuntimeException(exception)
            }
        }
        queryStatsThreads.clear()
    }

    fun clearCaches() {
        synchronized(this) {
            caches.clear()
        }
    }

    private fun addToCache(playerUuid: UUID, data: HypixelPlayerData) {
        synchronized(this) {
            caches[playerUuid] = data
        }
    }

    private fun logTimeout(playerName: String, playerUuid: UUID) {
        LOGGER.warn("Timeout when getting Hypixel player data: $playerName/${UUIDUtils.uuidToString(playerUuid)}")
    }

    private fun logIoException(playerName: String, playerUuid: UUID, exception: IOException) {
        LOGGER.warn("An IO exception thrown while querying Hypixel player data: $playerName/${UUIDUtils.uuidToString(playerUuid)}", exception)
    }

    private fun logError(playerName: String, playerUuid: UUID, exception: Throwable) {
        LOGGER.error("An error occurred while querying Hypixel player data: $playerName/${UUIDUtils.uuidToString(playerUuid)}", exception)
    }
}
