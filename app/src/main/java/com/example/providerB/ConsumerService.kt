package com.example.providerB

import android.app.Notification
import dagger.hilt.android.AndroidEntryPoint
import android.app.NotificationManager
import android.app.NotificationChannel
import android.app.Service
import com.example.providerB.R
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.data.entity.UserEntity
import com.example.data.local.dao.UserDao
import com.example.domain.usecase.ScheduleUseCase
import com.example.domain.usecase.UploadDataUseCase
import com.example.providerB.IMyAidlInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Throws

@AndroidEntryPoint
class ConsumerService : Service() {
    @Inject
    lateinit var userDao: UserDao

    @Inject
    lateinit var  scheduleUseCase: ScheduleUseCase

    private fun updateNotification(): Notification {
        val manager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(packageName, packageName, NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Alex channel description"
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, packageName)
        return builder.setSmallIcon(R.drawable.ic_baseline_sync_24).build()
    }

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            scheduleUseCase.invoke()
        }
        startForeground(100, updateNotification())
    }

    override fun onBind(intent: Intent): IBinder? {
        return aidl
    }

    var aidl: IMyAidlInterface.Stub = object : IMyAidlInterface.Stub() {
        @Throws(RemoteException::class)
        override fun getData(aString: String, aBoolean: Boolean) {
            userDao.insert(UserEntity(0,aString,aBoolean))
        }

        @Throws(RemoteException::class)
        override fun stopService() {
            stopSelf()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                stopForeground(STOP_FOREGROUND_REMOVE )
            }
        }
    }
}