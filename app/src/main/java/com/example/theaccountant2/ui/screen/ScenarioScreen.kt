package com.example.theaccountant2.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController // IMPORT ADDED
import com.example.theaccountant2.data.model.ScenarioQuestion
import com.example.theaccountant2.ui.viewmodel.ScenarioDisplayMode
import com.example.theaccountant2.ui.viewmodel.ScenarioViewModel
import com.example.theaccountant2.ui.screen.charts.WeeklyProfitLossBarChart
import com.example.theaccountant2.ui.viewmodel.FinancialStatementViewModel

@Composable
fun ScenarioScreen(
    modifier: Modifier = Modifier,
    scenarioViewModel: ScenarioViewModel,
    financialStatementViewModel: FinancialStatementViewModel,
    navController: NavHostController // PARAMETER ADDED
) {
    // Scenario States
    val scenario by scenarioViewModel.currentScenario.collectAsState()
    val displayMode by scenarioViewModel.displayMode.collectAsState()
    val currentQuestion by scenarioViewModel.currentDisplayedQuestion.collectAsState()
    val currentQuestionIndex by scenarioViewModel.currentQuestionIndex.collectAsState()
    val selectedAnswerIndex by scenarioViewModel.selectedAnswerIndex.collectAsState()
    val isAnswerSelected by scenarioViewModel.isAnswerSelected.collectAsState()
    val isCurrentAnswerCorrect by scenarioViewModel.isCurrentAnswerCorrect.collectAsState()
    val currentAnswerExplanation by scenarioViewModel.currentAnswerExplanation.collectAsState()

    // Financial Statement States for the Chart
    val weeklyPnlData by financialStatementViewModel.weeklyProfitAndLossData.collectAsState()

    Box(modifier = modifier.fillMaxSize().padding(16.dp)) {
        scenario?.let { currentScenario ->
            //weekNumber is not correct - weekNumber in simulation should just be dayNumber

            //val weekNumber = (currentScenario.dayNumber - 1) / 7 + 1
            val weekNumber = currentScenario.dayNumber
            val currentWeekForChart = if (currentScenario.dayNumber > 0) weekNumber else null

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                // Persistent Title
                Text(
                    text = "WEEK $weekNumber",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )

                // Persistent Scrollable Narrative
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp, max = 150.dp) // Allow scroll within this height
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = currentScenario.narrative.replace("+", " "), // Ensure + is replaced with space
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 20.sp
                    )
                }

                // Conditional content area (Chart or Questions)
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) { // Ensure this box can expand
                    if (displayMode == ScenarioDisplayMode.NARRATIVE) {
                        Column { // Column to group chart title and chart/placeholder
                            Text(
                                "Weekly P&L Performance (52 Weeks):",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            if (weeklyPnlData.any { it.hasActivity }) {
                                WeeklyProfitLossBarChart(
                                    weeklyPnlDataList = weeklyPnlData,
                                    currentWeek = currentWeekForChart,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp) // Give chart a specific height
                                        .padding(bottom = 16.dp)
                                )
                            } else {
                                // Show a placeholder if no chart data
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp) // Match chart height for consistent spacing
                                        .padding(bottom = 16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Profit and Loss or P&L chart data will appear as you progress.", textAlign = TextAlign.Center)
                                }
                            }
                        }
                    } else { // DisplayMode.QUESTION
                        currentQuestion?.let { q ->
                            QuestionView(
                                question = q,
                                questionNumber = currentQuestionIndex + 1,
                                totalQuestions = currentScenario.questions.size,
                                selectedAnswerIndex = selectedAnswerIndex,
                                isAnswerSelected = isAnswerSelected,
                                isCorrect = isCurrentAnswerCorrect,
                                explanation = currentAnswerExplanation, // Pass the original explanation
                                onAnswerSelected = { index -> scenarioViewModel.selectAnswer(index) }
                            )
                        } ?: run {
                            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Text("Loading question...")
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
                
                // Temporary navigation buttons for testing
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
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

                val isIncorrectAnswerState = displayMode == ScenarioDisplayMode.QUESTION &&
                                        isAnswerSelected &&
                                        isCurrentAnswerCorrect == false

                val onClickAction = if (isIncorrectAnswerState) {
                    { scenarioViewModel.retryCurrentQuestion() }
                } else {
                    { scenarioViewModel.handleNextAction() }
                }

                val buttonText = when {
                    isIncorrectAnswerState -> "Try Again"
                    displayMode == ScenarioDisplayMode.NARRATIVE -> "Let\'s Analyze This >"
                    displayMode == ScenarioDisplayMode.QUESTION -> {
                        if (isCurrentAnswerCorrect == true) {
                           if (currentQuestionIndex < currentScenario.questions.size - 1) {
                               "Next Question >"
                           } else {
                               "Great! Now, let\'s make the journal entry >"
                           }
                        } else if (isCurrentAnswerCorrect == null && !isAnswerSelected){
                             "Select an Answer"
                        }
                         else { // Handles correct answer or unanswered after a wrong attempt that was retried
                             if (currentQuestionIndex < currentScenario.questions.size - 1) {
                               "Next Question >"
                           } else {
                               "Great! Now, let\'s make the journal entry >"
                           }
                        }
                    }
                    else -> "Proceed"
                }

                Button(
                    onClick = onClickAction,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = (displayMode == ScenarioDisplayMode.NARRATIVE || isAnswerSelected || isIncorrectAnswerState)
                ) {
                    Text(buttonText)
                }
            }
        } ?: Column( // Fallback for when scenario is null (loading)
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
fun QuestionView(
    question: ScenarioQuestion,
    questionNumber: Int,
    totalQuestions: Int,
    selectedAnswerIndex: Int?,
    isAnswerSelected: Boolean,
    isCorrect: Boolean?,
    explanation: String?, // Full explanation from ViewModel
    onAnswerSelected: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) { 
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
            val feedbackText:
 String
            val feedbackColor: Color

            if (isCorrect == true) {
                feedbackText = "Correct! $explanation"
                feedbackColor = Color(0xFF2E7D32) // Dark Green
            } else { // isCorrect == false
                feedbackText = "Incorrect. Try again."
                feedbackColor = Color(0xFFC62828) // Dark Red
            }

            Text(
                text = feedbackText,
                style = MaterialTheme.typography.bodyMedium,
                color = feedbackColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
