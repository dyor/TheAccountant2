package com.example.theaccountant2.ui.screen.charts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theaccountant2.data.model.WeeklyPnlData
import com.example.theaccountant2.ui.theme.TheAccountant2Theme
import kotlin.math.abs

private const val MAX_BAR_HEIGHT_DP = 150 // Total height for positive + negative bar regions
private val BAR_WIDTH_DP = 10.dp // Slightly wider for better visibility
private val BAR_SPACING_DP = 4.dp // A bit more spacing
private val LABEL_AREA_HEIGHT_DP = 24.dp // Dedicated space for labels below bars

@Composable
fun WeeklyProfitLossBarChart(
    modifier: Modifier = Modifier,
    weeklyPnlDataList: List<WeeklyPnlData>,
    currentWeek: Int? = null // Optional: to highlight the current week
) {
    val chartSectionHeight = (MAX_BAR_HEIGHT_DP / 2).dp // Height for positive or negative bar section
    val labelHeight = LABEL_AREA_HEIGHT_DP

    if (weeklyPnlDataList.isEmpty() || weeklyPnlDataList.none { it.hasActivity }) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(chartSectionHeight * 2 + labelHeight) // Match the actual chart content height
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No Profit & Loss activity to display.")
        }
        return
    }

    val activeWeeksData = weeklyPnlDataList.filter { it.hasActivity }
    val maxAbsPnl = activeWeeksData.maxOfOrNull { abs(it.profitLoss) } ?: 1L

    // Theme colors
    val profitColor = MaterialTheme.colorScheme.primary
    val lossColor = MaterialTheme.colorScheme.error
    val inactiveColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) // More subtle inactive
    val neutralColor = MaterialTheme.colorScheme.outline
    val currentWeekHighlightColor = MaterialTheme.colorScheme.inversePrimary

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(chartSectionHeight * 2 + labelHeight), // Total height of the row: pos + neg + label
        horizontalArrangement = Arrangement.spacedBy(BAR_SPACING_DP, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = BAR_SPACING_DP) // Ensure padding at start/end
    ) {
        items(weeklyPnlDataList, key = { it.weekNumber }) { weekData ->
            val isCurrentWeek = weekData.weekNumber == currentWeek
            val pnl = weekData.profitLoss
            val absPnl = abs(pnl)
            val barScaleFactor = if (maxAbsPnl != 0L && weekData.hasActivity) absPnl.toFloat() / maxAbsPnl else 0f

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight() // Column will take full height of LazyRow item
                    .let { // Apply conditional highlight to the whole column background
                        if (isCurrentWeek && weekData.hasActivity) {
                            it.background(currentWeekHighlightColor.copy(alpha = 0.1f))
                               .padding(horizontal = 1.dp) // Small padding if highlighted
                        } else {
                            it
                        }
                    }
            ) {
                // Positive P&L section (Top half)
                Box(
                    modifier = Modifier.height(chartSectionHeight),
                    contentAlignment = Alignment.BottomCenter // Bar grows from bottom of this box upwards
                ) {
                    if (weekData.hasActivity && pnl > 0L) {
                        val barHeight = (barScaleFactor * chartSectionHeight.value).dp
                        Box(
                            modifier = Modifier
                                .width(BAR_WIDTH_DP)
                                .height(barHeight)
                                .background(profitColor)
                                .then(
                                    if (isCurrentWeek) Modifier.border(BorderStroke(1.5.dp, MaterialTheme.colorScheme.onSurface)) else Modifier
                                )
                        )
                    }
                }

                // Zero line indicator for this bar
                HorizontalDivider(
                    modifier = Modifier.width(BAR_WIDTH_DP),
                    thickness = 1.dp,
                    color = if (!weekData.hasActivity) inactiveColor else neutralColor
                )

                // Negative P&L section (Bottom half)
                Box(
                    modifier = Modifier.height(chartSectionHeight),
                    contentAlignment = Alignment.TopCenter // Bar grows from top of this box downwards
                ) {
                    if (weekData.hasActivity && pnl < 0L) {
                        val barHeight = (barScaleFactor * chartSectionHeight.value).dp
                        Box(
                            modifier = Modifier
                                .width(BAR_WIDTH_DP)
                                .height(barHeight)
                                .background(lossColor)
                                .then(
                                     if (isCurrentWeek) Modifier.border(BorderStroke(1.5.dp, MaterialTheme.colorScheme.onSurface)) else Modifier
                                )
                        )
                    }
                }

                // Week number label
                Text(
                    text = weekData.weekNumber.toString(),
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = (labelHeight.value / 2 - 5).dp), // Center text within its area, roughly
                    color = if (isCurrentWeek && weekData.hasActivity) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (isCurrentWeek && weekData.hasActivity) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Empty Chart", widthDp = 400)
@Composable
fun PreviewWeeklyProfitLossBarChart_EmptyState() {
    TheAccountant2Theme {
        WeeklyProfitLossBarChart(
            weeklyPnlDataList = emptyList()
        )
    }
}

@Preview(showBackground = true, name = "No Activity", widthDp = 400)
@Composable
fun PreviewWeeklyProfitLossBarChart_NoActualActivity() {
    TheAccountant2Theme {
        WeeklyProfitLossBarChart(
            weeklyPnlDataList = List(10) { WeeklyPnlData(it + 1, 0L, false) }
        )
    }
}

@Preview(showBackground = true, name = "Sample Data", widthDp = 400)
@Composable
fun PreviewWeeklyProfitLossBarChart_WithData() {
    TheAccountant2Theme {
        val sampleData = listOf(
            WeeklyPnlData(1, 100000, true), // Profit
            WeeklyPnlData(2, -50000, true), // Loss
            WeeklyPnlData(3, 0, true),      // Neutral Active
            WeeklyPnlData(4, 200000, true), // Higher Profit
            WeeklyPnlData(5, 0, false),     // Inactive
            WeeklyPnlData(6, -150000, true), // Higher Loss
            WeeklyPnlData(7, 75000, true)
        )
        WeeklyProfitLossBarChart(weeklyPnlDataList = sampleData, currentWeek = 4)
    }
}

@Preview(showBackground = true, name = "All Positive", widthDp = 400)
@Composable
fun PreviewWeeklyProfitLossBarChart_AllPositive() {
    TheAccountant2Theme {
        WeeklyProfitLossBarChart(
            weeklyPnlDataList = List(10) { WeeklyPnlData(it + 1, (Math.random() * 200000).toLong() + 10000, true) },
            currentWeek = 3
        )
    }
}

@Preview(showBackground = true, name = "All Negative", widthDp = 400)
@Composable
fun PreviewWeeklyProfitLossBarChart_AllNegative() {
    TheAccountant2Theme {
        WeeklyProfitLossBarChart(
            weeklyPnlDataList = List(10) { WeeklyPnlData(it + 1, -(Math.random() * 200000).toLong() - 10000, true) },
            currentWeek = 7
        )
    }
}

@Preview(showBackground = true, name = "Mixed Activity States", widthDp = 400)
@Composable
fun PreviewWeeklyProfitLossBarChart_MixedActivity() {
    TheAccountant2Theme {
        val data = (1..20).map { weekNum ->
            val pnl = when (weekNum % 5) {
                0 -> 0L // Zero P&L
                1 -> (Math.random() * 100000).toLong() + 50000 // Profit
                2 -> -((Math.random() * 80000).toLong() + 20000) // Loss
                else -> 0L // Will be marked inactive for some
            }
            WeeklyPnlData(
                weekNumber = weekNum,
                profitLoss = pnl,
                hasActivity = when (weekNum % 4) {
                    0 -> false // Inactive
                    else -> true // Active
                }
            )
        }
        WeeklyProfitLossBarChart(weeklyPnlDataList = data, currentWeek = 10)
    }
}
