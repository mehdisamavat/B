package com.example.data.provider

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import com.example.common.ProviderContract

class ProviderManager(private val contentResolver: ContentResolver) {

    fun deleteUser(id: Int, to: String): Int {
        return contentResolver.delete(
            Uri.parse(if (to == "B") ProviderContract.DOMAIN_URI_B else ProviderContract.DOMAIN_URI_A),
            "id=?",
            arrayOf("$id")
        )
    }

    fun insertUser(id: Int = 0, name: String, checked: Boolean, to: String): String? {
        return contentResolver.insert(
            Uri.parse(ProviderContract.DOMAIN_URI_B),
            ContentValues().apply {
                put("id", id)
                put("name", name)
                put("checked", checked)
                put("from", "B")
            })?.path
    }

    fun insertUserToA(id: Int, name: String, checked: Boolean, from: String): String? {
        return contentResolver.insert(
            Uri.parse(ProviderContract.DOMAIN_URI_A),
            ContentValues().apply {
                put("id", id)
                put("name", name)
                put("checked", checked)
                put("from", from)
            })?.path
    }

    fun updateUser(id: Int, name: String, checked: Boolean): Int {
        return contentResolver.update(
            Uri.parse(ProviderContract.DOMAIN_URI_B),
            ContentValues().apply {
                put("id", id)
                put("name", name)
                put("checked", checked)
                put("from", "B")
            },
            null,
            null
        )
    }

    fun updateUserToA(id: Int, name: String, checked: Boolean, from: String): Int {
        return contentResolver.update(
            Uri.parse(ProviderContract.DOMAIN_URI_A),
            ContentValues().apply {
                put("id", id)
                put("name", name)
                put("checked", checked)
                put(
                    "from", from
                )
            },
            null,
            null
        )
    }

    fun updateCheckedAllUser(to: String): Int {
        return contentResolver.update(
            Uri.parse(if (to == "B") ProviderContract.DOMAIN_UPDATE_URI_B else ProviderContract.DOMAIN_UPDATE_URI_A),
            ContentValues().apply { put("from", "ALL") },
            null,
            null
        )
    }
}