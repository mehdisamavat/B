package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val IUserRepository: IUserRepository) {
    suspend operator fun invoke(id:Int): Flow<User?> {
       return IUserRepository.getUser(id)
    }
}