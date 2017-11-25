package com.krenvpravo.coraller.sample.presenters

import android.util.Log
import com.krenvpravo.coraller.android.backgroundSync
import com.krenvpravo.coraller.android.onUiThread
import com.krenvpravo.coraller.sample.CORALLER_TAG
import com.krenvpravo.coraller.sample.MainScreenContract

/**
 * @author Dmitry Borodin on 2017-11-25.
 */
class BigLogicPresenter: MainScreenContract.Presenter {
    private var view : MainScreenContract.View? = null

    override fun onViewStarted(view : MainScreenContract.View) {
        this.view = view
        runExample()
        showObjectInView(this::class.java.simpleName + "example run finished")
    }

    override fun onViewStopped() {
        view = null
    }

    private fun runExample() = onUiThread{
        val stringList = backgroundSync {
            generateListHeavy()
        }
        showObjectInView(stringList)
    }

    /**
     *  This functions can be not "suspend" even when they are called from coroutines - since they don't suspend
     */
    private fun showObjectInView(textObject: Any) =
            Log.d(CORALLER_TAG, textObject.toString())

    private fun generateListHeavy() = mutableListOf<String>()
}