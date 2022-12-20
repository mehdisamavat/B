package com.example.providerB

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.data.entity.UserEntity
import com.example.data.local.dao.UserDao
import com.example.data.schedule.scheduler.UserWorker.Companion.NOTIFICATION_CHANNEL_ID
import com.example.data.schedule.scheduler.UserWorker.Companion.NOTIFICATION_CHANNEL_NAME
import com.example.data.schedule.scheduler.UserWorker.Companion.NOTIFICATION_ID
import com.example.domain.usecase.ScheduleUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ConsumerService : Service() {
    @Inject
    lateinit var userDao: UserDao

    @Inject
    lateinit var scheduleUseCase: ScheduleUseCase

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            scheduleUseCase.invoke()
        }
        startForeground(NOTIFICATION_ID, updateNotification(resources.getString(R.string.listening_to_provider)))
    }

    override fun onBind(intent: Intent): IBinder {
        return iMyAidlInterface
    }

    private var iMyAidlInterface: IMyAidlInterface.Stub = object : IMyAidlInterface.Stub() {
        override fun getData(aString: String, aBoolean: Boolean) {
            userDao.insert(UserEntity(0, aString, aBoolean))
        }

        override fun stopService() {
            stopSelf()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                stopForeground(STOP_FOREGROUND_REMOVE)
            }
        }
    }


    private fun updateNotification(title:String): Notification {
        val manager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        return builder.setSmallIcon(android.R.drawable.stat_notify_sync).setContentTitle(title).setAutoCancel(false).build()
    }


}