package com.simios.workboxchallenge.data.network

import com.simios.workboxchallenge.data.model.ResponseRandomUser
import retrofit2.Response
import retrofit2.http.GET

interface RandomApiClient {
    @GET("?results=51/")
    suspend fun getUser(
    ): Response<ResponseRandomUser>


}