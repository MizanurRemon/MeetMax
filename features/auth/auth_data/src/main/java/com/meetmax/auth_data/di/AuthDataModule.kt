package com.meetmax.auth_data.di

import android.app.Activity
import android.content.Context
import com.meetmax.auth_data.repository.AuthRepositoryImpl
import com.meetmax.auth_domain.repository.AuthRepository
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
        @ApplicationContext context: Context
    ): AuthRepository{
        return AuthRepositoryImpl(appContext = context)
    }

}