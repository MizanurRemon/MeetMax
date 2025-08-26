package com.meetmax.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userID : String?,
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val token: String?
)