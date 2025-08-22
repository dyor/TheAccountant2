package com.example.theaccountant2.data.model

/**
 * Represents the full scenario for a given day in the game.
 *
 * @property dayNumber The day number (1 to 52).
 * @property monthName The name of the month this scenario falls into (e.g., "January").
 * @property title The title of the scenario (e.g., "The Founding Investment").
 * @property narrative The descriptive text of the business events for the day.
 * @property questions A list of `ScenarioQuestion` objects related to this scenario.
 */
data class Scenario(
    val dayNumber: Int,
    val monthName: String,
    val title: String,
    val narrative: String,
    val questions: List<ScenarioQuestion>
)
