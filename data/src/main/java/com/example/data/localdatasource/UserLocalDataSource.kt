package com.example.data.localdatasource

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import com.example.common.ProviderContract
import com.example.data.dao.UserDao
import com.example.data.mapper.UserMapper.toDomain
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSource(private val userDao: UserDao, private val contentResolver:ContentResolver) :
    IUserLocalDataSource {


    override fun getUsers(): Flow<List<User?>> {
        return userDao.getAllUsers().map { list -> list.map { it.toDomain() } }
    }

    override fun getUser(id: Int): Flow<User?> {
        return userDao.getUser(id).map { user -> user.toDomain() }
    }

    override  fun deleteUser(id: Int): Int {
     return   contentResolver.delete(Uri.parse(ProviderContract.DOMAIN_URI_B), "id=?", arrayOf("$id"))

    }

    override  fun insertUser(user: User): Uri? {
        return contentResolver.insert(Uri.parse(ProviderContract.DOMAIN_URI_B),ContentValues().apply {
        put("id",user.id)
        put("name",user.name)
        put("checked",user.checked)
        put("from","B")
    })
    }

    override  fun updateUser(user: User) :Int{
        return   contentResolver.update(Uri.parse(ProviderContract.DOMAIN_URI_B), ContentValues().apply {
            put("id",user.id.toString())
            put("name",user.name)
            put("checked",user.checked)
            put("from","B")
        },null,null)
    }
}