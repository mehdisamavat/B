package com.example.domain.usecase

import com.example.domain.repository.IContentProviderRepository

class DeleteUserUseCase(private val iContentProviderRepository: IContentProviderRepository) {
     operator fun invoke(id:Int): Int {
     return   iContentProviderRepository.deleteUser(id)
    }
}