package com.example.revelations_cia.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.revelations_cia.data.RegistrationData
import com.example.revelations_cia.screens.ConfirmationScreen
import com.example.revelations_cia.screens.EventSelectionScreen
import com.example.revelations_cia.screens.RegistrationScreen
import com.example.revelations_cia.screens.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Registration : Screen("registration")
    object EventSelection : Screen("event_selection")
    object Confirmation : Screen("confirmation")
}

@Composable
fun NavGraph(navController: NavHostController) {
    val registrationData = remember { RegistrationData() }

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToRegistration = {
                    navController.navigate(Screen.Registration.route)
                }
            )
        }

        composable(Screen.Registration.route) {
            RegistrationScreen(
                onNavigateToEventSelection = { name, mobile, email, college ->
                    registrationData.name = name
                    registrationData.mobile = mobile
                    registrationData.email = email
                    registrationData.college = college
                    navController.navigate(Screen.EventSelection.route)
                }
            )
        }

        composable(Screen.EventSelection.route) {
            EventSelectionScreen(
                onNavigateToConfirmation = { groupName, numberOfEvents ->
                    registrationData.groupName = groupName
                    registrationData.numberOfEvents = numberOfEvents
                    navController.navigate(Screen.Confirmation.route)
                }
            )
        }

        composable(Screen.Confirmation.route) {
            ConfirmationScreen(
                name = registrationData.name,
                mobile = registrationData.mobile,
                email = registrationData.email,
                college = registrationData.college,
                groupName = registrationData.groupName,
                numberOfEvents = registrationData.numberOfEvents
            )
        }
    }
}
