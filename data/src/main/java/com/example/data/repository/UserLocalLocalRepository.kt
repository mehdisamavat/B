package com.example.data.repository

import com.example.data.entity.UserEntity
import com.example.data.local.dao.UserDao
import com.example.data.mapper.UserMapper.toDomain
import com.example.domain.model.User
import com.example.domain.repository.IUserLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalLocalRepository(private val userDao: UserDao) : IUserLocalRepository {
    override fun getUsers(): Flow<List<User?>> {
        return userDao.getAllUsers().map { list -> list.map { it.toDomain() } }
    }

    override fun deleteUser(id: Int): Int {
        return userDao.deleteById(id)
    }

    override fun updateUser(id: Int, name: String, checked: Boolean): Int {
        return userDao.update(UserEntity(id, name, checked))
    }


}