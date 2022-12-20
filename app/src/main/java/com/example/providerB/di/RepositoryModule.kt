package com.example.providerB.di

import com.example.data.local.dao.UserDao
import com.example.data.remote.ApiService
import com.example.data.repository.SchedulerRepository
import com.example.data.repository.UserLocalLocalRepository
import com.example.data.repository.UserRemoteRepository
import com.example.data.schedule.ScheduleManager
import com.example.domain.repository.ISchedulerRepository
import com.example.domain.repository.IUserLocalRepository
import com.example.domain.repository.IUserRemoteRepository
import com.example.domain.usecase.ScheduleUseCase
import com.example.domain.usecase.UploadDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): IUserLocalRepository =
        UserLocalLocalRepository(userDao)

    @Provides
    @Singleton
    fun provideUserRemoteRepository(
        apiService: ApiService, ): IUserRemoteRepository =
        UserRemoteRepository(apiService)

    @Provides
    @Singleton
    fun provideSchedulerRepository(scheduleManager: ScheduleManager): ISchedulerRepository =
        SchedulerRepository(scheduleManager)


    @Provides
    @Singleton
    fun provideScheduleUseCase(iSchedulerRepository: ISchedulerRepository) =
        ScheduleUseCase(iSchedulerRepository)

    @Provides
    @Singleton
    fun provideUploadUseCase(iUserRemoteRepository: IUserRemoteRepository) =
        UploadDataUseCase(iUserRemoteRepository)


}