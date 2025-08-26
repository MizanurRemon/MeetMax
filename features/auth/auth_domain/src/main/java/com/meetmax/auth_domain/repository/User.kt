package com.meetmax.auth_domain.repository

data class User(
    val id: Int,
    val userID: String,
    val name: String,
    val email: String,
    val photoUrl: String,
    val token: String
)
