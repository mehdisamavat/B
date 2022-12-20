package com.example.data.local.dao

import androidx.room.*
import com.example.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity): Long

    @Update
    fun update(userEntity: UserEntity): Int

    @Query("SELECT * FROM UserEntity WHERE checked = 0 ")
    fun getFalseValue(): List<UserEntity>?


    @Query(value = "DELETE FROM UserEntity WHERE id=:id")
    fun deleteById(id: Int): Int


    @Query(value = "SELECT * FROM UserEntity ")
    fun getAllUsers(): Flow<List<UserEntity>>

}