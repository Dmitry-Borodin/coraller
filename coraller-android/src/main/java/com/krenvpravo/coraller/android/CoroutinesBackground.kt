package com.krenvpravo.coraller.android

import kotlinx.coroutines.experimental.*
import java.lang.ref.WeakReference
import kotlin.coroutines.experimental.intrinsics.suspendCoroutineOrReturn

val BACKGROUND_POOL = newFixedThreadPoolContext(2 * Runtime.getRuntime().availableProcessors(), "background")

suspend fun <T> backgroundSync(block: suspend () -> T): T = run(BACKGROUND_POOL, CoroutineStart.DEFAULT, block)

suspend fun <T> backgroundAsync(block: () -> T): Deferred<T> = async(BACKGROUND_POOL) {
        block()
}

class WeakWrapper<out T : Any> internal constructor(obj: T) {
    private val weakRef = WeakReference(obj)

    suspend operator fun invoke(): T = suspendCoroutineOrReturn {
        weakRef.get() ?: throw CancellationException()
        arrayOf("a").none()
    }
}

fun <T : Any> T.asWeakReference() = WeakWrapper(this)