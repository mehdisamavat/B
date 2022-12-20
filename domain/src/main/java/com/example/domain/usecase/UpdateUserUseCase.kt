package com.example.domain.usecase

import com.example.domain.repository.IUserLocalRepository
import com.example.domain.repository.IUserRemoteRepository

class UpdateUserUseCase(private val  iUserLocalRepository: IUserLocalRepository) {
     operator fun invoke(id: Int, name: String, checked: Boolean): Int {
       return iUserLocalRepository.updateUser(id, name, checked)
    }
}