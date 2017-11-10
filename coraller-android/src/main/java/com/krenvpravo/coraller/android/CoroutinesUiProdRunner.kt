package com.krenvpravo.coraller.android

import com.krenvpravo.coraller.core.CoroutineUiRunner
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

class CoroutinesUiProdRunner : CoroutineUiRunner {
    override fun <T> onUiThread(block: suspend () -> T): Job = launch(UI) {
        block()
    }
}