package com.example.domain.usecase

import com.example.domain.NetworkResult
import com.example.domain.model.User
import com.example.domain.repository.IUserRemoteRepository

class UploadDataUseCase(private val iUserRemoteRepository: IUserRemoteRepository) {
    suspend operator fun invoke(falseUsers:List<User>): NetworkResult<Unit> {
        return  iUserRemoteRepository.uploadData(falseUsers)
    }
}