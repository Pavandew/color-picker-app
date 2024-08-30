package com.example.cloneapp

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cloneapp.viewModel.ColorViewModel

@Composable
fun ColorPickerScreen(colorViewModel: ColorViewModel = viewModel()) {
    val color by colorViewModel.color.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Color preview box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Red slider
        Slider(
            value = color.red,
            onValueChange = { colorViewModel.updateRed(it) },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )

        // Green slider
        Slider(
            value = color.green,
            onValueChange = { colorViewModel.updateGreen(it) },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )

        // Blue slider
        Slider(
            value = color.blue,
            onValueChange = { colorViewModel.updateBlue(it) },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save button
        Button(onClick = {
            colorViewModel.saveColor()
        }) {
            Text(text = "Save Color")
        }
    }
}
