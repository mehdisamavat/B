package com.example.providerB.di

import com.example.domain.repository.IUserLocalRepository
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
    fun provideGetUsersUseCase(iUserLocalRepository: IUserLocalRepository) =
        GetUsersUseCase(iUserLocalRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteUserUseCase(iUserLocalRepository: IUserLocalRepository) =
        DeleteUserUseCase(iUserLocalRepository)


    @Provides
    @ViewModelScoped
    fun provideUpdateUserUseCase(iUserLocalRepository: IUserLocalRepository) =
        UpdateUserUseCase(iUserLocalRepository)

}