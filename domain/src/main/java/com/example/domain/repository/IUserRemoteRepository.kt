package com.example.domain.repository

import com.example.domain.NetworkResult
import com.example.domain.model.User

interface IUserRemoteRepository {
    suspend fun uploadData(falseUsers:List<User>):NetworkResult<Unit>
}