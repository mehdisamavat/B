package com.example.data.repository

import com.example.data.provider.ProviderManager
import com.example.domain.model.User
import com.example.domain.repository.IContentProviderRepository

class ContentProviderRepository(private val providerManager: ProviderManager): IContentProviderRepository {
    override  fun deleteUser(id: Int): Int {
        return   providerManager.deleteUser(id,"B")
    }

    override fun insertUser(name: String, checked: Boolean): String? {
        return providerManager.insertUser(name = name, checked = checked, to = "B")
    }

    override fun updateUser(id: Int, name: String, checked: Boolean): Int {
        return  providerManager.updateUser(id,name,checked)
    }


}