package com.example.theaccountant2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SampleViewModel : ViewModel() {
    private val _sampleText = MutableStateFlow("Hello from SampleViewModel!")
    val sampleText: StateFlow<String> = _sampleText

    fun updateText(newText: String) {
        viewModelScope.launch {
            _sampleText.value = newText
        }
    }
}
