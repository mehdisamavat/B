package com.example.data.repository

import com.example.data.schedule.ScheduleManager
import com.example.domain.repository.ISchedulerRepository

class SchedulerRepository(private val scheduleManager: ScheduleManager) : ISchedulerRepository {
    override fun startToSchedule() {
        scheduleManager.startScheduleWithWorkManager()
    }
}