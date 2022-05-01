package com.simios.workboxchallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.simios.workboxchallenge.domain.model.User


@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "age") val age: Int,
)

fun User.toDatabase() =
    UserEntity(
        gender = gender,
        name = name,
        age = age,
        picture = picture,
        email = email,
        location = location,
        phone = phone,
    )