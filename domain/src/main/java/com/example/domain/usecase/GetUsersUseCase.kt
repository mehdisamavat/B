package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserLocalRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val iUserLocalRepository: IUserLocalRepository) {
    operator fun invoke(): Flow<List<User?>> {
        return iUserLocalRepository.getUsers()
    }
}