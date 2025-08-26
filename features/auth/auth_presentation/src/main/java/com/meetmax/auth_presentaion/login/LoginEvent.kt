package com.meetmax.auth_presentaion.login

import android.app.Activity
import android.content.Intent

sealed class LoginEvent {
    data class OnEmailEnter(val email: String) : LoginEvent()
    data class OnPasswordEnter(val password: String) : LoginEvent()
    data object OnEmailTouchedListener : LoginEvent()
    data object OnPasswordTouchedListener : LoginEvent()
    data class OnRememberMeChecked(val state: Boolean) : LoginEvent()

    data class OnGoogleSignIn(val activity: Activity?) : LoginEvent()
    data class OnHandleGoogleSignInResult(val data: Intent?) : LoginEvent()
}