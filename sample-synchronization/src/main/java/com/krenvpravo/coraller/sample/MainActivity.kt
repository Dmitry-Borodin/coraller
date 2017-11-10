package com.krenvpravo.coraller.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.krenvpravo.coraller.sample.examples.synchronization01

val CORALLER_TAG = "coraller_tag"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        synchronization01()
        Log.e(CORALLER_TAG, "onCreate is done")
    }
}
