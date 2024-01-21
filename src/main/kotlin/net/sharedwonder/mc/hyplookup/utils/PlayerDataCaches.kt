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

import net.sharedwonder.mc.hyplookup.query.HypixelAPI
import net.sharedwonder.mc.hyplookup.query.HypixelPlayerData
import net.sharedwonder.mc.ptbridge.utils.UUIDUtils
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.apache.logging.log4j.LogManager

object PlayerDataCaches {
    private const val DEFAULT_CACHE_SIZE_LIMIT = 100

    private val LOGGER = LogManager.getLogger(PlayerDataCaches::class.java)

    private val queryStatsThreads = ConcurrentHashMap<String, Thread>()

    private val caches = LinkedHashMap<UUID, HypixelPlayerData>()

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
            Thread({
                while (true) {
                    if (Thread.currentThread().isInterrupted) {
                        return@Thread
                    }
                    try {
                        addToCache(playerUuid, HypixelAPI.queryPlayerData(playerUuid))
                        return@Thread
                    } catch (exception: SocketTimeoutException) {
                        logTimeout(playerName, playerUuid)
                    } catch (exception: IOException) {
                        logIoException(playerName, playerUuid, exception)
                    } catch (exception: Throwable) {
                        logError(playerName, playerUuid, exception)
                        return@Thread
                    } finally {
                        queryStatsThreads.remove(playerName)
                    }
                }
            }, "HYPL-QueryPlayerData-${queryStatsThreads.size}").apply { start() }
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
            if (caches.size >= DEFAULT_CACHE_SIZE_LIMIT) {
                caches.iterator().apply {
                    next()
                    remove()
                }
            }
            caches[playerUuid] = data
        }
    }

    private fun logTimeout(playerName: String, playerUuid: UUID) {
        if (LOGGER.isWarnEnabled) {
            LOGGER.warn("Timeout when getting Hypixel player data: $playerName/${UUIDUtils.uuidToString(playerUuid)}")
        }
    }

    private fun logIoException(playerName: String, playerUuid: UUID, exception: IOException) {
        if (LOGGER.isWarnEnabled) {
            LOGGER.warn("An IO exception thrown while querying Hypixel player data: $playerName/${UUIDUtils.uuidToString(playerUuid)}", exception)
        }
    }

    private fun logError(playerName: String, playerUuid: UUID, exception: Throwable) {
        if (LOGGER.isErrorEnabled) {
            LOGGER.error("An error occurred while querying Hypixel player data: $playerName/${UUIDUtils.uuidToString(playerUuid)}", exception)
        }
    }
}
