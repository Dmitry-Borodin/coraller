package com.krenvpravo.coraller.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.krenvpravo.coraller.sample.presenters.Synchronization1Presenter

const val CORALLER_TAG = "coraller_tag"

class MainActivity : AppCompatActivity(), MainScreenContract.View {

    private val presenter = Synchronization1Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.onViewStarted(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onViewStopped()
    }
}
