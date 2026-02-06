package com.example.compose_navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AboutMe : Screen("about_me")
}
