package com.example.schedulemeet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schedulemeet.presentation.login.LoginScreen
import com.example.schedulemeet.ui.theme.ScheduleMeetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleMeetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScheduleMeetApp()
                }
            }
        }
    }
}


enum class Destinations{
    LOGIN,
    SCHEDULE,
    SUMMARY
}

@Composable
fun ScheduleMeetApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.LOGIN.name) {
        composable(Destinations.LOGIN.name){
            LoginScreen(navController)
        }
        composable(Destinations.SCHEDULE.name){
            Text(text = "SCHEDULE")
        }
        composable(Destinations.SUMMARY.name){
            Text(text = "SUMMARY")
        }
    }
}