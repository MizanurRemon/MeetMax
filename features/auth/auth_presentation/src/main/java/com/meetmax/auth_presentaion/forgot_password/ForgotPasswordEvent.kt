package com.meetmax.auth_presentaion.forgot_password

sealed class ForgotPasswordEvent {
    data class OnEmailEnter(val email: String) : ForgotPasswordEvent()
    object OnEmailTouchedListener : ForgotPasswordEvent()
}