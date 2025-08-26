package com.meetmax.designsystem.data

import com.meetmax.common.model.PostResponse
import com.meetmax.designsystem.R as DesignSystemR
import com.meetmax.designsystem.utils.COMMENTS_POST_1
import com.meetmax.designsystem.utils.COMMENT_POST_2
import com.meetmax.designsystem.utils.REACTED_PERSONS

val POSTS = listOf(
    PostResponse(
        name = "Sepural Gallery",
        avatar = DesignSystemR.drawable.ic_sephural,
        visibility = "public",
        description = "",
        createdTimeAgo = "15h",
        shareCount = 15,
        postImages = listOf(
            DesignSystemR.drawable.ic_post_image_1
        ),
        isReacted = true,
        reactedPersons = REACTED_PERSONS,
        comments = COMMENTS_POST_1,
    ),

    PostResponse(
        name = "Prothinidi Thomas",
        avatar = DesignSystemR.drawable.ic_avatar_3,
        visibility = "public",
        description = "If you think adventure is dangerous, try routine, it’s lethal Paulo Coelho! Good morning all friends.",
        createdTimeAgo = "2d",
        shareCount = 5,
        postImages = listOf(
            DesignSystemR.drawable.ic_post_image_1,
            DesignSystemR.drawable.ic_post_image_2,
            DesignSystemR.drawable.ic_post_image_3,
            DesignSystemR.drawable.ic_post_image_4
        ),
        isReacted = true,
        reactedPersons = REACTED_PERSONS,
        comments = COMMENT_POST_2,
    )
)