package com.example.providerB.di

import com.example.data.remote.ApiService
import com.example.data.local.dao.UserDao
import com.example.data.local.provider.ProviderManager
import com.example.data.repository.ContentProviderRepository
import com.example.data.repository.SchedulerRepository
import com.example.data.repository.UserLocalLocalRepository
import com.example.data.repository.UserRemoteRepository
import com.example.data.schedule.ScheduleManager
import com.example.domain.repository.IContentProviderRepository
import com.example.domain.repository.ISchedulerRepository
import com.example.domain.repository.IUserLocalRepository
import com.example.domain.repository.IUserRemoteRepository
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
    fun provideContentProviderRepository(providerManager: ProviderManager):IContentProviderRepository =
        ContentProviderRepository(providerManager)


    @Provides
    @Singleton
    fun provideUserRemoteRepository(apiService: ApiService, userDao: UserDao): IUserRemoteRepository =
        UserRemoteRepository(apiService,userDao)


    @Provides
    @Singleton
    fun provideSchedulerRepository(scheduleManager: ScheduleManager) :ISchedulerRepository =
        SchedulerRepository(scheduleManager)

}