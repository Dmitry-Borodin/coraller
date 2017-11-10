package com.krenvpravo.coraller_instrumentation

import com.krenvpravo.coraller_android.UI
import com.krenvpravo.coraller_core.CoroutineUiRunner
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