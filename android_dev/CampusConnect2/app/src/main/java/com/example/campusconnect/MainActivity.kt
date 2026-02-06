package com.example.campusconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.campusconnect.navigation.AppNavGraph
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

// Data class for Bottom Navigation items
data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

// Data class for Drawer Navigation items
data class DrawerNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String?,
    val isLogout: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusConnectApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Bottom Navigation items
    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, NavRoutes.Home.route),
        BottomNavItem("Notifications", Icons.Default.Notifications, NavRoutes.Notifications.route),
        BottomNavItem("Profile", Icons.Default.Person, NavRoutes.Profile.route)
    )

    // Drawer Navigation items
    val drawerItems = listOf(
        DrawerNavItem("Home", Icons.Default.Home, NavRoutes.Home.route),
        DrawerNavItem("Departments", Icons.Default.List, NavRoutes.Departments.route),
        DrawerNavItem("Profile", Icons.Default.Person, NavRoutes.Profile.route),
        DrawerNavItem("Logout", Icons.AutoMirrored.Filled.ExitToApp, null, isLogout = true)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Determine if we should show bottom nav (hide on EventDetails and Departments)
    val showBottomNav = currentDestination?.route?.let { route ->
        route in listOf(NavRoutes.Home.route, NavRoutes.Notifications.route, NavRoutes.Profile.route)
    } ?: true

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentDestination?.route == NavRoutes.Home.route,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Campus Connect",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            scope.launch { drawerState.close() }
                            if (item.isLogout) {
                                // Handle logout - for demo, just navigate to home
                                navController.navigate(NavRoutes.Home.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                item.route?.let { route ->
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (showBottomNav) {
                    NavigationBar {
                        bottomNavItems.forEach { item ->
                            NavigationBarItem(
                                icon = { Icon(item.icon, contentDescription = item.title) },
                                label = { Text(item.title) },
                                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                onMenuClick = {
                    scope.launch { drawerState.open() }
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}