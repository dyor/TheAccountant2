package com.example.theaccountant2.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.theaccountant2.R

sealed class BottomNavItem(
    val route: String,
    val titleResId: Int,
    val icon: ImageVector
) {
    object Play : BottomNavItem(
        route = "play_graph", // This will be a nested graph
        titleResId = R.string.bottom_nav_play, // Assumes R.string.bottom_nav_play = "Play"
        icon = Icons.Filled.PlayArrow
    )

    object Settings : BottomNavItem(
        route = "settings",
        titleResId = R.string.bottom_nav_settings, // Assumes R.string.bottom_nav_settings = "Settings"
        icon = Icons.Filled.Settings
    )
}
