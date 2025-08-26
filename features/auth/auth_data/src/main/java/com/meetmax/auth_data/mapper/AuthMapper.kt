package com.meetmax.auth_data.mapper

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.meetmax.auth_domain.model.AuthUserInfo
import com.meetmax.auth_domain.repository.User
import com.meetmax.database.model.UserEntity
import kotlin.String

fun GoogleSignInAccount.toAuthUserInfo() = AuthUserInfo(
    id = id ?: "",
    displayName = displayName ?: "",
    email = email ?: "",
    photoUrl = photoUrl?.toString(),
    token = idToken ?: ""
)

fun UserEntity.toUser() = User(
    id = id,
    userID = userID ?: "",
    name = name ?: "",
    email = email ?: "",
    photoUrl = photoUrl ?: "",
    token = token ?: ""
)

fun List<UserEntity>.toUserList(): List<User> {
    return this.map { it.toUser() }  // reuse single entity mapper
}