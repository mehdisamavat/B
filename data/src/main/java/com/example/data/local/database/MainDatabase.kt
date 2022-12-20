package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.UserEntity
import com.example.data.local.dao.UserDao

@Database(entities = [UserEntity::class], version = 2)
abstract class MainDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

