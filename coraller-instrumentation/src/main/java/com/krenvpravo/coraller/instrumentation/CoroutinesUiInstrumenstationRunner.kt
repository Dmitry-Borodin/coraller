package com.krenvpravo.coraller.instrumentation

import com.krenvpravo.coraller.android.UI
import com.krenvpravo.coraller.core.CoroutineUiRunner
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

class CoroutinesUiInstrumenstationRunner : CoroutineUiRunner {
    override fun <T> onUiThread(block: suspend () -> T): Job {
        EspressoIdlingResource.increment()
        return launch(UI) {
            block()
            EspressoIdlingResource.decrement()
        }
    }
}