package com.example.data.local.provider

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import com.example.data.local.provider.UserContentProviderB.Companion.ALL_KEY
import com.example.data.local.provider.UserContentProviderB.Companion.CHECKED_KEY
import com.example.data.local.provider.UserContentProviderB.Companion.DOMAIN_URI_A
import com.example.data.local.provider.UserContentProviderB.Companion.DOMAIN_URI_B
import com.example.data.local.provider.UserContentProviderB.Companion.FROM_KEY
import com.example.data.local.provider.UserContentProviderB.Companion.ID_KEY
import com.example.data.local.provider.UserContentProviderB.Companion.NAME_KEY
import com.example.data.local.provider.UserContentProviderB.Companion.PROVIDER_B

class ProviderManager(private val contentResolver: ContentResolver) {

    fun deleteUser(pathUri: String,id: Int): Int {
        return contentResolver.delete(
            Uri.parse(pathUri),
            "$ID_KEY=?",
            arrayOf("$id")
        )
    }

    fun insertUser(id: Int = 0, name: String, checked: Boolean): String? {
        return contentResolver.insert(
            Uri.parse(DOMAIN_URI_B),
            ContentValues().apply {
                put(ID_KEY, id)
                put(NAME_KEY, name)
                put(CHECKED_KEY, checked)
                put(FROM_KEY, PROVIDER_B)
            })?.path
    }

    fun insertUserToA(id: Int, name: String, checked: Boolean, from: String): String? {
        return contentResolver.insert(
            Uri.parse(DOMAIN_URI_A),
            ContentValues().apply {
                put(ID_KEY, id)
                put(NAME_KEY, name)
                put(CHECKED_KEY, checked)
                put(FROM_KEY, from)
            })?.path
    }

    fun updateUser(id: Int, name: String, checked: Boolean): Int {
        return contentResolver.update(
            Uri.parse(DOMAIN_URI_B),
            ContentValues().apply {
                put(ID_KEY, id)
                put(NAME_KEY, name)
                put(CHECKED_KEY, checked)
                put(FROM_KEY, PROVIDER_B)
            },
            null,
            null
        )
    }

    fun updateUserToA(id: Int, name: String, checked: Boolean, from: String): Int {
        return contentResolver.update(
            Uri.parse(DOMAIN_URI_A),
            ContentValues().apply {
                put(ID_KEY, id)
                put(NAME_KEY, name)
                put(CHECKED_KEY, checked)
                put(FROM_KEY, from)
            },
            null,
            null
        )
    }

    fun updateCheckedAllUser(pathUri: String): Int {
        return contentResolver.update(
            Uri.parse(pathUri),
            ContentValues().apply { put(FROM_KEY, ALL_KEY) },
            null,
            null
        )
    }
}