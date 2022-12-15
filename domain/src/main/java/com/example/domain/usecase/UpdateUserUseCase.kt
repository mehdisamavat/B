package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository

class UpdateUserUseCase(private val iUserRepository: IUserRepository) {
     operator fun invoke(user: User): Int {
       return iUserRepository.updateUser(user)
    }
}