package com.example.providerB.di

import android.app.Application
import android.content.ContentResolver
import androidx.room.Room
import com.example.data.local.database.MainDatabase
import com.example.data.local.provider.ProviderManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideMainDatabase(application: Application) =
     Room.databaseBuilder(application, MainDatabase::class.java, "main").allowMainThreadQueries()
//            .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun provideUserDao(mainDatabase: MainDatabase) = mainDatabase.userDao()

}