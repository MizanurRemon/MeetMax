package com.meetmax.auth_domain.use_case

import android.app.Activity
import com.meetmax.auth_domain.model.AuthUserInfo
import com.meetmax.auth_domain.repository.AuthRepository

class SignInWithGoogleUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(activity: Activity): Result<AuthUserInfo> = authRepository.signInWithGoogle(activity = activity)

}