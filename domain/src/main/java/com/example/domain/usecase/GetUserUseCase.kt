package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserLocalRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val iUserLocalRepository: IUserLocalRepository) {
    suspend operator fun invoke(id:Int): Flow<User?> {
       return iUserLocalRepository.getUser(id)
    }
}