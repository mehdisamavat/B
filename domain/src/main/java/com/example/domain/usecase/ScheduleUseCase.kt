package com.example.domain.usecase

import com.example.domain.repository.ISchedulerRepository

class ScheduleUseCase (private val iSchedulerRepository: ISchedulerRepository) {
    operator fun invoke() {
        iSchedulerRepository.startToSchedule()
    }
}