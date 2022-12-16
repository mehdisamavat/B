package com.example.domain.repository

import com.example.domain.NetworkResult

interface IUserRemoteRepository {
    suspend fun uploadData():NetworkResult<Unit>
}