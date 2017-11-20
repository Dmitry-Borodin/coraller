package com.krenvpravo.coraller.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.krenvpravo.coraller.sample.examples.synchronization01

const val CORALLER_TAG = "coraller_tag"

class MainActivity : AppCompatActivity() {

    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.onViewStarted()
    }

    override fun onStop() {
        super.onStop()
        presenter.onViewStopped()
    }
}
