package com.meetmax.auth_presentaion.registration

import com.meetmax.common.util.currentDate

data class SignUpState(
    val isMailValid: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isPasswordValid: Boolean = false,
    val isEmailTouched: Boolean = false,
    val isPasswordTouched: Boolean = false,
    val isRememberMeChecked: Boolean = false,
    val name: String = "",
    val isNameTouched: Boolean = false,
    val dob: String = currentDate(),
    val isDatePickerOpened: Boolean = false
)
