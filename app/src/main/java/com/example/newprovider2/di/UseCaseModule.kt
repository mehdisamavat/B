package com.example.newprovider2.di

import com.example.domain.repository.IUserRepository
import com.example.domain.usecase.DeleteUserUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.GetUsersUseCase
import com.example.domain.usecase.InsertUserUseCase
import com.example.domain.usecase.UpdateUserUseCase
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
    fun provideInsertUserUseCase(iUserRepository: IUserRepository) =
        InsertUserUseCase(iUserRepository)


    @Provides
    @ViewModelScoped
    fun provideDeleteUserUseCase(iUserRepository: IUserRepository) =
        DeleteUserUseCase(iUserRepository)


    @Provides
    @ViewModelScoped
    fun provideUpdateUserUseCase(iUserRepository: IUserRepository) =
        UpdateUserUseCase(iUserRepository)


    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(iUserRepository: IUserRepository) = GetUserUseCase(iUserRepository)


    @Provides
    @ViewModelScoped
    fun provideGetUsersUseCase(iUserRepository: IUserRepository) = GetUsersUseCase(iUserRepository)


}