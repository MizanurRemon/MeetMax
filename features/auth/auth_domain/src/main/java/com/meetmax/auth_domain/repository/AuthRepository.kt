package com.meetmax.auth_domain.repository

import android.app.Activity
import android.content.Intent
import com.meetmax.auth_domain.model.AuthUserInfo

interface AuthRepository {
    suspend fun signInWithGoogle(activity: Activity): Result<AuthUserInfo>
    suspend fun handleGoogleSignInResult(data: Intent?): Result<AuthUserInfo>
    fun getGoogleSignInIntent(activity: Activity): Intent

    /*    suspend fun signOut()

        fun user(): Flow<AuthUserInfo?>*/
}