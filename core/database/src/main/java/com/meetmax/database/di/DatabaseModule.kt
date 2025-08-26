package com.meetmax.database.di

import android.app.Application
import androidx.room.Room
import com.meetmax.database.AppDatabase
import com.meetmax.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private val DB_NAME = "meet_max_db"

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao
}