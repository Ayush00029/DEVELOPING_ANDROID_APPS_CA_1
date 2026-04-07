package com.example.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppUI()
        }
    }
}

@Composable
fun AppUI() {
    var isSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        isSplash = false
    }

    if (isSplash) {
        SplashScreen()
    } else {
        DashboardScreen()
    }
}


@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun DashboardScreen() {

    val context = LocalContext.current
    val data = "Hello from Splash 🚀"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = data)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }) {
            Text("Go to Second Activity")
        }
    }
}
