package com.example.schedulemeet.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.schedulemeet.Destinations
import com.example.schedulemeet.R
import com.example.schedulemeet.presentation.components.PopUpDialog
import com.example.schedulemeet.ui.theme.inverseSurfaceLight
import com.example.schedulemeet.ui.theme.onPrimaryLight
import com.example.schedulemeet.ui.theme.primaryLight
import com.example.schedulemeet.ui.theme.primaryLightMediumContrast
import com.example.schedulemeet.ui.theme.secondaryLight

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginState by loginViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val focusManager = LocalFocusManager.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "SIGN IN",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier,
                color = primaryLightMediumContrast
            )
        }

        OutlinedTextField(
            value = loginState.username,
            onValueChange = { loginViewModel.updateUsername(it) },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            label = {
                Text(text = "Username")
            },
            keyboardOptions =  KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor =   primaryLight,
                unfocusedTextColor = primaryLight,
                focusedBorderColor = secondaryLight,
                focusedLabelColor = secondaryLight,
                unfocusedLabelColor = inverseSurfaceLight,
            )
        )

        OutlinedTextField(
            value = loginState.password,
            onValueChange = { loginViewModel.updatePassword(it) },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            label = {
                Text(text = "Password")
            },
            keyboardOptions =  KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor =   primaryLight,
                unfocusedTextColor = primaryLight,
                focusedBorderColor = secondaryLight,
                focusedLabelColor = secondaryLight,
                unfocusedLabelColor = inverseSurfaceLight,
            )
        )

        if (loginState.state) {
            PopUpDialog(
                context = LocalContext.current,
                message = "Wrong Credentials",
                image = R.drawable.catmakingfun,
                onConfirm = {}
            ) {
                loginViewModel.updateState(false)
            }
        }

        Button(
            onClick = {
                val check = loginViewModel.onLoginClick()
                if (check) {
                    navController.navigate(Destinations.SCHEDULE.name)
                } else {
                    loginViewModel.showGIF()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryLight,
                contentColor = onPrimaryLight
            )
        ) {
            Text(text = "SIGN IN")
        }
    }
}