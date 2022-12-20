package com.example.data.mapper

import com.example.data.entity.UserEntity
import com.example.domain.model.User

object UserMapper {
    fun UserEntity?.toDomain(): User? {
        return this?.let {
            User(id = it.id, name = it.name, checked = it.checked)
        }
    }

    fun User?.toEntity(): UserEntity? {
        return this?.let {
            UserEntity(id = it.id, name = it.name, checked = it.checked)
        }
    }

}