package com.meetmax.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meetmax.database.dao.UserDao
import com.meetmax.database.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao

}