package com.jasminsp.parliamentmemberapp

import android.app.Application
import android.content.Context
import android.util.Log

// Contexts for the application

class MyApp : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("QQQ", "MyApp onCreate")
        appContext = applicationContext
    }
}
