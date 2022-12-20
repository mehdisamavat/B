package com.example.data.schedule.scheduler

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.data.local.dao.UserDao
import com.example.data.mapper.UserMapper.toDomain
import com.example.data.remote.onError
import com.example.data.remote.onException
import com.example.data.remote.onSuccess
import com.example.data.schedule.ScheduleManager
import com.example.data.schedule.ScheduleManager.Companion.WORK_NAME
import com.example.domain.usecase.UploadDataUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltWorker
class UserWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val userDao: UserDao,
    private val uploadDataUseCase: UploadDataUseCase
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        return try {

            val falseUsers = userDao.getFalseValue()
            if (falseUsers?.isNotEmpty() == true) {
                falseUsers.forEach {
                    it.checked = true
                    userDao.update(it)
                }
                CoroutineScope(Dispatchers.IO).launch {
                    uploadDataUseCase.invoke(falseUsers.map { it.toDomain()!! }).onSuccess {
                        updateNotification("Upload Done")
                    }.onError { code, message ->
                        updateNotification("$code  $message")
                    }.onException {
                        updateNotification(it.message ?: "Exception")
                    }
                }
            }

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

    private fun updateNotification(title: String): Notification {
        val manager = context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_NAME)
        return builder.setSmallIcon(android.R.drawable.stat_notify_sync).setContentTitle(title).setAutoCancel(false)
            .build()
    }

    companion object {
        const val NOTIFICATION_ID = 100
        const val NOTIFICATION_CHANNEL_ID = "provider"
        const val NOTIFICATION_CHANNEL_NAME = "provider"
    }


}
