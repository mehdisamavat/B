package com.example.data.repository

import com.example.data.mapper.UserMapper.toEntity
import com.example.data.remote.ApiService
import com.example.domain.NetworkResult
import com.example.domain.model.User
import com.example.domain.repository.IUserRemoteRepository

class UserRemoteRepository(private val apiService: ApiService) :
    IUserRemoteRepository {

    override suspend fun uploadData(falseUsers: List<User>): NetworkResult<Unit> {
        return apiService.uploadData(falseUsers.map { it.toEntity() })
    }

}