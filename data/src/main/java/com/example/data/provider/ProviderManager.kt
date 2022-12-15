package com.example.data.provider

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import com.example.common.ProviderContract
import com.example.domain.model.User
import javax.inject.Inject

class ProviderManager @Inject constructor(private val contentResolver: ContentResolver) {

    fun deleteUser(id: Int): Int {
        return   contentResolver.delete(Uri.parse(ProviderContract.DOMAIN_URI_B), "id=?", arrayOf("$id"))
    }

    fun insertUser(user: User): String? {
        return contentResolver.insert(Uri.parse(ProviderContract.DOMAIN_URI_B), ContentValues().apply {
            put("id",user.id)
            put("name",user.name)
            put("checked",user.checked)
            put("from","B")
        })?.path
    }

      fun updateUser(user: User) :Int{
        return   contentResolver.update(Uri.parse(ProviderContract.DOMAIN_URI_B), ContentValues().apply {
            put("id",user.id.toString())
            put("name",user.name)
            put("checked",user.checked)
            put("from","B")
        },null,null)
    }
}