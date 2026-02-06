package com.example.campusconnect.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onDrawerClick: () -> Unit,
    onNavigateToDepartments: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Campus Connect", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onDrawerClick) {
                        Icon(Icons.Filled.Menu, contentDescription = "Open Drawer")
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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to Campus Connect!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your campus hub for events and departments",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onNavigateToDepartments) {
                Text("Browse Departments")
            }
        }
    }
}
