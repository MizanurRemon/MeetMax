package com.meetmax.feed_presentation

import com.meetmax.designsystem.R as DesignSystemR


data class User(
    val name: String,
    val avatar: Int,
    val isMyself: Boolean = false
)

val USERS = listOf(
    User(
        name = "Saleh",
        avatar = DesignSystemR.drawable.ic_person_avatar,
        isMyself = true
    ),
    User(
        name = "Edilson",
        avatar = DesignSystemR.drawable.ic_edilson,
    ),
    User(
        name = "Afrim",
        avatar = DesignSystemR.drawable.ic_afrim,
    ),
    User(
        name = "Eduardo",
        avatar = DesignSystemR.drawable.ic_eduardo_1
    ),
    User(
        name = "Eduardo",
        avatar = DesignSystemR.drawable.ic_eduardo_2,
    ),
    User(
        name = "Eduardo",
        avatar = DesignSystemR.drawable.ic_eduardo_3,
    ),
    User(
        name = "Edilson",
        avatar = DesignSystemR.drawable.ic_edilson,
    ),
    User(
        name = "Afrim",
        avatar = DesignSystemR.drawable.ic_afrim
    ),

    User(
        name = "Edilson",
        avatar = DesignSystemR.drawable.ic_edilson,
    ),
    User(
        name = "Afrim",
        avatar = DesignSystemR.drawable.ic_afrim,
    ),
    User(
        name = "Eduardo",
        avatar = DesignSystemR.drawable.ic_eduardo_1
    ),
    User(
        name = "Eduardo",
        avatar = DesignSystemR.drawable.ic_eduardo_2,
    ),
    User(
        name = "Eduardo",
        avatar = DesignSystemR.drawable.ic_eduardo_3,
    ),
    User(
        name = "Edilson",
        avatar = DesignSystemR.drawable.ic_edilson,
    ),
    User(
        name = "Afrim",
        avatar = DesignSystemR.drawable.ic_afrim
    ),

)