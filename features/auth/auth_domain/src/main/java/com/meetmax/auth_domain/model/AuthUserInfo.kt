package com.meetmax.auth_domain.model

data class AuthUserInfo(
    val id: String,
    val displayName: String?,
    val email: String?,
    val photoUrl: String?,
    val token: String?
)
