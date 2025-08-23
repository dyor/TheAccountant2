package com.example.theaccountant2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaccountant2.data.model.Scenario
import com.example.theaccountant2.data.model.ScenarioQuestion
import com.example.theaccountant2.data.repository.AppProgressRepository
import com.example.theaccountant2.data.repository.ScenarioRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn // Make sure this is imported
import kotlinx.coroutines.launch

enum class ScenarioDisplayMode {
    NARRATIVE,
    QUESTION
}

class ScenarioViewModel(
    private val appProgressRepository: AppProgressRepository,
    private val scenarioRepository: ScenarioRepository
) : ViewModel() {

    // --- Start of new/modified code ---
    /**
     * Represents the current day of the year in the simulation.
     * Defaults to 0 if not yet determined or before the simulation starts for the day.
     */
    val currentDayInYear: StateFlow<Int> = appProgressRepository.currentDay
        .map { day -> day ?: 0 } // Map null from repository to 0
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0 // Initial value for the StateFlow
        )
    // --- End of new/modified code ---

    private val _currentScenario = MutableStateFlow<Scenario?>(null)
    val currentScenario: StateFlow<Scenario?> = _currentScenario.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _displayMode = MutableStateFlow(ScenarioDisplayMode.NARRATIVE)
    val displayMode: StateFlow<ScenarioDisplayMode> = _displayMode.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    // Navigation events
    private val _navigateToJournalEntry = MutableSharedFlow<String>() // Changed from Unit to String
    val navigateToJournalEntry: SharedFlow<String> = _navigateToJournalEntry.asSharedFlow() // Changed from Unit to String

    private val _navigateToIncomeStatement = MutableSharedFlow<Unit>()
    val navigateToIncomeStatement: SharedFlow<Unit> = _navigateToIncomeStatement.asSharedFlow()

    private val _navigateToBalanceSheet = MutableSharedFlow<Unit>()
    val navigateToBalanceSheet: SharedFlow<Unit> = _navigateToBalanceSheet.asSharedFlow()


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
        isAnswerSelected // Only show explanation if an answer is selected
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
            // The existing collection of appProgressRepository.currentDay can still
            // be used for its original purpose of loading scenarios.
            appProgressRepository.currentDay
                .collect { day -> // This 'day' is from the repository (Flow<Int?>)
                    // The 'currentDayInYear' StateFlow above will also update based on this.
                    day?.let { currentDayValue -> // Use currentDayValue to avoid confusion with the flow
                        _currentScenario.value = scenarioRepository.getScenarioForDay(currentDayValue)
                        _currentQuestionIndex.value = 0
                        _selectedAnswerIndex.value = null
                        _displayMode.value = ScenarioDisplayMode.NARRATIVE
                    } ?: run {
                        _currentScenario.value = null
                    }
                }
        }
    }

    fun selectAnswer(index: Int) {
        // Only allow selecting an answer if one hasn't been selected yet for the current question attempt
        if (_selectedAnswerIndex.value == null) {
            _selectedAnswerIndex.value = index
        }
    }

    fun handleNextAction() {
        when (_displayMode.value) {
            ScenarioDisplayMode.NARRATIVE -> {
                if (_currentScenario.value?.questions?.isNotEmpty() == true) {
                    _selectedAnswerIndex.value = null // Ensure fresh state for question
                    _displayMode.value = ScenarioDisplayMode.QUESTION
                } else {
                    proceedToJournalEntry()
                }
            }
            ScenarioDisplayMode.QUESTION -> {
                // This action is now only for advancing if the answer was correct,
                // or if it's the last question (leading to journal entry).
                // The "Try Again" case will be handled by a different button action in the UI.
                if (isCurrentAnswerCorrect.value == true) {
                    val questions = _currentScenario.value?.questions
                    if (questions != null && _currentQuestionIndex.value < questions.size - 1) {
                        _currentQuestionIndex.value++
                        _selectedAnswerIndex.value = null // Reset for the next question
                    } else {
                        proceedToJournalEntry()
                    }
                } else if (isCurrentAnswerCorrect.value == false) {
                    // This case should ideally be handled by the UI calling retryCurrentQuestion()
                    // If somehow handleNextAction is called with a wrong answer,
                    // we might choose to do nothing or log an unexpected state.
                    // For now, let's assume UI directs to retryCurrentQuestion().
                } else {
                    // No answer selected yet, do nothing (button should be disabled or have different text)
                }
            }
        }
    }

    fun retryCurrentQuestion() {
        _selectedAnswerIndex.value = null
    }

    private fun proceedToJournalEntry() {
        viewModelScope.launch {
            val narrative = _currentScenario.value?.narrative ?: ""
            _navigateToJournalEntry.emit(narrative)
        }
    }

    fun onNavigateToIncomeStatementClicked() {
        viewModelScope.launch {
            _navigateToIncomeStatement.emit(Unit)
        }
    }

    fun onNavigateToBalanceSheetClicked() {
        viewModelScope.launch {
            _navigateToBalanceSheet.emit(Unit)
        }
    }
}
