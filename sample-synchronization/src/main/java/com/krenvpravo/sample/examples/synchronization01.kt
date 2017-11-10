package com.krenvpravo.sample.examples

import android.util.Log
import com.krenvpravo.coraller_android.backgroundSync
import com.krenvpravo.coraller_android.onUiThread
import com.krenvpravo.sample.CORALLER_TAG

fun synchronization01() {
    Log.e(CORALLER_TAG, "synchronization01 task is started")
    repeat(1000_00) {
        val list = mutableListOf("bar")
        onUiThread {
            backgroundSync {
                if (list.size != 1) Log.e(CORALLER_TAG, "background second sync issue")
                list.add("foo")
                if (list.size != 2) Log.e(CORALLER_TAG, "background first sync issue")
            }
            if (list.size != 2) Log.e(CORALLER_TAG, "UI first sync issue")
            list.removeAt(1)
            if (list.size != 1) Log.e(CORALLER_TAG, "UI second sync issue")
        }
    }
}