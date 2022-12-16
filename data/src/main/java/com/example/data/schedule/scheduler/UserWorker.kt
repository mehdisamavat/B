package com.example.data.schedule.scheduler

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.data.local.provider.ProviderManager
import com.example.data.local.provider.UserContentProviderB.Companion.DOMAIN_UPDATE_URI_B
import com.example.data.schedule.ScheduleManager
import com.example.data.schedule.ScheduleManager.Companion.WORK_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class UserWorker  @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val providerManager: ProviderManager
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        return try {
            providerManager.updateCheckedAllUser(DOMAIN_UPDATE_URI_B)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        } finally {
            val falseWorkerRequest = OneTimeWorkRequest
                .Builder(UserWorker::class.java)
                .addTag(ScheduleManager.SCHEDULE_TAG)
                .setInitialDelay(ScheduleManager.DURATION_WORK, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(context)
                .enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.REPLACE, falseWorkerRequest)
        }

    }


}
