package com.simios.workboxchallenge.data.network

import com.simios.workboxchallenge.data.model.ResponseRandomUser
import retrofit2.Response
import retrofit2.http.GET

interface RandomApiClient {
    @GET("")
    suspend fun getUser(
    ): Response<ResponseRandomUser>


}