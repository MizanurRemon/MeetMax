package com.meetmax.designsystem.utils


import com.meetmax.common.model.Comment
import kotlin.Int
import kotlin.String
import com.meetmax.designsystem.R as DesignSystemR

val CEREMONY_USER_LIST = listOf(
    DesignSystemR.drawable.ic_avatar_1,
    DesignSystemR.drawable.ic_avatar_2,
    DesignSystemR.drawable.ic_avatar_3,
    DesignSystemR.drawable.ic_avatar_4,
    DesignSystemR.drawable.ic_avatar_5,
    DesignSystemR.drawable.ic_afrim,
    DesignSystemR.drawable.ic_eduardo_3
)

val PHOTOGRAPHY_USER_LIST = listOf(
    DesignSystemR.drawable.ic_avatar_4,
    DesignSystemR.drawable.ic_avatar_5,
    DesignSystemR.drawable.ic_avatar_1,
    DesignSystemR.drawable.ic_avatar_2,
    DesignSystemR.drawable.ic_avatar_3,
    DesignSystemR.drawable.ic_afrim,
    DesignSystemR.drawable.ic_eduardo_3,
    DesignSystemR.drawable.ic_afrim,
    DesignSystemR.drawable.ic_eduardo_3,
    DesignSystemR.drawable.ic_afrim,
    DesignSystemR.drawable.ic_eduardo_3
)

val REACTED_PERSONS = listOf(
    DesignSystemR.drawable.ic_avatar_4,
    DesignSystemR.drawable.ic_avatar_5,
    DesignSystemR.drawable.ic_avatar_1,
    DesignSystemR.drawable.ic_avatar_2,
    DesignSystemR.drawable.ic_avatar_3,
    DesignSystemR.drawable.ic_afrim,
    DesignSystemR.drawable.ic_eduardo_3,
    DesignSystemR.drawable.ic_afrim,
    DesignSystemR.drawable.ic_eduardo_3,
    DesignSystemR.drawable.ic_afrim,
    DesignSystemR.drawable.ic_eduardo_3
)

val COMMENTS_POST_1 = listOf(
    Comment(
        name = "Stefen Joe",
        avatar = DesignSystemR.drawable.ic_avatar_2,
        createdTime = "1h",
        replies = emptyList(),
        comment = "Looks amazing and breathtaking. Been there, beautiful!"
    )
)

val COMMENT_POST_2 = listOf(
    Comment(
        name = "Stefen Joe",
        avatar = DesignSystemR.drawable.ic_eduardo_3,
        createdTime = "2h",
        replies = listOf(
            Comment(
                name = "Whitechapel Gallery",
                avatar = DesignSystemR.drawable.ic_avatar_2,
                createdTime = "1h",
                replies = emptyList(),
                comment = "Thank You"
            )
        ),
        comment = "Looks amazing and breathtaking. Been there, beautiful!"
    ),
    Comment(
        name = "John Abraham",
        avatar = DesignSystemR.drawable.ic_avatar_4,
        createdTime = "3h",
        replies = emptyList(),
        comment = "Nice"
    ),
    Comment(
        name = "Priyanka Chopra",
        avatar = DesignSystemR.drawable.ic_avatar_1,
        createdTime = "4h",
        replies = emptyList(),
        comment = "Wow!"
    )
)