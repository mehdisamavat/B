package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository

class InsertUserUseCase(private val iUserRepository: IUserRepository) {
     operator fun invoke(user: User): String? {
      return  iUserRepository.insertUser(user)
    }
}