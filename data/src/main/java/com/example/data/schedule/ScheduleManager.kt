package com.example.data.schedule

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.data.schedule.scheduler.UserWorker
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ScheduleManager @Inject constructor(@ApplicationContext val context: Context) {

    fun startScheduleWithWorkManager() {

        val falseWorkerRequest = OneTimeWorkRequest
            .Builder(UserWorker::class.java)
            .addTag(WORKER_TAG)
            .setInitialDelay(5, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork("falser", ExistingWorkPolicy.KEEP,falseWorkerRequest)
    }

    fun startScheduleWithJobService() {

    }

    companion object {
         const val WORKER_TAG = "com.example.data.schedule"
    }


}