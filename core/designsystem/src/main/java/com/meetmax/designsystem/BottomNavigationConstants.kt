package com.meetmax.designsystem

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.meetmax.common.R as CommonR
import com.meetmax.designsystem.R as DesignSystemR

data class BottomNavigationItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
    // val isSelected: Boolean = false
)

val BOTTOM_NAVIGATION_ITEM = listOf(
    BottomNavigationItem(
        title = CommonR.string.feed,
        icon = DesignSystemR.drawable.ic_feed,
        route = BottomNavRoute.FEED,
    ),
    BottomNavigationItem(
        title = CommonR.string.my_community,
        icon = DesignSystemR.drawable.ic_community,
        route = BottomNavRoute.MY_COMMUNITY,
    ),
    BottomNavigationItem(
        title = CommonR.string.explore,
        icon = DesignSystemR.drawable.ic_explore,
        route = BottomNavRoute.EXPLORE,
    ),
    BottomNavigationItem(
        title = CommonR.string.notification,
        icon = DesignSystemR.drawable.ic_bell,
        route = BottomNavRoute.NOTIFICATION,
    ),
    BottomNavigationItem(
        title = CommonR.string.settings,
        icon = DesignSystemR.drawable.ic_setting,
        route = BottomNavRoute.SETTING,
    )
)

object BottomNavRoute {
    const val FEED = "feed"
    const val MY_COMMUNITY = "my_community"
    const val EXPLORE = "explore"
    const val NOTIFICATION = "notification"
    const val SETTING = "setting"

}