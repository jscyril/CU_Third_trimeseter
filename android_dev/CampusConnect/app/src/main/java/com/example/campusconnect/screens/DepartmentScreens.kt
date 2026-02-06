package com.example.campusconnect.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentsScreen(
    onEventClick: (String, String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Departments", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Select a Department",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Department Buttons
            Button(
                onClick = { onEventClick("Computer Science", "AI Workshop 2024") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Computer Science")
            }
            Button(
                onClick = { onEventClick("Electrical Engineering", "Robotics Competition") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Electrical Engineering")
            }
            Button(
                onClick = { onEventClick("Mechanical Engineering", "AutoExpo 2024") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mechanical Engineering")
            }
            Button(
                onClick = { onEventClick("Civil Engineering", "Bridge Design Contest") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Civil Engineering")
            }
            Button(
                onClick = { onEventClick("Business Administration", "Startup Summit") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Business Administration")
            }
        }
    }
}
