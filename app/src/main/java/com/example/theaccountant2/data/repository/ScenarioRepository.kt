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
                        "Debit Cash, Credit Owner\'s Capital", // Changed from Common Stock for sole proprietorship
                        "Debit Owner\'s Capital, Credit Cash"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The business\'s cash (Asset) increases, and the Owner\'s Capital (Equity) increases."
                ),
                ScenarioQuestion(
                    questionText = "What kind of accounts are Cash and Owner\'s Capital?",
                    choices = listOf(
                        "Cash is an Asset, Owner\'s Capital is a Liability",
                        "Cash is an Asset, Owner\'s Capital is Equity",
                        "Cash is a Liability, Owner\'s Capital is Equity"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash is something the business owns (Asset), and Owner\'s Capital represents the owner\'s investment (Equity)."
                ),
                ScenarioQuestion(
                    questionText = "What is the effect of this transaction on the accounting equation (Assets = Liabilities + Equity)?",
                    choices = listOf(
                        "Assets increase, Liabilities increase",
                        "Assets increase, Equity increases",
                        "Assets decrease, Equity decreases"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (Asset) increases by $50,000, and Owner\'s Capital (Equity) increases by $50,000, keeping the equation in balance."
                )
            )
        ),
        2 to Scenario(
            dayNumber = 2,
            monthName = "January",
            title = "Office Rent Payment",
            narrative = "It\'s time to secure a place for AAA Software to operate. You find a small office space " +
                        "and pay the first month\'s rent of $1,000 in cash.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the payment of rent recorded?",
                    choices = listOf(
                        "Debit Cash, Credit Rent Expense",
                        "Debit Rent Expense, Credit Cash",
                        "Debit Rent Expense, Credit Accounts Payable"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Rent Expense (Expense) increases, and Cash (Asset) decreases."
                ),
                ScenarioQuestion(
                    questionText = "What types of accounts are Rent Expense and Cash?",
                    choices = listOf(
                        "Rent Expense is an Asset, Cash is an Asset",
                        "Rent Expense is an Expense, Cash is an Asset",
                        "Rent Expense is a Liability, Cash is an Asset"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Rent Expense is a cost of doing business (Expense), and Cash is an Asset."
                ),
                ScenarioQuestion(
                    questionText = "What is the impact of this rent payment on the accounting equation?",
                    choices = listOf(
                        "Assets decrease, Liabilities decrease",
                        "Assets increase, Equity decreases (due to expense)",
                        "Assets decrease, Equity decreases (due to expense)"
                    ),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Cash (Asset) decreases, and Rent Expense increases, which reduces Equity. Assets = Liabilities + Equity remains balanced."
                )
            )
        ),
        3 to Scenario(
            dayNumber = 3,
            monthName = "January",
            title = "Purchase of Office Supplies",
            narrative = "To get the office running, you purchase some essential office supplies (pens, paper, " +
                        "toner, etc.) for $500. You pay for these supplies with cash.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should the purchase of office supplies for cash be recorded?",
                    choices = listOf(
                        "Debit Supplies Expense, Credit Cash",
                        "Debit Supplies (Asset), Credit Cash",
                        "Debit Cash, Credit Supplies (Asset)"
                    ),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Supplies (Asset) increases because they will be used over time, and Cash (Asset) decreases."
                ),
                ScenarioQuestion(
                    questionText = "What type of accounts are Supplies and Cash in this transaction?",
                    choices = listOf(
                        "Supplies is an Expense, Cash is an Asset",
                        "Supplies is an Asset, Cash is a Liability",
                        "Supplies is an Asset, Cash is an Asset"
                    ),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Supplies are considered an asset until they are used up, and Cash is also an Asset."
                ),
                ScenarioQuestion(
                    questionText = "What is the net effect of this transaction on Total Assets in the accounting equation?",
                    choices = listOf(
                        "Total Assets increase",
                        "Total Assets decrease",
                        "Total Assets remain unchanged"
                    ),
                    correctAnswerIndex = 2,
                    explanation = "Correct! One asset (Supplies) increases by $500, while another asset (Cash) decreases by $500. There\'s no change in total assets, liabilities, or equity from this specific exchange."
                )
            )
        )
        // TODO: Add more scenarios here as we develop them (Days 4-52)
    )

    /**
     * Fetches the scenario for the given day number.
     * Returns null if the scenario for the day is not found.
     */
    fun getScenarioForDay(dayNumber: Int): Scenario? {
        return allScenarios[dayNumber]
    }
}
