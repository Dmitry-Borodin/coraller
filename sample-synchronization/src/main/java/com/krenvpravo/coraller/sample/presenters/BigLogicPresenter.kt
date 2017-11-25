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
    }

    override fun onViewStopped() {
        view = null
    }

    private fun runExample() = onUiThread{
        val stringList = backgroundSync {
            generateListHeavy()
        }
        showObjectToView(stringList)
    }

    //this functions can be not "suspend" even when they are called from
    private fun showObjectToView(stringList: Any) =
            Log.d(CORALLER_TAG, stringList.toString())

    private fun generateListHeavy() = mutableListOf<String>()
}