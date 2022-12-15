package com.example.newprovider2.di

import com.example.data.localdatasource.IUserLocalDataSource
import com.example.data.repository.UserRepository
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
    fun provideProjectRepository(iUserLocalDataSource: IUserLocalDataSource): IUserRepository =
        UserRepository(iUserLocalDataSource)
}