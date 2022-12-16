package com.example.data.schedule.scheduler

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.ContentValues
import android.net.Uri
import com.example.data.local.provider.UserContentProviderB.Companion.ALL_KEY
import com.example.data.local.provider.UserContentProviderB.Companion.DOMAIN_UPDATE_URI_B
import com.example.data.local.provider.UserContentProviderB.Companion.FROM_KEY

@SuppressLint("SpecifyJobSchedulerIdRange")
class SchedulerService : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {
        contentResolver.update(Uri.parse(DOMAIN_UPDATE_URI_B), ContentValues().apply { put(FROM_KEY,ALL_KEY) },null,null)
        jobFinished(params,true)
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }
}