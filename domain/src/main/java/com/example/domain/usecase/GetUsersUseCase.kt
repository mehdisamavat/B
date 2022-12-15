package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase (private val iUserRepository: IUserRepository) {
    operator fun invoke(): Flow<List<User?>> {
      return  iUserRepository.getUsers()
    }
}