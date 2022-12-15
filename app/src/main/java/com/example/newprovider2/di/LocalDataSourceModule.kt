package com.example.newprovider2.di

import android.content.ContentResolver
import com.example.data.dao.UserDao
import com.example.data.localdatasource.IUserLocalDataSource
import com.example.data.localdatasource.UserLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideTaskLocalDataSource(userDao: UserDao, contentResolver: ContentResolver): IUserLocalDataSource =
        UserLocalDataSource(userDao,contentResolver)


}