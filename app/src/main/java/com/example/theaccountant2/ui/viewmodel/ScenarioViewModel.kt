package com.example.theaccountant2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaccountant2.data.model.Scenario
import com.example.theaccountant2.data.model.ScenarioQuestion
import com.example.theaccountant2.data.repository.AppProgressRepository
import com.example.theaccountant2.data.repository.ScenarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ScenarioDisplayMode {
    NARRATIVE,
    QUESTION
}

class ScenarioViewModel(
    private val appProgressRepository: AppProgressRepository,
    private val scenarioRepository: ScenarioRepository
) : ViewModel() {

    private val _currentScenario = MutableStateFlow<Scenario?>(null)
    val currentScenario: StateFlow<Scenario?> = _currentScenario.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _displayMode = MutableStateFlow(ScenarioDisplayMode.NARRATIVE)
    val displayMode: StateFlow<ScenarioDisplayMode> = _displayMode.asStateFlow()

    // Answer selection state
    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    val currentDisplayedQuestion: StateFlow<ScenarioQuestion?> = combine(
        _currentScenario,
        _currentQuestionIndex
    ) { scenario, index ->
        scenario?.questions?.getOrNull(index)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val isAnswerSelected: StateFlow<Boolean> = _selectedAnswerIndex
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val isCurrentAnswerCorrect: StateFlow<Boolean?> = combine(
        currentDisplayedQuestion,
        _selectedAnswerIndex
    ) { question, selectedIdx ->
        if (question != null && selectedIdx != null) {
            question.correctAnswerIndex == selectedIdx
        } else {
            null
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val currentAnswerExplanation: StateFlow<String?> = combine(
        currentDisplayedQuestion,
        isAnswerSelected // Use isAnswerSelected to trigger explanation display
    ) { question, answerSelected ->
        if (question != null && answerSelected) {
            question.explanation
        } else {
            null
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    init {
        viewModelScope.launch {
            appProgressRepository.ensureInitialProgress()
            appProgressRepository.currentDay
                .collect { day ->
                    day?.let {
                        _currentScenario.value = scenarioRepository.getScenarioForDay(it)
                        _currentQuestionIndex.value = 0
                        _selectedAnswerIndex.value = null // Reset selection for new scenario
                        _displayMode.value = ScenarioDisplayMode.NARRATIVE
                    } ?: run {
                        _currentScenario.value = null
                    }
                }
        }
    }

    fun selectAnswer(index: Int) {
        // Only allow selection if not already selected, or allow re-selection
        // For now, allow re-selection. Or prevent if already correct?
        _selectedAnswerIndex.value = index
    }

    fun handleNextAction() {
        when (_displayMode.value) {
            ScenarioDisplayMode.NARRATIVE -> {
                if (_currentScenario.value?.questions?.isNotEmpty() == true) {
                    _selectedAnswerIndex.value = null // Ensure no selection when moving to first question
                    _displayMode.value = ScenarioDisplayMode.QUESTION
                } else {
                    proceedToJournalEntry()
                }
            }
            ScenarioDisplayMode.QUESTION -> {
                // Logic to advance only if answer is correct can be added here if desired
                // For now, we advance regardless, and feedback is shown.
                val questions = _currentScenario.value?.questions
                if (questions != null && _currentQuestionIndex.value < questions.size - 1) {
                    _currentQuestionIndex.value++
                    _selectedAnswerIndex.value = null // Reset selection for next question
                } else {
                    proceedToJournalEntry()
                }
            }
        }
    }

    private fun proceedToJournalEntry() {
        println("Proceeding to journal entry screen (placeholder)")
        // Future: Reset display mode, advance day via appProgressRepository.updateCurrentDay(currentDay + 1)
        // _displayMode.value = ScenarioDisplayMode.NARRATIVE // Or navigate away
    }
}
