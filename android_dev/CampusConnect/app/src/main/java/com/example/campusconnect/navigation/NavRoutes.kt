package com.example.campusconnect.navigation
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Profile : NavRoutes("profile")
    object Notifications : NavRoutes("notifications")
    object Departments : NavRoutes("departments")

    object EventDetails : NavRoutes("event_details/{departmentName}/{eventName}") {
        fun createRoute(departmentName: String, eventName: String): String {
            return "event_details/$departmentName/$eventName"
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val badgeCount: Int? = null
)

data class DrawerNavItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val isLogout: Boolean = false
)