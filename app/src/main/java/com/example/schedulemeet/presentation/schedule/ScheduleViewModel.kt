package com.example.schedulemeet.presentation.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScheduleViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleState())
    var uiState = _uiState.asStateFlow()

    fun updateShowDatePicker(show: Boolean) {
        _uiState.update { it.copy(showDatePicker = show) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateDate(date: String) {
        _uiState.update { it.copy(date = date) }
    }

}

data class ScheduleState(
    val showDatePicker: Boolean = true,
    val date: String = "",
    val type: Int = 0
)