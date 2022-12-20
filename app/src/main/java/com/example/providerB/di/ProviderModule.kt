package com.example.providerB.di

import android.app.Application
import android.content.ContentResolver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Provides
    fun provideContentResolver(application: Application): ContentResolver = application.contentResolver

}