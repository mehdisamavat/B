package com.example.domain.usecase

import com.example.domain.repository.IContentProviderRepository

class UpdateUserUseCase(private val iContentProviderRepository: IContentProviderRepository) {
     operator fun invoke(id: Int, name: String, checked: Boolean): Int {
       return iContentProviderRepository.updateUser(id, name, checked)
    }
}