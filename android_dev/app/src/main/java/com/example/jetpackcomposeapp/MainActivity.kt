package com.example.jetpackcomposeapp

import android.os.Bundle
import android.widget.Toast
import androis
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Jacob",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    var uname by remember {mutableStateOf("")}
//    Row(modifier = modifier
//        .fillMaxSize()
//        .padding(10.dp)
//        .)
    Text(
        text = "Hello $name!",
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
    Button(
//        modifier = modifier,
        onClick = {
            Toast.makeText(this, "hello", Toast.LENGTH_LONG).show()
        })
    {Text("Click")}

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeAppTheme {
        Greeting("Jacob", Modifier.width(100.dp))
    }
}