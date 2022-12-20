package com.example.data.schedule

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.data.schedule.scheduler.SchedulerService
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

    fun startScheduleWithJobService() {
        val mJobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        JobInfo.Builder(1, ComponentName(context, SchedulerService::class.java.name))
            .setPeriodic(INTERVAL_MILLIS)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
            .build().also {
                mJobScheduler.schedule(it)
            }
    }

    companion object {
        const val SCHEDULE_TAG = "com.example.data.schedule"
        const val INTERVAL_MILLIS = (5 * 60 * 1000).toLong()
        const val DURATION_WORK = 1L
        const val WORK_NAME = "falser"
    }


}