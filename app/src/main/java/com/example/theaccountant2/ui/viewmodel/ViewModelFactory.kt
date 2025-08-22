package com.example.theaccountant2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theaccountant2.data.repository.AppProgressRepository
import com.example.theaccountant2.data.repository.ScenarioRepository

/**
 * ViewModelFactory for creating ViewModels that require repository dependencies.
 */
class ViewModelFactory(
    private val appProgressRepository: AppProgressRepository,
    private val scenarioRepository: ScenarioRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScenarioViewModel::class.java)) {
            return ScenarioViewModel(appProgressRepository, scenarioRepository) as T
        }
        // Add other ViewModels here as needed
        // if (modelClass.isAssignableFrom(AnotherViewModel::class.java)) {
        //     return AnotherViewModel(otherRepository) as T
        // }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
