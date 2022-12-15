package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IContentProviderRepository

class InsertUserUseCase(private val iContentProviderRepository: IContentProviderRepository ) {
     operator fun invoke(user: User): String? {
      return  iContentProviderRepository.insertUser(user)
    }
}