package com.meetmax.auth_presentaion.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.meetmax.common.util.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    init {

    }

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnEmailEnter -> {
                state = state.copy(
                    isMailValid = validateEmail(event.email),
                    email = event.email
                )
            }

            is SignUpEvent.OnPasswordEnter -> {
                state = state.copy(
                    password = event.password,
                    isPasswordValid = event.password.length >= 6
                )
            }

            is SignUpEvent.OnEmailTouchedListener -> {
                state = state.copy(isEmailTouched = true)
            }

            is SignUpEvent.OnPasswordTouchedListener -> {
                state = state.copy(isPasswordTouched = true)
            }

            is SignUpEvent.OnNameInput -> {
                state = state.copy(name = event.name)

            }

            is SignUpEvent.OnNameInputTouchedListener -> {
                state = state.copy(isNameTouched = true)
            }

            is SignUpEvent.OnDateSelection -> {
                state = state.copy(isDatePickerOpened = true)
            }
        }
    }
}