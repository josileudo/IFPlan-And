package com.app.ifplan_leite.core.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    var title: String,
    var route: Any,
    var selectedIcon: ImageVector,
    var unselectedIcon: ImageVector,
)
