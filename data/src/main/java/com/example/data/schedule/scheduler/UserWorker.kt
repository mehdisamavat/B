package com.example.data.schedule.scheduler

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.data.local.dao.UserDao
import com.example.data.mapper.UserMapper.toDomain
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
class UserWorker  @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val userDao: UserDao,
    private val uploadDataUseCase: UploadDataUseCase
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        return try {
            val falseUsers= userDao.getFalseValue()
            if (falseUsers?.isNotEmpty() == true){
                falseUsers.forEach {
                    it.checked=true
                    userDao.update(it)
                }
                CoroutineScope(Dispatchers.IO).launch {
                    uploadDataUseCase.invoke(falseUsers.map { it.toDomain()!! })
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


}
