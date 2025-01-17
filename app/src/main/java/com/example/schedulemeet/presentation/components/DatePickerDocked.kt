package com.example.schedulemeet.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.schedulemeet.Destinations
import com.example.schedulemeet.SummaryData
import com.example.schedulemeet.ui.theme.inverseSurfaceLight
import com.example.schedulemeet.ui.theme.primaryLight
import com.example.schedulemeet.ui.theme.secondaryLight
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    modifier: Modifier = Modifier,
    navController: NavController,
    showDatePicker: Boolean,
    updateShowDatePicker: (Boolean) -> Unit,
    updateDate: (String) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    val millis = datePickerState.selectedDateMillis

    val selectedDate: String = millis?.let { convertMillisToDate(it) } ?: ""
    var showPopup by remember { mutableStateOf(false) }
    var option by remember { mutableIntStateOf(0) }

    LaunchedEffect(selectedDate) {
        if (selectedDate.isNotEmpty() && !showPopup) {
            updateDate(selectedDate)
            val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

            try {
                val selectedDateNew = LocalDate.parse(selectedDate, formatter)
                val startDate = LocalDate.of(2025, 1, 1)
                val endDate = LocalDate.of(2025, 2, 28)

                when {
                    selectedDateNew.dayOfWeek != DayOfWeek.SUNDAY -> {
                        option = 2
                        showPopup = true
                    }
                    selectedDateNew in startDate..endDate -> {
                        option = 1
                        showPopup = true
                    }
                    else -> {
                        option = 4
                        showPopup = true
                    }
                }
            } catch (e: Exception) {
                println("Error parsing date: ${e.message}")
            }
        }
    }

    if (showPopup) {
        GIFsSelection(
            option = option, onDismiss = {
                showPopup = false
                option = 0
            }
        ) {
            SummaryData.date = selectedDate
            navController.navigate(Destinations.SUMMARY.name)
        }
    }

    val calenderDate = Calendar.getInstance().apply {
        add(Calendar.DAY_OF_YEAR, -1)
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }.timeInMillis

    Box(
        modifier = modifier
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {  },
            label = { Text("Schedule Meet") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    updateShowDatePicker(!showDatePicker)
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = modifier
                .height(64.dp)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor =   primaryLight,
                unfocusedTextColor = primaryLight,
                focusedBorderColor = secondaryLight,
                focusedLabelColor = secondaryLight,
                unfocusedLabelColor = inverseSurfaceLight,
            )
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { updateShowDatePicker(false) },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false,
                        dateValidator = { date ->
                            date > calenderDate // Disable dates after today
                        }
                    )
                }
            }
        }
    }
}


fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
