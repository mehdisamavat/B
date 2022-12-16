package com.example.data.repository

import com.example.data.local.dao.UserDao
import com.example.data.mapper.UserMapper.toDomain
import com.example.data.remote.ApiService
import com.example.domain.NetworkResult
import com.example.domain.repository.IUserRemoteRepository

class UserRemoteRepository(private val apiService: ApiService, private val userDao: UserDao) :
    IUserRemoteRepository {

    override suspend fun uploadData(): NetworkResult<Unit> {
        return apiService.uploadData(userDao.getAll().map { it.toDomain() })

    }


}