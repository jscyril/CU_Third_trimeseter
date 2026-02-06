package com.example.gridrowcolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gridrowcolumn.ui.theme.GridRowColumnTheme

class MainActivity : ComponentActivity() {

    companion object {
        // Tourist Places of Maharashtra
        val items = listOf(
            Item("Gateway of India", android.R.drawable.ic_menu_gallery),
            Item("Ajanta Caves", android.R.drawable.ic_menu_camera),
            Item("Ellora Caves", android.R.drawable.ic_menu_compass),
            Item("Shaniwar Wada", android.R.drawable.ic_menu_mylocation),
            Item("Marine Drive", android.R.drawable.ic_menu_mapmode),
            Item("Lonavala", android.R.drawable.ic_menu_view),
            Item("Mahabaleshwar", android.R.drawable.ic_menu_slideshow),
            Item("Shirdi", android.R.drawable.ic_menu_day),
            Item("Alibaug Beach", android.R.drawable.ic_menu_info_details),
            Item("Elephanta Caves", android.R.drawable.ic_menu_recent_history),
            Item("Kanheri Caves", android.R.drawable.ic_menu_search),
            Item("Panchgani", android.R.drawable.ic_menu_zoom)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridRowColumnTheme {
                TouristPlacesApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristPlacesApp() {
    var selectedScreen by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tourist Places of Maharashtra",
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Navigation Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { selectedScreen = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedScreen == 0)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Column")
                }
                Button(
                    onClick = { selectedScreen = 1 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedScreen == 1)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Row")
                }
                Button(
                    onClick = { selectedScreen = 2 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedScreen == 2)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Grid")
                }
            }

            // Display selected screen
            when (selectedScreen) {
                0 -> LazyColumnScreen()
                1 -> LazyRowScreen()
                2 -> LazyGridScreen()
            }
        }
    }
}