package com.example.domain.repository

interface IContentProviderRepository {

    fun deleteUser(id: Int):Int

    fun insertUser(name:String,checked:Boolean): String?

    fun updateUser(id: Int,name:String,checked:Boolean):Int
}