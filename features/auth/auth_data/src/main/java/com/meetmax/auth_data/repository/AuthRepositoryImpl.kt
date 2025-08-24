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
import com.meetmax.auth_domain.model.AuthUserInfo
import com.meetmax.auth_domain.repository.AuthRepository
import com.meetmax.common.util.WEB_CLIENT_ID
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context
) : AuthRepository {
    /*override suspend fun signInWithGoogle(activity: Activity?): Result<AuthUserInfo> {

        if (activity == null) return Result.failure(IllegalArgumentException("Activity cannot be null"))

        return try {

            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId(WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val credentialManager = CredentialManager.create(activity)

            val result = credentialManager.getCredential(
                context = appContext,
                request = request
            )

            val googleCred = GoogleIdTokenCredential.createFrom(result.credential.data)

            return Result.success(
                AuthUserInfo(
                    id = googleCred.id,
                    displayName = googleCred.displayName,
                    email = googleCred.id,
                    photoUrl = googleCred.profilePictureUri?.toString(),
                    token = googleCred.idToken
                )
            )
        } catch (e: NoCredentialException) {
            // No credential exists: prompt user with the full Google Sign-In flow
           // Result.failure(e)
            launchGoogleSignInUI(activity)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }*/

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

/*    private suspend fun launchGoogleSignInUI(activity: Activity): Result<AuthUserInfo> {
        return try {
            val gso = GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
            )
                .requestIdToken(WEB_CLIENT_ID)
                .requestEmail()
                .build()

            val client = GoogleSignIn.getClient(activity, gso)

            val deferred = kotlinx.coroutines.CompletableDeferred<Result<AuthUserInfo>>()

            val signInIntent = client.signInIntent
            activity.startActivityForResult(signInIntent, 1001)

            // You need to handle onActivityResult in your Activity and complete the deferred:
            // deferred.complete(Result.success(userInfo)) or deferred.complete(Result.failure(exception))

            deferred.await()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

     override suspend fun signOut() {

     }

     override fun user(): Flow<AuthUserInfo?> {

     }*/
}