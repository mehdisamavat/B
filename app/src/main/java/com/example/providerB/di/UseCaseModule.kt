package com.example.providerB.di

import com.example.domain.repository.IContentProviderRepository
import com.example.domain.repository.ISchedulerRepository
import com.example.domain.repository.IUserLocalRepository
import com.example.domain.repository.IUserRemoteRepository
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
    fun provideGetUserUseCase(iUserLocalRepository: IUserLocalRepository) = GetUserUseCase(iUserLocalRepository)


    @Provides
    @ViewModelScoped
    fun provideGetUsersUseCase(iUserLocalRepository: IUserLocalRepository) = GetUsersUseCase(iUserLocalRepository)

    @Provides
    @ViewModelScoped
    fun provideGetUsersRemoteUseCase(iUserRemoteRepository: IUserRemoteRepository) = UploadDataUseCase(iUserRemoteRepository)

    @Provides
    @ViewModelScoped
    fun provideScheduleUseCase( iSchedulerRepository: ISchedulerRepository) = ScheduleUseCase(iSchedulerRepository)


}