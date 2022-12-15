package com.example.data.scheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.example.common.ProviderContract


class SchedulerService : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {
        Log.i("mehdi", "onStartJob")
        contentResolver.update(Uri.parse(ProviderContract.DOMAIN_UPDATE_URI_B), ContentValues().apply { put("from","ALL") },null,null)
        return true
    }
    override fun onStopJob(params: JobParameters): Boolean {
        Log.i("mehdi", "onStopJob")
        return false
    }
}