package com.meetmax.auth_domain.use_case

import android.content.Intent
import com.meetmax.auth_domain.model.AuthUserInfo
import com.meetmax.auth_domain.repository.AuthRepository

class HandleGoogleSignInResultUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(data: Intent?): Result<AuthUserInfo> =
        authRepository.handleGoogleSignInResult(data = data)

}