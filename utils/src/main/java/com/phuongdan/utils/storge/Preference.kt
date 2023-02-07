package com.danphuong.utils.storge

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object DefaultUtilsPreference {

    private var preferences = PreferenceUtils.instance
    private val  SCREEN_BRIGHTNESS = "SCREEN_BRIGHTNESS"
    var screenBrightness : Float = 1.0f
        get() {
            return (preferences.get(SCREEN_BRIGHTNESS, Float::class.java) ?: 1.0) as Float
        }
        set(value) {
            preferences.put(SCREEN_BRIGHTNESS, value) ?: ""
            field = value
        }
}
class PreferenceUtils private constructor() {
    operator fun <T> get(key: String?, anonymousClass: Class<T>): T? {
        if (anonymousClass == String::class.java) {
            return mSharedPreferences!!.getString(key, "") as T?
        } else if (anonymousClass == Boolean::class.java) {
            return java.lang.Boolean.valueOf(mSharedPreferences!!.getBoolean(key, false)) as T
        } else if (anonymousClass == Float::class.java) {
            return java.lang.Float.valueOf(mSharedPreferences!!.getFloat(key, 0f)) as T
        } else if (anonymousClass == Int::class.java) {
            return Integer.valueOf(mSharedPreferences!!.getInt(key, 0)) as T
        } else if (anonymousClass == Long::class.java) {
            return java.lang.Long.valueOf(mSharedPreferences!!.getLong(key, 0)) as T
        }
        return null
    }

    fun <T> put(key: String?, data: T) {
        val editor = mSharedPreferences!!.edit()
        if (data is String) {
            editor.putString(key, data as String)
        } else if (data is Boolean) {
            editor.putBoolean(key, (data as Boolean))
        } else if (data is Float) {
            editor.putFloat(key, (data as Float))
        } else if (data is Int) {
            editor.putInt(key, (data as Int))
        } else if (data is Long) {
            editor.putLong(key, (data as Long))
        }
        editor.apply()
    }

    fun clear() {
        mSharedPreferences!!.edit().clear().apply()
    }

    companion object {
        private lateinit var mSharedPreferences: SharedPreferences
        var mInstance: PreferenceUtils? = null
        var mApplication: Application? = null
        val instance: PreferenceUtils
            get() {
                if (mApplication == null) {
                    throw (Throwable("mApplication not init -> Go application and init "))
                }
                if (mInstance == null) {
                    mInstance = PreferenceUtils()
                }
                return mInstance as PreferenceUtils
            }
        fun initPreference(application: Application, prefName: String) {
            mApplication = application
            mSharedPreferences = application.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        }
    }
}