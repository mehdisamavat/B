package com.example.data.local.dao

import android.database.Cursor
import androidx.room.*
import com.example.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity): Long

    @Update
    fun update(userEntity: UserEntity): Int

    @Query("UPDATE UserEntity SET checked = CASE WHEN 1 THEN 0 END")
    fun updateAllCheckedToFalse(): Int

    @Delete
    fun delete(userEntity: UserEntity): Int

    @Query(value = "DELETE FROM UserEntity WHERE id=:id")
    fun deleteById(id: Int): Int

    @Query(value = "SELECT * FROM UserEntity ")
    fun selectAll(): Cursor

    @Query(value = "SELECT * FROM UserEntity WHERE id=:id")
    fun selectById(id: Int): Cursor

    @Query(value = "SELECT * FROM UserEntity ")
    fun getAll(): List<UserEntity>

    @Query(value = "SELECT * FROM UserEntity ")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query(value = "SELECT * FROM UserEntity WHERE id=:id")
    fun getUser(id: Int): Flow<UserEntity>


}