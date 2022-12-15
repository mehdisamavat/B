package com.example.data.repository

import com.example.data.provider.ProviderManager
import com.example.domain.model.User
import com.example.domain.repository.IContentProviderRepository

class ContentProviderRepository(private val providerManager: ProviderManager): IContentProviderRepository {
    override  fun deleteUser(id: Int): Int {
        return   providerManager.deleteUser(id)
    }

    override  fun insertUser(user: User): String? {
        return providerManager.insertUser(user)
    }

    override  fun updateUser(user: User) :Int{
        return  providerManager.updateUser(user)
    }

}