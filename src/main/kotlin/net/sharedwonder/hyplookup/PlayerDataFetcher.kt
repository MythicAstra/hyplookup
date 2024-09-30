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

package net.sharedwonder.hyplookup

import java.io.IOException
import java.net.SocketTimeoutException
import java.util.Collections
import java.util.Timer
import java.util.TimerTask
import java.util.UUID
import java.util.concurrent.Executor
import java.util.concurrent.locks.ReentrantLock
import java.util.function.Consumer
import net.sharedwonder.hyplookup.data.Player
import net.sharedwonder.hyplookup.util.HypixelAPI
import net.sharedwonder.lightproxy.util.UuidUtils
import org.apache.logging.log4j.LogManager

object PlayerDataFetcher {
    private val logger = LogManager.getLogger(PlayerDataFetcher::class.java)

    @Suppress("serial")
    private val cache = Collections.synchronizedMap(object : LinkedHashMap<UUID, Player>() {
        override fun removeEldestEntry(eldest: Map.Entry<UUID, Player>): Boolean = size > HypLookup.CONFIG.playerDataCacheLimit
    })

    private val fetching = HashSet<UUID>()

    private val throttlingLock = ReentrantLock()

    private val throttlingEnded = throttlingLock.newCondition()

    private var throttling = false

    @JvmStatic
    fun fetch(uuid: UUID, name: String): Player? {
        cache[uuid]?.let { return it }

        return fetchImpl(uuid, name).first
    }

    @JvmStatic
    fun fetch(uuid: UUID, name: String, executor: Executor, consumer: Consumer<Player>) {
        if (cache.containsKey(uuid)) {
            return
        }

        synchronized(fetching) {
            if (fetching.contains(uuid)) {
                return
            }
            fetching.add(uuid)
        }

        executor.execute {
            try {
                while (true) {
                    if (throttling) {
                        throttlingLock.lock()
                        try {
                            if (throttling) {
                                throttlingEnded.await()
                            }
                        } catch (exception: InterruptedException) {
                            return@execute
                        } finally {
                            throttlingLock.unlock()
                        }
                    }
                    val (data, retry) = fetchImpl(uuid, name)
                    if (data != null) {
                        try {
                            consumer.accept(data)
                        } catch (exception: Throwable) {
                            logger.error("An error occurred while consuming Hypixel player data: $name/${UuidUtils.uuidToString(uuid)}", exception)
                        }
                        return@execute
                    }
                    if (!retry || Thread.currentThread().isInterrupted) {
                        return@execute
                    }
                }
            } finally {
                synchronized(fetching) {
                    fetching.remove(uuid)
                }
            }
        }
    }

    @JvmStatic
    fun hasCached(uuid: UUID): Boolean = cache.containsKey(uuid)

    @JvmStatic
    fun getCached(uuid: UUID): Player? = cache[uuid]

    @JvmStatic
    fun clearCache() {
        cache.clear()
    }

    private fun fetchImpl(uuid: UUID, name: String): Pair<Player?, Boolean> {
        try {
            return Pair(HypixelAPI.fetchPlayerData(uuid).also { cache[uuid] = it }, false)
        } catch (exception: SocketTimeoutException) {
            logger.warn("Timeout when fetching Hypixel player data: $name/${UuidUtils.uuidToString(uuid)}")
            return Pair(null, true)
        } catch (exception: IOException) {
            logger.warn("An IO exception thrown while fetching Hypixel player data: $name/${UuidUtils.uuidToString(uuid)}", exception)
            return Pair(null, true)
        } catch (exception: InterruptedException) {
            return Pair(null, false)
        } catch (exception: HypixelAPI.ThrottlingException) {
            throttle()
            return Pair(null, false)
        } catch (exception: Throwable) {
            logger.error("An error occurred while fetching Hypixel player data: $name/${UuidUtils.uuidToString(uuid)}", exception)
            return Pair(null, false)
        }
    }

    private fun throttle() {
        if (!throttling) {
            synchronized(this) {
                if (throttling) {
                    return
                }
                throttling = true
            }

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    throttling = false
                    throttlingLock.lock()
                    try {
                        throttlingEnded.signalAll()
                    } finally {
                        throttlingLock.unlock()
                    }
                }
            }, HypLookup.CONFIG.hypixelApiThrottlingTimeout)
            logger.warn("Hypixel API rate limit exceeded")
        }
    }
}
