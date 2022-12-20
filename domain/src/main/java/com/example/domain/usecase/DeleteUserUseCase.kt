package com.example.domain.usecase

import com.example.domain.repository.IUserLocalRepository

class DeleteUserUseCase(private val iUserLocalRepository: IUserLocalRepository) {
    operator fun invoke(id: Int): Int {
        return iUserLocalRepository.deleteUser(id)
    }
}