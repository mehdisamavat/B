package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase (private val IUserRepository: IUserRepository) {
    operator fun invoke(): Flow<List<User?>> {
      return  IUserRepository.getUsers()
    }
}