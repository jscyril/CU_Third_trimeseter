package com.example.compose_navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Connect With Me",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Instagram Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=YOUR_INSTAGRAM_USERNAME"))
                intent.setPackage("com.instagram.android")
                try {
                    context.startActivity(intent)
                } catch (e: Exception) {
                    // Fallback to web browser if Instagram app is not installed
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/YOUR_INSTAGRAM_USERNAME"))
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Instagram", style = MaterialTheme.typography.titleMedium)
        }

        // LinkedIn Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://profile/YOUR_LINKEDIN_ID"))
                intent.setPackage("com.linkedin.android")
                try {
                    context.startActivity(intent)
                } catch (e: Exception) {
                    // Fallback to web browser if LinkedIn app is not installed
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/YOUR_LINKEDIN_ID"))
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text("LinkedIn", style = MaterialTheme.typography.titleMedium)
        }

        // GitHub Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/YOUR_GITHUB_USERNAME"))
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("GitHub", style = MaterialTheme.typography.titleMedium)
        }

        // About Me Button
        Button(
            onClick = {
                navController.navigate(Screen.AboutMe.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "About Me",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("About Me", style = MaterialTheme.typography.titleMedium)
        }
    }
}
