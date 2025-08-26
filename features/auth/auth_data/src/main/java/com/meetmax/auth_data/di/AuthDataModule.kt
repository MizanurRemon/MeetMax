package com.meetmax.auth_data.di

import android.content.Context
import com.meetmax.auth_data.dataSource.local.AuthLocalDataSource
import com.meetmax.auth_data.dataSourceImpl.AuthLocalDataSourceImpl
import com.meetmax.auth_data.repository.AuthRepositoryImpl
import com.meetmax.auth_domain.repository.AuthRepository
import com.meetmax.common.util.CoroutineDispatcherProvider
import com.meetmax.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AuthDataModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        @ApplicationContext context: Context,
        authLocalDataSource: AuthLocalDataSource,
    ): AuthRepository {
        return AuthRepositoryImpl(appContext = context, authLocalDataSource = authLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideAuthLocalDataSource(
        userDao: UserDao,
        coroutineDispatcherProvider: CoroutineDispatcherProvider
    ): AuthLocalDataSource {
        return AuthLocalDataSourceImpl(userDao, coroutineDispatcherProvider)
    }

}