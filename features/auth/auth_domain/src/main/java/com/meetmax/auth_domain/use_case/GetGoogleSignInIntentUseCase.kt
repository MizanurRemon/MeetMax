package com.meetmax.auth_domain.use_case

import android.app.Activity
import android.content.Intent
import com.meetmax.auth_domain.repository.AuthRepository

class GetGoogleSignInIntentUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(activity: Activity): Intent {
        return authRepository.getGoogleSignInIntent(activity)
    }
}