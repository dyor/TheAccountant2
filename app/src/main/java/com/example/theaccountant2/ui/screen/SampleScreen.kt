package com.example.theaccountant2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.theaccountant2.ui.viewmodel.SampleViewModel

@Composable
fun SampleScreen(modifier: Modifier = Modifier, sampleViewModel: SampleViewModel = viewModel()) {
    val sampleText by sampleViewModel.sampleText.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = sampleText)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { sampleViewModel.updateText("Button Clicked!") }) {
            Text("Update Text")
        }
    }
}
