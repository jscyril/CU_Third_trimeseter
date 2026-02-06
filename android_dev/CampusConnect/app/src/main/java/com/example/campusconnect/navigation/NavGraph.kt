package com.example.campusconnect.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.campusconnect.screens.*

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onDrawerClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
        modifier = modifier
    ) {
        // Home Screen
        composable(route = NavRoutes.Home.route) {
            HomeScreen(
                onDrawerClick = onDrawerClick,
                onNavigateToDepartments = {
                    navController.navigate(NavRoutes.Departments.route)
                }
            )
        }

        // Profile Screen
        composable(route = NavRoutes.Profile.route) {
            ProfileScreen(
                onDrawerClick = onDrawerClick
            )
        }

        // Notifications Screen
        composable(route = NavRoutes.Notifications.route) {
            NotificationsScreen(
                onDrawerClick = onDrawerClick
            )
        }

        // Departments Screen
        composable(route = NavRoutes.Departments.route) {
            DepartmentsScreen(
                onEventClick = { departmentName, eventName ->
                    navController.navigate(
                        NavRoutes.EventDetails.createRoute(departmentName, eventName)
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Event Details Screen with arguments
        composable(
            route = NavRoutes.EventDetails.route,
            arguments = listOf(
                navArgument("departmentName") { type = NavType.StringType },
                navArgument("eventName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val departmentName = backStackEntry.arguments?.getString("departmentName") ?: ""
            val eventName = backStackEntry.arguments?.getString("eventName") ?: ""

            EventDetailsScreen(
                departmentName = departmentName,
                eventName = eventName,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}