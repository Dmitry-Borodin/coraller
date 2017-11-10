package com.krenvpravo.coraller_core

import kotlinx.coroutines.experimental.Job

/**
 * @author Dmitry Borodin on 11/10/17.
 */
interface CoroutineUiRunner {
    fun <T> onUiThread(block: suspend () -> T): Job
}