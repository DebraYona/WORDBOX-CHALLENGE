package com.simios.workboxchallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simios.workboxchallenge.data.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM  user_table ORDER BY id DESC")
    suspend fun getAllUsers():List<UserEntity>

    @Insert
    suspend fun insertUser(comic: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<UserEntity>)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}