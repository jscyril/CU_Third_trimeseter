package com.example.activity_lifecycle_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.activity_lifecycle_app.ui.theme.Activity_lifecycle_appTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    private val TAG = "ActivityLifecycle"
    private val PREFS_NAME = "lifecycle_logs"
    private val LOGS_KEY = "logs"
    private val lifecycleLogs = mutableStateListOf<String>()

    private fun loadLogs() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedLogsString = prefs.getString(LOGS_KEY, "") ?: ""
        if (savedLogsString.isNotEmpty()) {
            val savedLogs = savedLogsString.split("|||")
            lifecycleLogs.addAll(savedLogs)
        }
    }

    private fun saveLogs() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val logsString = lifecycleLogs.joinToString("|||")
        prefs.edit().putString(LOGS_KEY, logsString).apply()
    }

    private fun addLog(method: String) {
        val timestamp = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()).format(Date())
        val logMessage = "[$timestamp] $method"
        lifecycleLogs.add(logMessage)
        saveLogs()
        Log.d(TAG, method)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        loadLogs()
        addLog("onCreate()")
        enableEdgeToEdge()
        setContent {
            Activity_lifecycle_appTheme {
                var showWelcomeScreen by remember { mutableStateOf(true) }

                if (showWelcomeScreen) {
                    WelcomeScreen(
                        onGoClick = {
                            showWelcomeScreen = false
                        }
                    )
                } else {
                    MainScreen(
                        lifecycleLogs = lifecycleLogs,
                        onClearLogs = {
                            lifecycleLogs.clear()
                            saveLogs()
                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        addLog("onStart()")
    }

    override fun onResume() {
        super.onResume()
        addLog("onResume()")
    }

    override fun onPause() {
        super.onPause()
        addLog("onPause()")
    }

    override fun onStop() {
        super.onStop()
        addLog("onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        addLog("onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        addLog("onDestroy()")
    }
}

@Composable
fun MainScreen(lifecycleLogs: List<String>, onClearLogs: () -> Unit) {
    val listState = rememberLazyListState()

    LaunchedEffect(lifecycleLogs.size) {
        if (lifecycleLogs.isNotEmpty()) {
            listState.animateScrollToItem(lifecycleLogs.size - 1)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Activity Lifecycle Logger",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Total Events: ${lifecycleLogs.size}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Button(
                        onClick = onClearLogs,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    ) {
                        Text("Clear Logs")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            if (lifecycleLogs.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No lifecycle events yet...",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(lifecycleLogs) { log ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        ) {
                            Text(
                                text = log,
                                modifier = Modifier.padding(16.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onGoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_screen_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 48.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "2547121 - Jacob Sebastian Cyril",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = onGoClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Go",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Activity_lifecycle_appTheme {
        MainScreen(
            lifecycleLogs = listOf(
                "[12:34:56.789] onCreate()",
                "[12:34:56.890] onStart()",
                "[12:34:56.901] onResume()"
            ),
            onClearLogs = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    Activity_lifecycle_appTheme {
        WelcomeScreen(onGoClick = {})
    }
}
