package com.simios.workboxchallenge.di

import android.content.Context
import androidx.room.Room
import com.simios.workboxchallenge.data.database.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val COMIC_DATABASE_NAME = "User_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDataBase::class.java, COMIC_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideComicDao(db: UserDataBase) = db.getUsersDao()
}