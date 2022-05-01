package com.simios.workboxchallenge.data.repository

import com.simios.workboxchallenge.data.database.dao.UserDao
import com.simios.workboxchallenge.data.database.entities.UserEntity
import com.simios.workboxchallenge.domain.model.User
import com.simios.workboxchallenge.data.model.RandomUserModel
import com.simios.workboxchallenge.data.network.GetUsersService
import com.simios.workboxchallenge.domain.model.toDomain

class GetUserRepository (
    private val api: GetUsersService,
    private val userDao: UserDao
) {

    suspend fun getAllUsersFromApi(): List<User> {
        val response: List<RandomUserModel>  = api.getUsersService()
        return response.map { it.toDomain() }
    }

    suspend fun getAllUsersFromDatabase():List<User>{
        val response: List<UserEntity> = userDao.getAllUsers()
        return response.map { it.toDomain() }
    }

    suspend fun insertAllUser(users:List<UserEntity>){
        userDao.insertAll(users)
    }

    suspend fun clearUser(){
        userDao.deleteAllUsers()
    }
}