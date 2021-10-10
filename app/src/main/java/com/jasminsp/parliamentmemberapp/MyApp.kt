package com.jasminsp.parliamentmemberapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: Contexts for the application

class MyApp : Application() {
    private val appScope = CoroutineScope(Dispatchers.Default)
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("QQQ", "MyApp onCreate")
        appContext = applicationContext
        delayedInit()
    }

    private fun delayedInit() {
        appScope.launch {
            setupRecurringWork()
        }
    }

    // Setup WorkManager background job to update new data every 14 days.
    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<DataWorker>(14, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(appContext).enqueueUniquePeriodicWork(
            DataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}
