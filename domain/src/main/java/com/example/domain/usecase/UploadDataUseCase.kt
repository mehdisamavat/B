package com.example.domain.usecase

import com.example.domain.NetworkResult
import com.example.domain.model.User
import com.example.domain.repository.IUserRemoteRepository

class UploadDataUseCase(private val iUserRemoteRepository: IUserRemoteRepository) {
    suspend operator fun invoke(): NetworkResult<Unit> {
        return  iUserRemoteRepository.uploadData()
    }
}