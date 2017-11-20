package com.krenvpravo.coraller.sample

import android.util.Log
import com.krenvpravo.coraller.sample.examples.synchronization01

/**
 * @author Dmitry Borodin on 2017-11-20.
 */
class MainPresenter : Presenter {

    private var view : View? = null

    override fun onViewStarted(view : View) {
        this.view = view
        runFirstExample()
    }

    override fun onViewStopped() {
        view = null
    }

    private fun runFirstExample() {
        synchronization01()
        Log.e(CORALLER_TAG, "onCreate is done")
    }

}