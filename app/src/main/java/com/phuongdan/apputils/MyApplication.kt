package com.phuongdan.apputils

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.danphuong.utils.storge.DefaultUtilsPreference
import com.danphuong.utils.storge.PreferenceUtils

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceUtils.initPreference(this, "hihi")
        DefaultUtilsPreference.screenBrightness = 1f
        Log.d("dannv", DefaultUtilsPreference.screenBrightness.toString())
    }
}