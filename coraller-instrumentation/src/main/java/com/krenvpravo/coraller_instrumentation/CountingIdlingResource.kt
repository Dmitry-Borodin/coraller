package com.krenvpravo.coraller_instrumentation

import android.support.test.espresso.IdlingResource

import java.util.concurrent.atomic.AtomicInteger

object EspressoIdlingResource {
    private const val GLOBAL_TAG = "GLOBAL"

    private val countingIdlingResource = CountingIdlingResource(GLOBAL_TAG)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource = countingIdlingResource
}

class CountingIdlingResource(tag: String) : IdlingResource {

    private val resourceName: String = tag

    private val counter = AtomicInteger(0)

    @Volatile private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = resourceName

    override fun isIdleNow(): Boolean = counter.get() == 0

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
            if (null != resourceCallback) {
                resourceCallback?.onTransitionToIdle()
            }
        }

        if (counterVal < 0) {
            throw IllegalArgumentException("Counter has been corrupted!")
        }
    }
}
