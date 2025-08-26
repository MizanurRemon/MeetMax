package com.meetmax.auth_presentaion.registration

import android.app.Activity
import android.content.Intent

sealed class SignUpEvent {
    data class OnEmailEnter(val email: String) : SignUpEvent()
    data class OnPasswordEnter(val password: String) : SignUpEvent()
    data object OnEmailTouchedListener : SignUpEvent()
    data object OnPasswordTouchedListener : SignUpEvent()
    data class OnNameInput(val name : String) : SignUpEvent()
    data object OnNameInputTouchedListener : SignUpEvent()
    data class OnDateSelection(val status : Boolean): SignUpEvent()
    data class OnDateEnter(val date : String) : SignUpEvent()
    data class OnOptionSelected(val gender: Int) : SignUpEvent()
    data class OnGoogleSignIn(val activity: Activity?) : SignUpEvent()
    data class OnHandleGoogleSignInResult(val data: Intent?) : SignUpEvent()
}