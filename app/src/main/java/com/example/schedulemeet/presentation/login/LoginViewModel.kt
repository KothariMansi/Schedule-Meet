package com.example.schedulemeet.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    var uiState = _uiState.asStateFlow()

    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onLoginClick(): Boolean {
        return uiState.value.username == "Komal" && uiState.value.password == "143"
    }

    fun updateState(state: Boolean) {
        _uiState.update { it.copy(state = state) }
    }
    fun showGIF() {
        updateState(true)
    }

}

data class LoginState(
    val username: String = "",
    val password: String = "",
    val state: Boolean = false
)