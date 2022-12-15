package com.example.data.schedule.scheduler

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import androidx.work.*
import com.example.common.ProviderContract
import com.example.data.schedule.ScheduleManager
import java.util.concurrent.TimeUnit

class UserWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters){
    override suspend fun doWork(): Result {
        try {
            context.contentResolver.update(Uri.parse(ProviderContract.DOMAIN_UPDATE_URI_B), ContentValues().apply { put("from","ALL") },null,null)
            return Result.success()
        } catch (e: Exception){
//            WorkManager.getInstance(context).enqueue(( OneTimeWorkRequest
//                .Builder(UserWorker::class.java)
//                .setInitialDelay(5, TimeUnit.MINUTES)
//                .build()))
            val falseWorkerRequest = OneTimeWorkRequest
                .Builder(UserWorker::class.java)
                .addTag(ScheduleManager.WORKER_TAG)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(context).enqueueUniqueWork("falser", ExistingWorkPolicy.KEEP,falseWorkerRequest)

        }finally {
            val falseWorkerRequest = OneTimeWorkRequest
                .Builder(UserWorker::class.java)
                .addTag(ScheduleManager.WORKER_TAG)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(context).enqueueUniqueWork("falser", ExistingWorkPolicy.KEEP,falseWorkerRequest)
        }
        return Result.failure()
    }
}
