package com.example.data.schedule

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.data.schedule.scheduler.UserWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ScheduleManager @Inject constructor(@ApplicationContext val context: Context) {

    fun startScheduleWithWorkManager() {
        val falseWorkerRequest = OneTimeWorkRequest
            .Builder(UserWorker::class.java)
            .addTag(SCHEDULE_TAG)
            .setInitialDelay(DURATION_WORK, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.KEEP, falseWorkerRequest)
    }

    companion object {
        const val SCHEDULE_TAG = "com.example.data.schedule"
        const val DURATION_WORK = 1L
        const val WORK_NAME = "falser"
    }


}