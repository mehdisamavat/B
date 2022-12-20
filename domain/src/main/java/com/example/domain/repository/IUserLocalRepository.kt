package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserLocalRepository {
    fun getUsers(): Flow<List<User?>>

    fun deleteUser(id: Int):Int

    fun updateUser(id: Int,name:String,checked:Boolean):Int

}
