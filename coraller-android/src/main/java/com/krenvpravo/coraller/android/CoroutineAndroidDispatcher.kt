package com.krenvpravo.coraller.android

import android.os.Handler
import android.os.Looper
import com.krenvpravo.coraller.core.CoroutineUiRunner
import kotlinx.coroutines.experimental.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.CoroutineContext

val UI = CoroutineAndroidDispatcher(Handler(Looper.getMainLooper()), "UI dispatcher")

fun <T> onUiThread(block: suspend () -> T): Job = CoroutineUiDelegate.coroutineRunner.onUiThread(block)

object CoroutineUiDelegate {
    var coroutineRunner : CoroutineUiRunner = CoroutinesUiProdRunner()
}


class CoroutineAndroidDispatcher(private val handler: Handler, private val name: String? = null) : CoroutineDispatcher(), Delay {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        handler.post(block)
    }

    override fun scheduleResumeAfterDelay(time: Long, unit: TimeUnit, continuation: CancellableContinuation<Unit>) {
        handler.postDelayed({
            with(continuation) { resumeUndispatched(Unit) }
        }, unit.toMillis(time))
    }

    override fun invokeOnTimeout(time: Long, unit: TimeUnit, block: Runnable): DisposableHandle {
        handler.postDelayed(block, unit.toMillis(time))
        return object : DisposableHandle {
            override fun dispose() {
                handler.removeCallbacks(block)
            }
        }
    }

    override fun toString() = name ?: handler.toString()
    override fun equals(other: Any?): Boolean = other is CoroutineAndroidDispatcher && other.handler === handler
    override fun hashCode(): Int = System.identityHashCode(handler)
}