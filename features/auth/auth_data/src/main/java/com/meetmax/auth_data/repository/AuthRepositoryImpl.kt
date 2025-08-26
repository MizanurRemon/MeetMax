package com.meetmax.auth_data.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.meetmax.auth_data.dataSource.local.AuthLocalDataSource
import com.meetmax.auth_data.mapper.toUser
import com.meetmax.auth_data.mapper.toUserList
import com.meetmax.auth_domain.model.AuthUserInfo
import com.meetmax.auth_domain.repository.AuthRepository
import com.meetmax.auth_domain.repository.User
import com.meetmax.common.util.WEB_CLIENT_ID
import com.meetmax.database.model.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {

    override suspend fun signInWithGoogle(activity: Activity): Result<AuthUserInfo> {
        return try {
            val credentialManager = CredentialManager.create(appContext)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)   // show picker if needed
                .setServerClientId(WEB_CLIENT_ID)       // MUST be Web client ID
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val response = credentialManager.getCredential(
                context = activity, // must be an Activity
                request = request
            )

            val tokenCred = GoogleIdTokenCredential.createFrom(response.credential.data)
            val user = AuthUserInfo(
                id = tokenCred.id,
                displayName = tokenCred.displayName,
                email = tokenCred.id,
                photoUrl = tokenCred.profilePictureUri?.toString(),
                token = tokenCred.idToken
            )

            Log.d("dataxx", "signInWithGoogle: $user")
            authLocalDataSource.deleteUsers()
            authLocalDataSource.saveUser(
                UserEntity(
                    userID = user.id,
                    name = user.displayName,
                    email = user.email,
                    photoUrl = user.photoUrl,
                    token = user.token
                )
            )
            Result.success(user)
        } catch (e: NoCredentialException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun handleGoogleSignInResult(data: Intent?): Result<AuthUserInfo> {
        return try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)

            val user = AuthUserInfo(
                id = account.id.orEmpty(),
                displayName = account.displayName,
                email = account.email.orEmpty(),
                photoUrl = account.photoUrl?.toString(),
                token = account.idToken
            )
            authLocalDataSource.deleteUsers()
            authLocalDataSource.saveUser(
                UserEntity(
                    userID = user.id,
                    name = user.displayName,
                    email = user.email,
                    photoUrl = user.photoUrl,
                    token = user.token
                )
            )
            Log.d("dataxx", "handleGoogleSignInResult: $user")
            Result.success(user)
        } catch (e: ApiException) {
            Log.d("dataxx", "Google Sign-In failed with status=${e.statusCode}", e)
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getGoogleSignInIntent(activity: Activity): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID) // MUST be Web client ID
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(activity, gso)
        return client.signInIntent
    }

    override suspend fun getUser(): Result<List<User>> {
        return try {
            val userList = authLocalDataSource.getUsers().first()
            if (userList.isNotEmpty()) {
                Result.success(userList.toUserList())
            } else {
                Result.failure(Exception("No user found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}