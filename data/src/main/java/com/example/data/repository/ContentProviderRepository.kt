package com.example.data.repository

import com.example.data.local.provider.ProviderManager
import com.example.data.local.provider.UserContentProviderB.Companion.DOMAIN_URI_B
import com.example.domain.repository.IContentProviderRepository

class ContentProviderRepository(private val providerManager: ProviderManager): IContentProviderRepository {
    override  fun deleteUser(id: Int): Int {
        return   providerManager.deleteUser(DOMAIN_URI_B,id)
    }

    override fun insertUser(name: String, checked: Boolean): String? {
        return providerManager.insertUser(name = name, checked = checked)
    }

    override fun updateUser(id: Int, name: String, checked: Boolean): Int {
        return  providerManager.updateUser(id,name,checked)
    }


}