package com.example.theaccountant2.data.repository

import com.example.theaccountant2.data.model.Scenario
import com.example.theaccountant2.data.model.ScenarioQuestion

/**
 * Repository for fetching daily scenarios.
 * Initially, scenarios are hardcoded. Later, this could fetch from a database or asset file.
 */
class ScenarioRepository {

    private val allScenarios: Map<Int, Scenario> = mapOf(
        1 to Scenario(
            dayNumber = 1,
            monthName = "January",
            title = "The Founding Investment",
            narrative = "Welcome to AAA Software! You, the owner, have decided to start your own software " +
                        "consulting firm. To begin, you invest $50,000 of your personal savings into a new " +
                        "business bank account.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this initial investment be recorded?",
                    choices = listOf(
                        "Debit Cash, Credit Service Revenue",
                        "Debit Cash, Credit Common Stock",
                        "Debit Common Stock, Credit Cash"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The business\'s cash (Asset) increases, and the owner\'s equity (Common Stock) increases."
                ),
                ScenarioQuestion(
                    questionText = "What kind of accounts are Cash and Common Stock?",
                    choices = listOf(
                        "Cash is an Asset, Stock is a Liability",
                        "Cash is an Asset, Common Stock is Equity",
                        "Cash is a Liability, Common Stock is Equity"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash is something the business owns (Asset), and Common Stock represents the owner\'s investment (Equity)."
                ),
                ScenarioQuestion(
                    questionText = "What is the effect of this transaction on the accounting equation (Assets = Liabilities + Equity)?",
                    choices = listOf(
                        "Assets increase, Liabilities increase",
                        "Assets increase, Equity increases",
                        "Assets decrease, Equity decreases"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (Asset) increases by $50,000, and Common Stock (Equity) increases by $50,000, keeping the equation in balance."
                )
            )
        )
        // TODO: Add more scenarios here as we develop them (Days 2-52)
    )

    /**
     * Fetches the scenario for the given day number.
     * Returns null if the scenario for the day is not found.
     */
    fun getScenarioForDay(dayNumber: Int): Scenario? {
        return allScenarios[dayNumber]
    }
}
