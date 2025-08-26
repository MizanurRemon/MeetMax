package com.meetmax.meetmax.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meetmax.auth_domain.use_case.GetUserUseCase
import com.meetmax.common.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(2000)

            getUserUseCase().onSuccess { user ->
                if (user.isNotEmpty()) {
                    _uiEvent.emit(UiEvent.Success)
                } else {
                    _uiEvent.emit(UiEvent.NavigateUp)
                }
            }.onFailure {
                _uiEvent.emit(UiEvent.NavigateUp)
            }
        }
    }
}