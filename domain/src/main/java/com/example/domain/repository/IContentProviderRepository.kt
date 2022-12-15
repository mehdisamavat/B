package com.example.domain.repository

import com.example.domain.model.User

interface IContentProviderRepository {

    fun deleteUser(id: Int):Int

    fun insertUser(user: User): String?

    fun updateUser(user: User):Int
}