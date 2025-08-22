package com.meetmax.auth_presentaion.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.meetmax.common.util.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    init {

    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailEnter -> {
                state = state.copy(
                    isMailValid = validateEmail(event.email),
                    email = event.email
                )
            }

            is LoginEvent.OnPasswordEnter -> {
                state = state.copy(
                    password = event.password,
                    isPasswordValid = event.password.length >= 6
                )
            }

            is LoginEvent.OnEmailTouchedListener -> {
                state = state.copy(isEmailTouched = true)
            }

            is LoginEvent.OnPasswordTouchedListener -> {
                state = state.copy(isPasswordTouched = true)
            }
        }


    }
}