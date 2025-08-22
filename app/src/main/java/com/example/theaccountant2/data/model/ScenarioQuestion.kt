package com.example.theaccountant2.data.model

/**
 * Represents a single multiple-choice question within a scenario.
 *
 * @property questionText The text of the question.
 * @property choices A list of possible answer strings.
 * @property correctAnswerIndex The index in the `choices` list that represents the correct answer.
 * @property explanation A brief explanation of why the correct answer is right.
 */
data class ScenarioQuestion(
    val questionText: String,
    val choices: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)
