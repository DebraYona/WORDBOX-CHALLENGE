package com.simios.workboxchallenge.domain

import com.simios.workboxchallenge.data.database.entities.toDatabase
import com.simios.workboxchallenge.data.repository.GetUserRepository
import com.simios.workboxchallenge.domain.model.User
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: GetUserRepository) {

    suspend operator fun invoke(): List<User> {
        val users = repository.getAllUsersFromApi()

        return if (users.isNotEmpty()) {
            repository.clearUser()
            repository.insertAllUser(users.map { it.toDatabase() })
            users
        } else {
            repository.getAllUsersFromDatabase()
        }
    }
}