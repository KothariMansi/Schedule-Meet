package com.example.schedulemeet

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schedulemeet.presentation.front.FrontPageScreen
import com.example.schedulemeet.presentation.login.LoginScreen
import com.example.schedulemeet.presentation.schedule.ScheduleScreen
import com.example.schedulemeet.presentation.summary.SummaryScreen
import com.example.schedulemeet.ui.theme.ScheduleMeetTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
    SUMMARY,
    START
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleMeetApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.START.name) {
        composable(Destinations.START.name){
            FrontPageScreen(navController)
        }
        composable(Destinations.LOGIN.name){
            LoginScreen(navController)
        }
        composable(Destinations.SCHEDULE.name){
           ScheduleScreen(navController)
        }
        composable(Destinations.SUMMARY.name){
            SummaryScreen()
        }
    }
}