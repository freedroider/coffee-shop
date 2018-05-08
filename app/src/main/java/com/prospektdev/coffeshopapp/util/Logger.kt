package com.prospektdev.coffeshopapp.util

import android.util.Log
import com.prospektdev.coffeshopapp.BuildConfig

object Logger {
    private const val DEFAULT_TAG = BuildConfig.APP_VERSION_NAME

    fun e(message: String?) {
        if (BuildConfig.DEBUG) {
            Log.e(DEFAULT_TAG, message ?: "")
        }
    }

    fun e(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace()
            Log.e(DEFAULT_TAG, "Exception", throwable)
        }
    }
}