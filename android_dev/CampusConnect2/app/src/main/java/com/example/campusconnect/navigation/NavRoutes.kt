package com.example.campusconnect.navigation

/**
 * Sealed class representing all navigation routes in the app
 */
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Profile : NavRoutes("profile")
    object Notifications : NavRoutes("notifications")
    object Departments : NavRoutes("departments")
    object EventDetails : NavRoutes("event_details/{departmentName}") {
        fun createRoute(departmentName: String) = "event_details/$departmentName"
    }
}
