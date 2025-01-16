package com.example.schedulemeet.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.schedulemeet.R

@Composable
fun GIFsSelection(option: Int, onDismiss:()-> Unit, onConfirm:() -> Unit) {
    val context = LocalContext.current
    when(option) {
        1 -> {
            PopUpDialog(
                context = context,
                message = "Exams",
                image = R.drawable.sad,
                onConfirm = {  }) {
                onDismiss()
            }
        }
        2 -> {
            PopUpDialog(
                context = context,
                message = "No Sunday",
                image = R.drawable.sad2,
                onConfirm = {}) {
                onDismiss()
            }
        }
        3 -> {
            PopUpDialog(
                context = context,
                message = "Yeah",
                image = R.drawable.love,
                onConfirm = {}) {
                onDismiss()
            }
        }
        4 -> {
            PopUpDialog(
                context = context,
                message = "Finally!",
                image = R.drawable.letsgo,
                show = true,
                onConfirm = { onConfirm() }) {
                onDismiss()
            }
        }
    }

}