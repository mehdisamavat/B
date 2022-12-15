package com.example.newprovider2.di

import com.example.data.dao.UserDao
import com.example.data.provider.ProviderManager
import com.example.data.repository.ContentProviderRepository
import com.example.data.repository.SchedulerRepository
import com.example.data.repository.UserRepository
import com.example.data.schedule.ScheduleManager
import com.example.domain.repository.IContentProviderRepository
import com.example.domain.repository.ISchedulerRepository
import com.example.domain.repository.IUserRepository
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
    fun provideUserRepository(userDao: UserDao): IUserRepository =
        UserRepository(userDao)

    @Provides
    @Singleton
    fun provideContentProviderRepository(providerManager: ProviderManager):IContentProviderRepository =
        ContentProviderRepository(providerManager)


    @Provides
    @Singleton
    fun provideSchedulerRepository(scheduleManager: ScheduleManager) :ISchedulerRepository =
        SchedulerRepository(scheduleManager)

}