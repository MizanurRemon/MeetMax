package com.meetmax.auth_presentaion.login

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.meetmax.auth_domain.use_case.GetGoogleSignInIntentUseCase
import com.meetmax.auth_domain.use_case.HandleGoogleSignInResultUseCase
import com.meetmax.auth_domain.use_case.SignInWithGoogleUseCase
import com.meetmax.common.util.WEB_CLIENT_ID
import com.meetmax.common.util.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getGoogleSignInIntentUseCase: GetGoogleSignInIntentUseCase,
    private val handleGoogleSignInResultUseCase: HandleGoogleSignInResultUseCase
) : ViewModel() {

    private val _launchSignInIntent = MutableSharedFlow<Intent>(extraBufferCapacity = 1)
    val launchSignInIntent = _launchSignInIntent.asSharedFlow()

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

            is LoginEvent.OnRememberMeChecked -> {
                state = state.copy(isRememberMeChecked = event.state)
            }

          /*  is LoginEvent.OnGoogleSignIn -> {
                viewModelScope.launch {
                    event.activity?.let {
                        signInWithGoogleUseCase(activity = it).onSuccess {
                            Log.d("dataxx", "onEvent: $it")
                        }.onFailure {
                            Log.d("dataxx", "ERROR: $it")
                        }
                    }
                }
            }*/

            is LoginEvent.OnGoogleSignIn -> {
                val activity = event.activity ?: return
                viewModelScope.launch {
                   // state = state.copy(loading = true, error = null)
                    val result = signInWithGoogleUseCase(activity)
                    result.onSuccess { user ->
                       // state = state.copy(user = user, loading = false, error = null)
                        Log.d("dataxx", "Signed in via Credential Manager: $user")
                    }.onFailure { err ->
                        if (err is NoCredentialException) {
                            // Fallback to legacy intent
                            try {
                                // IMPORTANT: clear stale session before launching
                                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                    .requestIdToken(WEB_CLIENT_ID)
                                    .requestEmail()
                                    .build()
                                val client = GoogleSignIn.getClient(activity, gso)
                                client.signOut().addOnCompleteListener {
                                    val intent = getGoogleSignInIntentUseCase(activity)
                                    _launchSignInIntent.tryEmit(intent)
                                }
                            } catch (e: Exception) {
                                //state = state.copy(loading = false, error = e.message)
                                Log.d("dataxx", "Failed to build Google sign-in intent", e)
                            }
                        } else {
                           // state = state.copy(loading = false, error = err.message)
                            Log.d("dataxx", "Credential Manager failed", err)
                        }
                    }
                }
            }


            is LoginEvent.OnHandleGoogleSignInResult -> {
                viewModelScope.launch {
                    val result = handleGoogleSignInResultUseCase(event.data)
                    result.onSuccess { user ->
                       // state = state.copy(user = user, loading = false, error = null)
                        Log.d("dataxx", "Signed in via Legacy UI: $user")
                    }.onFailure { e ->
                      //  state = state.copy(loading = false, error = e.message)
                        Log.d("dataxx", "Google Sign-In error", e)
                    }
                }
            }
        }
    }
}