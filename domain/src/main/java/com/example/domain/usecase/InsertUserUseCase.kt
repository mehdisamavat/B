package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.IContentProviderRepository

class InsertUserUseCase(private val iContentProviderRepository: IContentProviderRepository ) {
     operator fun invoke(name:String,checked:Boolean): String? {
      return  iContentProviderRepository.insertUser(name,checked)
    }
}