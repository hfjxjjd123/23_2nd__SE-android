package com.unnamed.mobile.component.button

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamed.mobile.component.model.Blob
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.ButtonModifier
import kotlinx.coroutines.*

@Composable
fun BackButton() {
    Button(
        onClick = {},
        modifier = ButtonModifier
    ){
        Text(text = "(Back Arrow)")
    }
}