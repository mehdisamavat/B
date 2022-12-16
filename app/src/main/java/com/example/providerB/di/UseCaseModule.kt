package com.example.providerB.di

import com.example.domain.repository.IContentProviderRepository
import com.example.domain.repository.ISchedulerRepository
import com.example.domain.repository.IUserRepository
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideInsertUserUseCase( iContentProviderRepository: IContentProviderRepository) =
        InsertUserUseCase(iContentProviderRepository)


    @Provides
    @ViewModelScoped
    fun provideDeleteUserUseCase( iContentProviderRepository: IContentProviderRepository) =
        DeleteUserUseCase(iContentProviderRepository)


    @Provides
    @ViewModelScoped
    fun provideUpdateUserUseCase( iContentProviderRepository: IContentProviderRepository) =
        UpdateUserUseCase(iContentProviderRepository)


    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(iUserRepository: IUserRepository) = GetUserUseCase(iUserRepository)


    @Provides
    @ViewModelScoped
    fun provideGetUsersUseCase(iUserRepository: IUserRepository) = GetUsersUseCase(iUserRepository)

    @Provides
    @ViewModelScoped
    fun provideScheduleUseCase( iSchedulerRepository: ISchedulerRepository) = ScheduleUseCase(iSchedulerRepository)


}