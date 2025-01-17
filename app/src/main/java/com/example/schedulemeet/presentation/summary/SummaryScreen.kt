package com.example.schedulemeet.presentation.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schedulemeet.R
import com.example.schedulemeet.SummaryData
import com.example.schedulemeet.presentation.components.GIFImage
import com.example.schedulemeet.ui.theme.onPrimaryContainerLight
import com.example.schedulemeet.ui.theme.primaryLight

@Composable
fun SummaryScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GIFImage(modifier = Modifier.size(150.dp),context = LocalContext.current, image = R.drawable.love)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Ok. You are meeting me on ${SummaryData.date}.",
                color = onPrimaryContainerLight,
                fontWeight = FontWeight.SemiBold
            )

        }
        Spacer(modifier = Modifier.height(260.dp))
        Text(
            text = "Screenshot this and Send Me.",
            color = primaryLight
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
   

}