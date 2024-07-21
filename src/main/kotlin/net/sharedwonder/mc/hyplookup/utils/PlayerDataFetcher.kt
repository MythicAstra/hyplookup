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

import java.io.IOException
import java.io.Serial
import java.net.SocketTimeoutException
import java.util.Collections
import java.util.Timer
import java.util.TimerTask
import java.util.UUID
import java.util.concurrent.Executor
import java.util.concurrent.locks.ReentrantLock
import java.util.function.Consumer
import net.sharedwonder.mc.hyplookup.HypLookup
import net.sharedwonder.mc.hyplookup.data.HypixelPlayerData
import net.sharedwonder.mc.ptbridge.utils.UUIDUtils
import org.apache.logging.log4j.LogManager

object PlayerDataFetcher {
    val cache: MutableMap<UUID, HypixelPlayerData> = Collections.synchronizedMap(object : LinkedHashMap<UUID, HypixelPlayerData>() {
        @Serial private val serialVersionUID: Long = -984844544700582720L

        override fun removeEldestEntry(eldest: Map.Entry<UUID, HypixelPlayerData>): Boolean = size > cacheLimit
    })

    private val LOGGER = LogManager.getLogger(PlayerDataFetcher::class.java)

    private val cacheLimit = HypLookup.CONFIG.playerDataCacheLimit

    private val fetching = Collections.synchronizedSet(HashSet<UUID>())

    private val throttlingLock = ReentrantLock()

    private val throttlingEnded = throttlingLock.newCondition()

    private var throttling = false

    @JvmStatic
    fun fetch(uuid: UUID, name: String, attempts: Int): HypixelPlayerData? {
        cache[uuid]?.also { return it }

        for (counter in 0..<attempts) {
            val (error, data) = fetch(uuid, name)
            if (data != null) {
                return data
            } else if (error) {
                return null
            }
        }
        return null
    }

    @JvmStatic
    fun fetch(uuid: UUID, name: String, executor: Executor, alwaysRunConsumerByExecutor: Boolean = false, consumer: Consumer<HypixelPlayerData>) {
        if (!alwaysRunConsumerByExecutor) {
            cache[uuid]?.also {
                runConsumer(consumer, it, uuid, name)
                return
            }
        }

        if (fetching.contains(uuid)) {
            return
        }
        fetching.add(uuid)

        executor.execute {
            try {
                if (alwaysRunConsumerByExecutor) {
                    cache[uuid]?.also {
                        runConsumer(consumer, it, uuid, name)
                        return@execute
                    }
                }

                while (!Thread.currentThread().isInterrupted) {
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
                    val (error, data) = fetch(uuid, name)
                    if (data != null) {
                        runConsumer(consumer, data, uuid, name)
                        break
                    } else if (error) {
                        return@execute
                    }
                }
            } finally {
                fetching.remove(uuid)
            }
        }
    }

    private fun fetch(uuid: UUID, name: String): Pair<Boolean, HypixelPlayerData?> {
        try {
            return Pair(false, HypixelAPI.fetchPlayerData(uuid).also { cache[uuid] = it })
        } catch (exception: SocketTimeoutException) {
            LOGGER.warn("Timeout when fetching Hypixel player data: $name/${UUIDUtils.uuidToString(uuid)}")
        } catch (exception: IOException) {
            LOGGER.warn("An IO exception thrown while fetching Hypixel player data: $name/${UUIDUtils.uuidToString(uuid)}", exception)
        } catch (exception: HypixelAPI.ThrottlingException) {
            if (!throttling) {
                synchronized(this) {
                    if (throttling) {
                        return Pair(true, null)
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
                }, 300000L)
                LOGGER.warn("Hypixel API rate limit exceeded")
            }
            return Pair(true, null)
        } catch (exception: Throwable) {
            LOGGER.error("An error occurred while fetching Hypixel player data: $name/${UUIDUtils.uuidToString(uuid)}", exception)
            return Pair(true, null)
        }
        return Pair(false, null)
    }

    private fun runConsumer(consumer: Consumer<HypixelPlayerData>, data: HypixelPlayerData, uuid: UUID, name: String) {
        try {
            consumer.accept(data)
        } catch (exception: Throwable) {
            LOGGER.error("An error occurred while consuming Hypixel player data: $name/${UUIDUtils.uuidToString(uuid)}", exception)
        }
    }
}
