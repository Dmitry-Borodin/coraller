package com.krenvpravo.coraller_android

import com.krenvpravo.coraller_core.CoroutineUiRunner
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

class CoroutinesUiProdRunner : CoroutineUiRunner {
    override fun <T> onUiThread(block: suspend () -> T): Job = launch(UI) {
        block()
    }
}