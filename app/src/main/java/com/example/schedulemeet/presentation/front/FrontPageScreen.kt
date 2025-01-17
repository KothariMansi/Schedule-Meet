package com.example.schedulemeet.presentation.front

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schedulemeet.Destinations
import com.example.schedulemeet.ui.theme.onPrimaryLight
import com.example.schedulemeet.ui.theme.onSecondaryContainerLight
import com.example.schedulemeet.ui.theme.primaryLight

@Composable
fun FrontPageScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "We haven't met since last year. So, I made this app for you to schedule time. So we can meet up.",
            color = onSecondaryContainerLight
            ,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { navController.navigate(Destinations.LOGIN.name) },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryLight,
                contentColor = onPrimaryLight
            ),
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = "Let's Go!",
            )
        }
    }
}