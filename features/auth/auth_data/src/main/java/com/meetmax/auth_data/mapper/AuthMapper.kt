package com.meetmax.auth_data.mapper

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.meetmax.auth_domain.model.AuthUserInfo

 fun GoogleSignInAccount.toAuthUserInfo() = AuthUserInfo(
    id = id ?: "",
    displayName = displayName ?: "",
    email = email ?: "",
    photoUrl = photoUrl?.toString(),
    token = idToken ?: ""
)