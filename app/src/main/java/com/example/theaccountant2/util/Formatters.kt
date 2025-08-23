package com.example.theaccountant2.util

import java.text.NumberFormat
import java.util.Locale

/**
 * Formats a Long value representing cents into a locale-specific currency string.
 * @param cents The amount in cents.
 * @return A string representing the formatted currency.
 */
fun formatCurrency(cents: Long): String {
    val amount = cents / 100.0 // Convert cents to a double representation of the main currency unit
    return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(amount)
}
