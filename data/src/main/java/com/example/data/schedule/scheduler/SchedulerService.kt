package com.example.data.schedule.scheduler

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.ContentValues
import android.net.Uri
import com.example.common.ProviderContract

@SuppressLint("SpecifyJobSchedulerIdRange")
class SchedulerService : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {
        contentResolver.update(Uri.parse(ProviderContract.DOMAIN_UPDATE_URI_B), ContentValues().apply { put("from","ALL") },null,null)
        jobFinished(params,true)
        return true
    }
    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }
}