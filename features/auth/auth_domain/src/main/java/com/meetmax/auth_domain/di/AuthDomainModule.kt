package com.meetmax.auth_domain.di

import com.meetmax.auth_domain.repository.AuthRepository
import com.meetmax.auth_domain.use_case.GetGoogleSignInIntentUseCase
import com.meetmax.auth_domain.use_case.GetUserUseCase
import com.meetmax.auth_domain.use_case.HandleGoogleSignInResultUseCase
import com.meetmax.auth_domain.use_case.SignInWithGoogleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@InstallIn(ViewModelComponent::class)
@Module
class AuthDomainModule {

    @Provides
    @ViewModelScoped
    fun provideSignInWithGoogleUseCase(authRepository: AuthRepository): SignInWithGoogleUseCase {
        return SignInWithGoogleUseCase(authRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideHandleGoogleSignInResultUseCase(authRepository: AuthRepository): HandleGoogleSignInResultUseCase {
        return HandleGoogleSignInResultUseCase(authRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetGoogleSignInIntentUseCase(authRepository: AuthRepository): GetGoogleSignInIntentUseCase {
        return GetGoogleSignInIntentUseCase(authRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(authRepository: AuthRepository): GetUserUseCase{
        return GetUserUseCase(authRepository)
    }
}