package com.meetmax.auth_data.dataSourceImpl

import com.meetmax.auth_data.dataSource.local.AuthLocalDataSource
import com.meetmax.common.util.CoroutineDispatcherProvider
import com.meetmax.database.dao.UserDao
import com.meetmax.database.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AuthLocalDataSourceImpl(
    private val userDao: UserDao,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
): AuthLocalDataSource {

    override suspend fun saveUser(userEntity: UserEntity) =
        withContext(coroutineDispatcherProvider.io) {
            userDao.insertUser(userEntity)
        }

    override suspend fun getUsers(): Flow<List<UserEntity>> =
        withContext(coroutineDispatcherProvider.io) {
            userDao.getUsers()
        }

    override suspend fun deleteUsers() = withContext(coroutineDispatcherProvider.io) {
        userDao.deleteUsers()
    }
}