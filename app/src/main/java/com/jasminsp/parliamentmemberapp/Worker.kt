package com.jasminsp.parliamentmemberapp

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jasminsp.parliamentmemberapp.repository.MemberRepository
import retrofit2.HttpException

class DataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.jasminsp.parliamentmemberapp.worker.DataWorker"
    }

    // if memberData is not refreshed, try again, otherwise return success
    override suspend fun doWork(): Result {
        val repository = MemberRepository
        try {
            repository.refreshData()
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}