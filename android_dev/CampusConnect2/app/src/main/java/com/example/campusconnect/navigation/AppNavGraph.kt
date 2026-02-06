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
fun AppNavGraph(
    navController: NavHostController,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
        modifier = modifier
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(onMenuClick = onMenuClick)
        }

        composable(NavRoutes.Profile.route) {
            ProfileScreen()
        }

        composable(NavRoutes.Notifications.route) {
            NotificationsScreen()
        }

        composable(NavRoutes.Departments.route) {
            DepartmentsScreen(
                onBackClick = { navController.popBackStack() },
                onDepartmentClick = { departmentName ->
                    navController.navigate(NavRoutes.EventDetails.createRoute(departmentName))
                }
            )
        }

        composable(
            route = NavRoutes.EventDetails.route,
            arguments = listOf(
                navArgument("departmentName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val departmentName = backStackEntry.arguments?.getString("departmentName") ?: ""
            EventDetailsScreen(
                departmentName = departmentName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
