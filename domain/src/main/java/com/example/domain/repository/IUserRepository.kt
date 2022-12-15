package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getUsers(): Flow<List<User?>>

    fun getUser(id: Int): Flow<User?>

     fun deleteUser(id: Int):Int

     fun insertUser(user: User): String?

     fun updateUser(user: User):Int
}
