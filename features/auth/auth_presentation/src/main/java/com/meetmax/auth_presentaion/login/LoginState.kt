package com.meetmax.auth_presentaion.login

data class LoginState(
    val isMailValid: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = false,
    val isEmailTouched: Boolean = false,
    val isPasswordTouched: Boolean = false,
    val isRememberMeChecked: Boolean = false
)
