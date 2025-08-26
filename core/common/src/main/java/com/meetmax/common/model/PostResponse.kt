package com.meetmax.common.model


data class PostResponse(
    val name: String,
    val avatar: Int,
    val visibility: String,
    val description: String?,
    val createdTimeAgo: String,
    val shareCount: Int,
    val postImages: List<Int> = emptyList(),
    val isReacted: Boolean = false,
    val reactedPersons: List<Int> = emptyList(),
    val comments: List<Comment> = emptyList()
)

data class Comment(
    val name: String,
    val avatar: Int,
    val createdTime: String,
    val comment: String,
    val replies: List<Comment> = emptyList()
)