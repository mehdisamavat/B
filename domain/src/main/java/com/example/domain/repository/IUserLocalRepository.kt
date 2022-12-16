package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserLocalRepository {
    fun getUsers(): Flow<List<User?>>

    fun getUser(id: Int): Flow<User?>

}
