package com.example.campusconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.campusconnect.components.BottomNavBar
import com.example.campusconnect.components.NavigationDrawerContent
import com.example.campusconnect.navigation.NavGraph
import com.example.campusconnect.navigation.NavRoutes
import com.example.campusconnect.ui.theme.CampusConnectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusConnectTheme {
                CampusConnectApp()
            }
        }
    }
}

@Composable
fun CampusConnectApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Get current route for highlighting selected items
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define screens that should show bottom navigation
    val bottomNavScreens = listOf(
        NavRoutes.Home.route,
        NavRoutes.Notifications.route,
        NavRoutes.Profile.route
    )

    // Check if current screen should show bottom nav
    val showBottomNav = currentRoute in bottomNavScreens

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerContent(
                currentRoute = currentRoute,
                onItemClick = { route ->
                    scope.launch {
                        drawerState.close()
                    }
                    // Navigate to selected screen
                    navController.navigate(route) {
                        // Pop up to home to avoid building up a large back stack
                        popUpTo(NavRoutes.Home.route) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                onLogoutClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    // Show logout toast (in real app, handle logout logic)
                    Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT).show()
                }
            )
        },
        gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                // Only show bottom nav on main screens
                if (showBottomNav) {
                    BottomNavBar(
                        currentRoute = currentRoute,
                        onItemClick = { route ->
                            navController.navigate(route) {
                                popUpTo(NavRoutes.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                onDrawerClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    }
}
