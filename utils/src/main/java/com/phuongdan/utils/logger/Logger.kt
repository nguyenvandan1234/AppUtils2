package com.phuongdan.utils.logger

import android.os.Build
import android.util.Log
import com.phuongdan.utils.BuildConfig

object Logger {
    var tag = "dannv"

    fun d (message: String) {
        if (BuildConfig.DEBUG) {
            Log.d("dannv", "$message")
        }
    }

    fun e (message: String) {
        if (BuildConfig.DEBUG) {
            Log.e("dannv", "$message")
        }
    }
}