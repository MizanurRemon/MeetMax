package com.meetmax.auth_data.dataSource.local

import com.meetmax.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveUser(userEntity: UserEntity)
    suspend fun getUsers(): Flow<List<UserEntity>>
    suspend fun deleteUsers()
}