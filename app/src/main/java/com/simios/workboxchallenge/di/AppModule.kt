package com.simios.workboxchallenge

import android.content.Context
import androidx.room.Room
import com.simios.workboxchallenge.data.database.UserDataBase
import com.simios.workboxchallenge.data.network.GetUsersService
import com.simios.workboxchallenge.data.network.RandomApiClient
import com.simios.workboxchallenge.data.repository.GetUserRepository
import com.simios.workboxchallenge.domain.GetUsersUseCase
import com.simios.workboxchallenge.ui.viewmodels.UsersViewModel
import com.simios.workboxchallenge.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    val USER_DATABASE_NAME = "User_database"

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(RandomApiClient::class.java)
    }

    single {
        GetUsersService(get())
    }

    single {
        Room.databaseBuilder(get<Context>(), UserDataBase::class.java, USER_DATABASE_NAME).build()
    }

    single {
        get<UserDataBase>().getUsersDao()
    }

    single {
        GetUserRepository(get(), get())
    }

    single {
        GetUsersUseCase(get())
    }

    viewModel { UsersViewModel(get()) }
}