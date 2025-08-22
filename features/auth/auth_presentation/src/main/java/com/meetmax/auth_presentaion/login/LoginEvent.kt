package com.meetmax.auth_presentaion.login

sealed class LoginEvent {
    data class OnEmailEnter(val email: String) : LoginEvent()
    data class OnPasswordEnter(val password: String): LoginEvent()
    data object OnEmailTouchedListener: LoginEvent()
    data object OnPasswordTouchedListener: LoginEvent()
}