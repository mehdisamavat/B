package com.example.data.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.common.ProviderContract
import com.example.common.ProviderContract.AUTHORITY_B
import com.example.common.ProviderContract.DOMAINS
import com.example.common.ProviderContract.DOMAINS_ALL_FALSE
import com.example.common.ProviderContract.DOMAINS_ALL_FALSE_CODE
import com.example.common.ProviderContract.DOMAINS_CODE
import com.example.common.ProviderContract.DOMAINS_ITEM
import com.example.common.ProviderContract.DOMAINS_ITEM_CODE
import com.example.common.ProviderContract.DOMAIN_UPDATE_URI_B
import com.example.data.dao.UserDao
import com.example.data.entity.UserEntity
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors.fromApplication
import dagger.hilt.components.SingletonComponent


class UserContentProvider2 : ContentProvider() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ContentProviderEntryPoint {
        fun getDao(): UserDao
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY_B, DOMAINS, DOMAINS_CODE)
        addURI(AUTHORITY_B, DOMAINS_ITEM, DOMAINS_ITEM_CODE)
        addURI(AUTHORITY_B, DOMAINS_ALL_FALSE, DOMAINS_ALL_FALSE_CODE)
    }
    lateinit var userDao: UserDao
    private lateinit var appContext: Context
    override fun onCreate(): Boolean {

        appContext = context?.applicationContext ?: throw IllegalStateException()
        val hiltEntryPoint = fromApplication(appContext, ContentProviderEntryPoint::class.java)
        userDao = hiltEntryPoint.getDao()

        return true
    }


    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            DOMAINS_CODE -> {
                userDao.selectAll()
            }
            DOMAINS_ITEM_CODE -> {
                userDao.selectById(uri.lastPathSegment!!.toInt())
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            DOMAINS_CODE -> "vnd.android.cursor.dir/$AUTHORITY_B/domains"
            DOMAINS_ITEM_CODE -> "vnd.android.cursor.item/$AUTHORITY_B/domains"
            DOMAINS_ALL_FALSE_CODE -> "vnd.android.cursor.item/$AUTHORITY_B/domainsAllFalse"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (uriMatcher.match(uri)) {
            DOMAINS_CODE -> {
                val userEntity = UserEntity(id =  values?.get("id").toString().toInt(), name = values?.get("name").toString(), checked = values?.get("checked").toString().toBoolean() )
                Log.i("mehdi", "insert    $userEntity   ${values?.get("from")}   2")

                val rowId = userDao.insert(userEntity)
                val finalUri = ContentUris.withAppendedId(uri, rowId)
                context!!.contentResolver.notifyChange(finalUri, null)
                if (values?.get("from").toString() == "B")
                context!!. contentResolver.insert(Uri.parse(ProviderContract.DOMAIN_URI_A),ContentValues().apply {
                    put("id",rowId)
                    put("name",userEntity.name)
                    put("checked",userEntity.checked)
                    put("from","B")
                })
                finalUri
            }
            else -> null
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return when (uriMatcher.match(uri)) {
            DOMAINS_CODE -> {
                val userEntity = UserEntity(id = values?.get("id").toString().toInt()
                    , name = values?.get("name").toString()
                    , checked = values?.get("checked")?.toString().toBoolean())
                val count = userDao.update(userEntity)
                if (count == 1) {
                    context!!.contentResolver.notifyChange(ContentUris.withAppendedId(uri, userEntity.id.toLong()), null)
                 when(values?.get("from")?.toString()){
                     "B" -> {
                         context!!.contentResolver.update(Uri.parse(ProviderContract.DOMAIN_URI_A), ContentValues().apply {
                             put("id",userEntity.id.toString())
                             put("name",userEntity.name)
                             put("checked",userEntity.checked)
                             put("from","B")
                         },null,null)
                     }

                 }
                }
                count
            }
            DOMAINS_ALL_FALSE_CODE -> {
                userDao.updateAllCheckedToFalse()
                context!!.contentResolver.update(Uri.parse(ProviderContract.DOMAIN_UPDATE_URI_A), ContentValues().apply { put("from","ALL") },null,null)
            }
            else -> 0
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            DOMAINS_CODE -> {
               val id= selectionArgs?.get(0)?.toInt()
                Log.i("mehdi", "delete    $id   2")
                val count = userDao.deleteById(selectionArgs?.get(0)?.toInt()!!)
                if (count == 1) {
                    context!!.contentResolver.notifyChange(ContentUris.withAppendedId(uri, id?.toLong()!!), null)

                context!!.contentResolver!!.query( Uri.withAppendedPath(Uri.parse(ProviderContract.DOMAIN_URI_A),id.toString()), null, null, null, null)?.apply {
                val idIndex = getColumnIndex("id")
                    while (moveToNext()) {
                         if (getInt(idIndex) == id)
                             context!!.contentResolver.delete(Uri.parse(ProviderContract.DOMAIN_URI_A), "id=?", arrayOf("$id"))
                    }
                    close()
                }

                }
                count
            }
            else -> 0
        }
    }


}