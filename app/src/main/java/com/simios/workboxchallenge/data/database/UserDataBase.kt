package com.simios.workboxchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simios.workboxchallenge.data.database.dao.UserDao
import com.simios.workboxchallenge.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUsersDao(): UserDao
}