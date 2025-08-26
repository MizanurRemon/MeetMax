package com.meetmax.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.meetmax.database.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("DELETE  FROM user")
    suspend fun deleteUsers()

    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<UserEntity>>
}