package com.simios.workboxchallenge.data.network

import com.simios.workboxchallenge.data.model.RandomUserModel
import com.simios.workboxchallenge.data.model.ResponseRandomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsersService @Inject constructor(private val api: RandomApiClient) {

    suspend fun getUsersService(): List<RandomUserModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getUser()
            (response.body()?.results ?: emptyList())
        }
    }
}