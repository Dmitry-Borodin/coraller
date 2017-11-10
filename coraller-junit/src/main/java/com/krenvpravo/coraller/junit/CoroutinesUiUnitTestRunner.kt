package com.krenvpravo.coraller.junit

import com.krenvpravo.coraller.core.CoroutineUiRunner
import kotlinx.coroutines.experimental.CompletionHandler

import kotlinx.coroutines.experimental.DisposableHandle
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.SelectInstance
import kotlin.coroutines.experimental.CoroutineContext

class CoroutinesUiUnitTestRunner : CoroutineUiRunner {
    override fun <T> onUiThread(block: suspend () -> T): Job {
        runBlocking {
            block()
        }
        return TestFinishedJob()
    }
}

internal class TestFinishedJob : Job {
    override val isActive: Boolean
        get() = false
    override val isCancelled: Boolean
        get() = false
    override val isCompleted: Boolean
        get() = true
    override val key: CoroutineContext.Key<*> = Job.Key

    override fun getCompletionException(): Throwable {
        return IllegalStateException("not implemented")
    }

    override fun invokeOnCompletion(handler: CompletionHandler): DisposableHandle {
        throw IllegalStateException("not implemented")
    }

    override fun invokeOnCompletion(handler: CompletionHandler, onCancelling: Boolean): DisposableHandle {
        throw IllegalStateException("not implemented")
    }

    override fun start(): Boolean {
        throw IllegalStateException("not implemented")
    }

    override fun cancel(cause: Throwable?): Boolean = false // do nothing

    suspend override fun join() {
        throw IllegalStateException("not implemented")
    }

    override fun <R> registerSelectJoin(select: SelectInstance<R>, block: suspend () -> R) {
        throw IllegalStateException("not implemented")
    }
}