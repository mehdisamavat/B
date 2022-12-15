package com.example.data.repository

import com.example.data.dao.UserDao
import com.example.data.mapper.UserMapper.toDomain
import com.example.data.provider.ProviderManager
import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) : IUserRepository {
    override fun getUsers(): Flow<List<User?>> {
        return userDao.getAllUsers().map { list -> list.map { it.toDomain() } }
    }

    override fun getUser(id: Int): Flow<User?> {
        return userDao.getUser(id).map { user -> user.toDomain() }
    }

}