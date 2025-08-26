package com.meetmax.meetmax.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meetmax.auth_domain.use_case.GetUserUseCase
import com.meetmax.meetmax.home.bottom_nav_screens.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set
    init {
        viewModelScope.launch {
            getUserUseCase().onSuccess { user ->
                if (user.isNotEmpty()) {

                } else {

                }
            }.onFailure {

            }
        }
    }
}