package com.example.domain.usecase

import com.example.domain.repository.IUserRepository

class DeleteUserUseCase(private val iUserRepository: IUserRepository) {
     operator fun invoke(id:Int): Int {
     return   iUserRepository.deleteUser(id)
    }
}