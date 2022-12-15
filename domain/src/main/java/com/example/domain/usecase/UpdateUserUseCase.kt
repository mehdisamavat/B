package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IContentProviderRepository

class UpdateUserUseCase(private val iContentProviderRepository: IContentProviderRepository) {
     operator fun invoke(user: User): Int {
       return iContentProviderRepository.updateUser(user)
    }
}