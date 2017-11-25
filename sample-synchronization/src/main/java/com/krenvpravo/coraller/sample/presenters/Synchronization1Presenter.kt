package com.krenvpravo.coraller.sample.presenters

import android.util.Log
import com.krenvpravo.coraller.android.backgroundSync
import com.krenvpravo.coraller.android.onUiThread
import com.krenvpravo.coraller.sample.CORALLER_TAG
import com.krenvpravo.coraller.sample.MainScreenContract

/**
 * @author Dmitry Borodin on 2017-11-20.
 */
class Synchronization1Presenter : MainScreenContract.Presenter {

    private var view : MainScreenContract.View? = null

    override fun onViewStarted(view : MainScreenContract.View) {
        this.view = view
        runExample()
    }

    override fun onViewStopped() {
        view = null
    }

    /**
     * Checks, that sequential switching between threads not leading to synchronization issues
     * Logs with sync issue will never appear in the logs
     */
    private fun runExample() {
        Log.e(CORALLER_TAG, "synchronization01 task is started")
        repeat(100_000) {
            val list = mutableListOf("bar")
            onUiThread {
                backgroundSync {
                    if (list.size != 1) submitSyncIssue("background second sync issue")
                    list.add("foo")
                    if (list.size != 2) submitSyncIssue("background first sync issue")
                }
                if (list.size != 2) submitSyncIssue("UI first sync issue")
                list.removeAt(1)
                if (list.size != 1) submitSyncIssue("UI second sync issue")
            }
        }
        Log.e(CORALLER_TAG, "onCreate is done")
    }

    private fun submitSyncIssue(string: String) {
        Log.e(CORALLER_TAG, string)
        throw RuntimeException(string)
    }
}