package com.simios.workboxchallenge.domain.model

import com.simios.workboxchallenge.data.database.entities.UserEntity
import com.simios.workboxchallenge.data.model.RandomUserModel

data class User(
    val gender: String,
    val age: Int,
    val name: String,
    val picture: String,
    val email: String,
    val location: String,
    val phone: String,
)

fun RandomUserModel.toDomain(): User =
    User(
        gender,
        dob.age,
        name.title + " " + name.first + " " + name.last,
        picture.medium,
        email,
        location.city,
        phone
    )

fun UserEntity.toDomain() =
    User(gender, age, name, picture, email, location, phone)




