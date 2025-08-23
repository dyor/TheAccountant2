package com.example.theaccountant2.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theaccountant2.data.model.ScenarioQuestion
import com.example.theaccountant2.ui.viewmodel.ScenarioDisplayMode
import com.example.theaccountant2.ui.viewmodel.ScenarioViewModel

@Composable
fun ScenarioScreen(
    modifier: Modifier = Modifier,
    scenarioViewModel: ScenarioViewModel
) {
    val scenario by scenarioViewModel.currentScenario.collectAsState()
    val displayMode by scenarioViewModel.displayMode.collectAsState()
    val currentQuestion by scenarioViewModel.currentDisplayedQuestion.collectAsState()
    val currentQuestionIndex by scenarioViewModel.currentQuestionIndex.collectAsState()

    val selectedAnswerIndex by scenarioViewModel.selectedAnswerIndex.collectAsState()
    val isAnswerSelected by scenarioViewModel.isAnswerSelected.collectAsState()
    val isCurrentAnswerCorrect by scenarioViewModel.isCurrentAnswerCorrect.collectAsState() // This is already the Boolean?
    val currentAnswerExplanation by scenarioViewModel.currentAnswerExplanation.collectAsState()

    Box(modifier = modifier.fillMaxSize().padding(16.dp)) {
        scenario?.let { currentScenario ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = if (displayMode == ScenarioDisplayMode.NARRATIVE)
                               "[AAA Software]   Day ${currentScenario.dayNumber} of 52"
                           else
                               "[AAA Software]   Day ${currentScenario.dayNumber}: Analysis",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (displayMode == ScenarioDisplayMode.NARRATIVE) {
                    NarrativeView(currentScenario)
                } else {
                    currentQuestion?.let { q ->
                        QuestionView(
                            question = q,
                            questionNumber = currentQuestionIndex + 1,
                            totalQuestions = currentScenario.questions.size,
                            selectedAnswerIndex = selectedAnswerIndex,
                            isAnswerSelected = isAnswerSelected,
                            isCorrect = isCurrentAnswerCorrect,
                            explanation = currentAnswerExplanation,
                            onAnswerSelected = { index -> scenarioViewModel.selectAnswer(index) }
                        )
                    } ?: run {
                        Text("Loading question...")
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Pushes buttons to bottom

                // Temporary navigation buttons for testing
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { scenarioViewModel.onNavigateToIncomeStatementClicked() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("View Income St.")
                    }
                    OutlinedButton(
                        onClick = { scenarioViewModel.onNavigateToBalanceSheetClicked() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("View Balance Sh.")
                    }
                }
                // End of temporary buttons

                // Determine onClick action and button text based on current state
                val isIncorrectAnswer = displayMode == ScenarioDisplayMode.QUESTION &&
                                        isAnswerSelected &&
                                        isCurrentAnswerCorrect == false // Corrected: No .value here

                val onClickAction = if (isIncorrectAnswer) {
                    { scenarioViewModel.retryCurrentQuestion() }
                } else {
                    { scenarioViewModel.handleNextAction() }
                }

                val buttonText = when {
                    isIncorrectAnswer -> "Try Again"
                    displayMode == ScenarioDisplayMode.NARRATIVE -> "Let's Analyze This >"
                    displayMode == ScenarioDisplayMode.QUESTION -> {
                        // This branch is now for when the answer is correct or not yet selected
                        if (isCurrentAnswerCorrect == true) { // Check for explicit true to proceed
                           if (currentQuestionIndex < currentScenario.questions.size - 1) {
                               "Next Question >"
                           } else {
                               "Great! Now, let's make the journal entry >"
                           }
                        } else if (isCurrentAnswerCorrect == null && !isAnswerSelected){ // Not yet answered
                             "Select an Answer" // Or any placeholder text
                        }
                         else { // Answered but not correct (handled by isIncorrectAnswer) or other states
                            // Fallback, though isIncorrectAnswer should catch the main case.
                            // If an answer is selected but not incorrect (i.e. correct), this branch is hit.
                             if (currentQuestionIndex < currentScenario.questions.size - 1) {
                               "Next Question >"
                           } else {
                               "Great! Now, let's make the journal entry >"
                           }
                        }
                    }
                    else -> "Proceed" // Should not happen
                }

                Button(
                    onClick = onClickAction,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = (displayMode == ScenarioDisplayMode.NARRATIVE || isAnswerSelected || isIncorrectAnswer) // Enable for "Try Again"
                ) {
                    Text(buttonText)
                }
            }
        } ?: Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Loading scenario...")
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator()
        }
    }
}

@Composable
fun NarrativeView(scenario: com.example.theaccountant2.data.model.Scenario) {
    Column {
        Text(
            text = "MONTH: ${scenario.monthName.uppercase()}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "TITLE: ${scenario.title}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "NARRATIVE:",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = scenario.narrative,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun QuestionView(
    question: ScenarioQuestion,
    questionNumber: Int,
    totalQuestions: Int,
    selectedAnswerIndex: Int?,
    isAnswerSelected: Boolean,
    isCorrect: Boolean?, // This is the collected state value
    explanation: String?,
    onAnswerSelected: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "QUESTION $questionNumber of $totalQuestions:",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = question.questionText,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        question.choices.forEachIndexed { index, choice ->
            val isSelected = selectedAnswerIndex == index
            val buttonColors = when {
                isSelected && isCorrect == true -> ButtonDefaults.buttonColors(containerColor = Color(0xFFC8E6C9))
                isSelected && isCorrect == false -> ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2))
                else -> ButtonDefaults.outlinedButtonColors()
            }
            val textColor = if (isSelected && isCorrect != null) Color.Black else MaterialTheme.colorScheme.onSurface

            Button(
                onClick = { onAnswerSelected(index) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = buttonColors,
                border = if (!isSelected || isCorrect == null) BorderStroke(1.dp, MaterialTheme.colorScheme.outline) else null,
                enabled = !isAnswerSelected
            ) {
                Text(text = choice, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth(), color = textColor)
            }
        }

        if (isAnswerSelected && explanation != null) {
            Spacer(modifier = Modifier.height(16.dp))
            val feedbackColor = if (isCorrect == true) Color(0xFF2E7D32) else Color(0xFFC62828)
            val feedbackPrefix = if (isCorrect == true) "Correct!" else "Incorrect."
            Text(
                text = "$feedbackPrefix $explanation",
                style = MaterialTheme.typography.bodyMedium,
                color = feedbackColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
