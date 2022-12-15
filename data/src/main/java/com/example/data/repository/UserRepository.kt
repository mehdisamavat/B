package com.example.data.repository

import com.example.data.localdatasource.IUserLocalDataSource
import com.example.domain.repository.IUserRepository
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val iUserLocalDataSource: IUserLocalDataSource) : IUserRepository {
    override fun getUsers(): Flow<List<User?>> {
      return iUserLocalDataSource.getUsers()
    }

    override fun getUser(id: Int): Flow<User?> {
      return iUserLocalDataSource.getUser(id)
    }

    override  fun deleteUser(id: Int): Int {
     return   iUserLocalDataSource.deleteUser(id)
    }

    override  fun insertUser(user: User): String? {
        return iUserLocalDataSource.insertUser(user)?.path
    }

    override  fun updateUser(user: User): Int {
        return iUserLocalDataSource.updateUser(user)
    }

}