package com.example.theaccountant2.data.repository

import com.example.theaccountant2.data.model.Scenario
import com.example.theaccountant2.data.model.ScenarioQuestion

class ScenarioRepository {

    private fun getMonthNameForDay(dayNumber: Int): String {
        return when {
            dayNumber <= 7 -> "January"    // Approx Week 1
            dayNumber <= 14 -> "February"  // Approx Week 2
            dayNumber <= 21 -> "March"     // Approx Week 3
            dayNumber <= 28 -> "April"     // Approx Week 4
            dayNumber <= 35 -> "May"       // Approx Week 5
            dayNumber <= 42 -> "June"      // Approx Week 6
            dayNumber <= 49 -> "July"      // Approx Week 7
            dayNumber <= 52 -> "August"    // Approx Week 8 (ends early)
            else -> "ErrorMonth"
        }
    }

    private val allScenarios: Map<Int, Scenario> = mapOf(
        1 to Scenario(
            dayNumber = 1,
            monthName = getMonthNameForDay(1),
            title = "The Founding Investment",
            narrative = "Welcome to AAA Software! You, the owner, have decided to start your own software " +
                        "consulting firm. To begin, you invest $50,000 of your personal savings into a new " +
                        "business bank account.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this initial investment be recorded?",
                    choices = listOf(
                        "Debit Cash, Credit Service Revenue",
                        "Debit Cash, Credit Owner\'s Capital",
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
            monthName = getMonthNameForDay(2),
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
            monthName = getMonthNameForDay(3),
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
        ),
        4 to Scenario(
            dayNumber = 4,
            monthName = getMonthNameForDay(4),
            title = "First Project Kick-off: Hiring a Contractor",
            narrative = "AAA Software has landed its first potential client project! For a specialized part of the upcoming work, the company hires \'DevPro Services\', an external contractor. DevPro completes 10 hours of work today at a rate of $50 per hour. AAA Software pays DevPro Services the full amount due immediately via a bank transfer.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the total amount of expense AAA Software should recognize for the contractor\'s services today?",
                    choices = listOf("$50", "$10", "$500", "$0 as it\'s for a client"),
                    correctAnswerIndex = 2,
                    explanation = "The expense is 10 hours * $50/hour = $500. This cost is recognized as an expense when the service is received and paid."
                ),
                ScenarioQuestion(
                    questionText = "Which of the following accounts would be debited to record this payment to DevPro Services?",
                    choices = listOf("Service Revenue", "Accounts Payable", "Contractor Expense", "Prepaid Services"),
                    correctAnswerIndex = 2,
                    explanation = "'Contractor Expense' (or a similar expense account like \'Cost of Services - Contractor\') is debited to reflect the cost incurred for the services provided by the contractor."
                ),
                ScenarioQuestion(
                    questionText = "How does this immediate payment to the contractor affect AAA Software\'s equity?",
                    choices = listOf("Increases equity", "Decreases equity", "No direct effect on equity", "Increases assets"),
                    correctAnswerIndex = 1,
                    explanation = "Equity decreases. Paying the contractor results in an expense (Contractor Expense), and expenses reduce net income, which in turn reduces retained earnings (a component of equity)."
                )
            )
        ),
        5 to Scenario(
            dayNumber = 5,
            monthName = getMonthNameForDay(5),
            title = "Client Billing: First Invoice Sent (Net 30)",
            narrative = "Following the initial work (which included DevPro\'s services), AAA Software is ready to bill its first client, \'Client Alpha\'. An invoice for $800 is prepared and sent to Client Alpha for services rendered. The payment terms are Net 30.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What does \'Net 30\' payment terms mean for Client Alpha?",
                    choices = listOf("They receive a 30% discount if they pay early.", "They must pay the full invoice amount within 30 days.", "They must pay 30% of the invoice immediately.", "The invoice is valid for only 30 days."),
                    correctAnswerIndex = 1,
                    explanation = "'Net 30' means the full invoice amount is due within 30 days from the invoice date."
                ),
                ScenarioQuestion(
                    questionText = "When AAA Software sends this invoice, which account is debited to reflect the amount owed by Client Alpha?",
                    choices = listOf("Cash", "Service Revenue", "Unearned Revenue", "Accounts Receivable"),
                    correctAnswerIndex = 3,
                    explanation = "Accounts Receivable is debited. This asset account represents money owed to AAA Software by its customers for services already provided or goods sold on credit."
                ),
                ScenarioQuestion(
                    questionText = "Despite not receiving cash yet, AAA Software recognizes $800 in Service Revenue. This is an application of which accounting principle?",
                    choices = listOf("Cash Basis Accounting", "The Matching Principle", "The Revenue Recognition Principle (Accrual Basis)", "The Conservatism Principle"),
                    correctAnswerIndex = 2,
                    explanation = "The Revenue Recognition Principle (under accrual basis accounting) states that revenue is recognized when it is earned (services performed or goods delivered), regardless of when cash is received."
                )
            )
        ),
        6 to Scenario(
            dayNumber = 6,
            monthName = getMonthNameForDay(6),
            title = "Cash Inflow: Client Alpha Pays Invoice",
            narrative = "Good news! Client Alpha, who was invoiced on Day 5 with Net 30 terms for $800, has paid their invoice in full today. The payment was received via a bank transfer.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "When Client Alpha pays, which asset account increases?",
                    choices = listOf("Accounts Receivable", "Service Revenue", "Cash", "Prepaid Expenses"),
                    correctAnswerIndex = 2,
                    explanation = "Cash increases because the company has received money. Accounts Receivable, which represented the amount owed by the client, will decrease."
                ),
                ScenarioQuestion(
                    questionText = "What happens to the \'Accounts Receivable - Client Alpha\' balance after this payment?",
                    choices = listOf("It increases by $800", "It decreases by $800", "It remains unchanged", "It is transferred to Bad Debt Expense"),
                    correctAnswerIndex = 1,
                    explanation = "Accounts Receivable for Client Alpha decreases by $800, as the client has settled their debt. The balance for this specific client should now be zero."
                ),
                ScenarioQuestion(
                    questionText = "Does receiving this cash payment affect AAA Software\'s total revenue for the period?",
                    choices = listOf("Yes, revenue increases by $800 now", "No, revenue was already recognized when the service was billed", "Yes, but only if it\'s within the same month", "No, this only affects assets"),
                    correctAnswerIndex = 1,
                    explanation = "No, the revenue was already recognized on Day 5 when the service was performed and the invoice was sent (accrual basis). This transaction only affects asset accounts (Cash increases, Accounts Receivable decreases)."
                )
            )
        ),
        7 to Scenario(
            dayNumber = 7,
            monthName = getMonthNameForDay(7),
            title = "Settling Debts: Paying for Computer Equipment",
            narrative = "AAA Software pays the remaining $1,500 balance owed for the computer equipment purchased on Day 3. The payment is made via a bank transfer from the company\'s account.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "Which liability account decreases as a result of this payment?",
                    choices = listOf("Notes Payable", "Salaries Payable", "Unearned Revenue", "Accounts Payable"),
                    correctAnswerIndex = 3,
                    explanation = "Accounts Payable decreases because AAA Software is settling its short-term obligation to the supplier of the computer equipment."
                ),
                ScenarioQuestion(
                    questionText = "How does this payment affect AAA Software\'s total liabilities?",
                    choices = listOf("Increases total liabilities", "Decreases total liabilities", "No effect on total liabilities", "Increases total assets"),
                    correctAnswerIndex = 1,
                    explanation = "Total liabilities decrease because the balance of Accounts Payable is reduced."
                ),
                ScenarioQuestion(
                    questionText = "What is the impact of this transaction on AAA Software\'s total assets?",
                    choices = listOf("Increases total assets by $1,500", "Decreases total assets by $1,500", "No effect on total assets", "Increases Computer Equipment value"),
                    correctAnswerIndex = 1,
                    explanation = "Total assets decrease by $1,500 because Cash (an asset) is paid out. The Computer Equipment account itself is not affected by this payment of a prior obligation."
                )
            )
        ),
        8 to Scenario(
            dayNumber = 8,
            monthName = getMonthNameForDay(8),
            title = "New Project & Upfront Payment: Client Gamma",
            narrative = "AAA Software signs a contract with a new, larger client, \'Client Gamma\', for a custom software module. The total project value is $5,000. Client Gamma pays an upfront retainer of $2,000 today. The remaining will be billed upon project completion.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "When AAA Software receives the $2,000 retainer, what liability account is typically credited?",
                    choices = listOf("Service Revenue", "Accounts Receivable", "Unearned Revenue", "Notes Payable"),
                    correctAnswerIndex = 2,
                    explanation = "Unearned Revenue (or Deferred Revenue) is credited. This liability account represents the obligation to provide services in the future for cash already received."
                ),
                ScenarioQuestion(
                    questionText = "How much Service Revenue should AAA Software recognize from Client Gamma on this day?",
                    choices = listOf("$5,000", "$2,000", "$0", "$3,000"),
                    correctAnswerIndex = 2,
                    explanation = "$0. Revenue is recognized when it is earned (i.e., when services are performed). Since no work has been done yet, the upfront payment is recorded as a liability (Unearned Revenue)."
                ),
                ScenarioQuestion(
                    questionText = "What is the effect of receiving the $2,000 retainer on AAA Software\'s total assets?",
                    choices = listOf("Total assets increase by $2,000", "Total assets decrease by $2,000", "No change in total assets", "Total assets increase by $5,000"),
                    correctAnswerIndex = 0,
                    explanation = "Total assets increase by $2,000 because Cash (an asset) increases. This is balanced by an increase in liabilities (Unearned Revenue)."
                )
            )
        ),
        9 to Scenario(
            dayNumber = 9,
            monthName = getMonthNameForDay(9),
            title = "Operating Cost: Monthly Software Subscription",
            narrative = "AAA Software pays its monthly subscription fee of $50 for a project management software tool. The payment is made automatically from its bank account.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "Which account should be debited to record this software subscription payment?",
                    choices = listOf("Software Asset", "Prepaid Subscriptions", "Subscription Expense", "Accounts Payable"),
                    correctAnswerIndex = 2,
                    explanation = "Subscription Expense (or a similar account like Software Expense) is debited because the benefit of the software is consumed within the current period (this month)."
                ),
                ScenarioQuestion(
                    questionText = "How does this $50 payment affect AAA Software\'s net income?",
                    choices = listOf("Increases net income by $50", "Decreases net income by $50", "No effect on net income", "Decreases assets by $50 but not net income"),
                    correctAnswerIndex = 1,
                    explanation = "Net income decreases by $50. Expenses (like Subscription Expense) reduce net income."
                ),
                ScenarioQuestion(
                    questionText = "If this software subscription was for a full year and paid upfront, what asset account might be used initially?",
                    choices = listOf("Subscription Expense", "Software Asset (Intangible)", "Prepaid Subscription", "Accrued Expense"),
                    correctAnswerIndex = 2,
                    explanation = "Prepaid Subscription (or Prepaid Expense) would be debited initially for an annual subscription paid upfront. The expense would then be recognized monthly as the service is consumed."
                )
            )
        ),
        10 to Scenario(
            dayNumber = 10,
            monthName = getMonthNameForDay(10),
            title = "Progress on Client Gamma: Recognizing Earned Revenue",
            narrative = "AAA Software has completed a significant portion of the work for Client Gamma. Based on the work performed to date, AAA Software determines that $800 of the $2,000 retainer received on Day 8 has now been earned.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "When recognizing the $800 of earned revenue, which account is debited?",
                    choices = listOf("Cash", "Accounts Receivable", "Service Revenue", "Unearned Revenue"),
                    correctAnswerIndex = 3,
                    explanation = "Unearned Revenue is debited to reduce the liability, as the company has now fulfilled a part of its obligation to Client Gamma."
                ),
                ScenarioQuestion(
                    questionText = "Which account is credited to reflect the revenue earned from Client Gamma?",
                    choices = listOf("Cash", "Accounts Receivable", "Service Revenue", "Unearned Revenue"),
                    correctAnswerIndex = 2,
                    explanation = "Service Revenue is credited by $800. This reflects the income earned from providing services to Client Gamma during this period."
                ),
                ScenarioQuestion(
                    questionText = "What is the balance of \'Unearned Revenue - Client Gamma\' after this transaction?",
                    choices = listOf("$2,000", "$800", "$1,200", "$0"),
                    correctAnswerIndex = 2,
                    explanation = "The Unearned Revenue balance for Client Gamma decreases from $2,000 to $1,200 ($2,000 - $800) because $800 of the obligation has been fulfilled."
                )
            )
        ),
        11 to Scenario(
            dayNumber = 11,
            monthName = getMonthNameForDay(11),
            title = "Contractor Support: Client Gamma Project",
            narrative = "To expedite Client Gamma\'s project, AAA Software engages \'CodeWizards Inc.\' for 15 hours of specialized coding at $60 per hour. AAA Software pays CodeWizards Inc. the full amount of $900 today.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the accounting impact of paying CodeWizards Inc.?",
                    choices = listOf("Debit Accounts Payable, Credit Cash", "Debit Contractor Expense, Credit Cash", "Debit Service Revenue, Credit Cash", "Debit Unearned Revenue, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Contractor Expense (or Cost of Services - Contractor) is debited for $900 to recognize the cost, and Cash is credited for $900 as it\'s paid immediately."
                ),
                ScenarioQuestion(
                    questionText = "This $900 payment is considered a:",
                    choices = listOf("Reduction in client\'s Unearned Revenue", "Direct increase to the client\'s project asset value", "Business operating expense for AAA Software", "Loan to CodeWizards Inc."),
                    correctAnswerIndex = 2,
                    explanation = "It\'s a business operating expense for AAA Software, specifically related to the cost of delivering services. It reduces AAA Software\'s net income."
                ),
                ScenarioQuestion(
                    questionText = "If AAA Software had not paid CodeWizards Inc. immediately, what liability account would have been credited instead of Cash?",
                    choices = listOf("Salaries Payable", "Contractor Payable (or Accounts Payable)", "Unearned Contractor Fees", "Notes Payable"),
                    correctAnswerIndex = 1,
                    explanation = "Contractor Payable or Accounts Payable would be credited to recognize the obligation to pay CodeWizards Inc. in the future."
                )
            )
        ),
        12 to Scenario(
            dayNumber = 12,
            monthName = getMonthNameForDay(12),
            title = "New Client: Services for \'Client Delta\' (Net 15)",
            narrative = "AAA Software completes a small one-day consulting service for a new client, \'Client Delta\', for $300. An invoice is sent to Client Delta with Net 15 payment terms.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "When AAA Software invoices Client Delta, which entry is correct?",
                    choices = listOf("Debit Cash $300, Credit Service Revenue $300", "Debit Accounts Receivable $300, Credit Service Revenue $300", "Debit Unearned Revenue $300, Credit Service Revenue $300", "Debit Service Revenue $300, Credit Accounts Receivable $300"),
                    correctAnswerIndex = 1,
                    explanation = "Accounts Receivable is debited for $300 (amount owed by client) and Service Revenue is credited for $300 (revenue earned)."
                ),
                ScenarioQuestion(
                    questionText = "\'Net 15\' payment terms mean:",
                    choices = listOf("Client Delta gets a 15% discount.", "Payment is due in 15 business days.", "Payment is due within 15 calendar days from the invoice date.", "Client Delta has 15 months to pay."),
                    correctAnswerIndex = 2,
                    explanation = "'Net 15' signifies that the full invoice amount is due within 15 calendar days of the invoice date."
                ),
                ScenarioQuestion(
                    questionText = "How does this transaction with Client Delta affect AAA Software\'s total equity?",
                    choices = listOf("Equity increases by $300 immediately", "Equity decreases by $300 until cash is received", "No immediate effect on equity, only assets", "Equity will increase by $300 when cash is received"),
                    correctAnswerIndex = 0,
                    explanation = "Equity increases by $300 immediately. This is because Service Revenue increases by $300, and revenue increases net income, which increases retained earnings (a component of equity)."
                )
            )
        ),
        13 to Scenario(
            dayNumber = 13,
            monthName = getMonthNameForDay(13),
            title = "Paying Bills: Office Utilities",
            narrative = "AAA Software receives and pays the electricity bill for the office for the past month. The amount is $75, paid via bank transfer.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the correct journal entry to record this utility payment?",
                    choices = listOf("Debit Cash, Credit Utilities Expense", "Debit Utilities Expense, Credit Accounts Payable", "Debit Accounts Payable, Credit Utilities Expense", "Debit Utilities Expense, Credit Cash"),
                    correctAnswerIndex = 3,
                    explanation = "Utilities Expense is debited to record the cost incurred, and Cash is credited because the payment is made immediately."
                ),
                ScenarioQuestion(
                    questionText = "This $75 utility payment is classified as:",
                    choices = listOf("An asset", "A liability", "An operating expense", "Revenue"),
                    correctAnswerIndex = 2,
                    explanation = "It\'s an operating expense, a cost incurred in the normal course of running the business, which reduces net income."
                ),
                ScenarioQuestion(
                    questionText = "If AAA Software had received the bill but not paid it yet, what account would be credited instead of Cash?",
                    choices = listOf("Prepaid Utilities", "Utilities Expense", "Accrued Utilities (or Utilities Payable/Accounts Payable)", "Unearned Utilities Revenue"),
                    correctAnswerIndex = 2,
                    explanation = "Accrued Utilities, Utilities Payable, or Accounts Payable would be credited to recognize the liability for the unpaid bill."
                )
            )
        ),
        14 to Scenario(
            dayNumber = 14,
            monthName = getMonthNameForDay(14),
            title = "Payment Received: Client Delta Pays Invoice",
            narrative = "Client Delta, invoiced on Day 12 for $300 with Net 15 terms, pays their invoice in full today via a direct deposit into AAA Software\'s bank account.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "Which accounts are affected by Client Delta\'s payment?",
                    choices = listOf("Cash and Service Revenue", "Accounts Payable and Cash", "Cash and Accounts Receivable", "Service Revenue and Accounts Receivable"),
                    correctAnswerIndex = 2,
                    explanation = "Cash (asset) increases as payment is received, and Accounts Receivable (asset) decreases as the amount owed by Client Delta is now settled."
                ),
                ScenarioQuestion(
                    questionText = "What is the journal entry to record this payment?",
                    choices = listOf("Debit Service Revenue, Credit Cash", "Debit Cash, Credit Accounts Receivable", "Debit Accounts Receivable, Credit Cash", "Debit Cash, Credit Service Revenue"),
                    correctAnswerIndex = 1,
                    explanation = "Cash is debited (increased) by $300, and Accounts Receivable is credited (decreased) by $300."
                ),
                ScenarioQuestion(
                    questionText = "Does this transaction impact AAA Software\'s reported Net Income for the period?",
                    choices = listOf("Yes, Net Income increases by $300", "No, Net Income is not affected by this transaction", "Yes, Net Income decreases by $300", "Only if it results in a profit"),
                    correctAnswerIndex = 1,
                    explanation = "No, Net Income is not affected. The revenue was recognized when the service was billed (Day 12). This transaction is purely an exchange of one asset (Accounts Receivable) for another (Cash)."
                )
            )
        ),
        15 to Scenario(
            dayNumber = 15,
            monthName = getMonthNameForDay(15),
            title = "Marketing Push: Advertising Campaign",
            narrative = "To attract more clients, AAA Software launches a small online advertising campaign and pays $200 upfront for the first month of advertising services.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this $200 payment for advertising be recorded?",
                    choices = listOf("Debit Prepaid Advertising, Credit Cash", "Debit Advertising Expense, Credit Cash", "Debit Marketing Asset, Credit Cash", "Debit Cash, Credit Advertising Revenue"),
                    correctAnswerIndex = 1,
                    explanation = "Assuming the benefit of the advertising is for the current month, Advertising Expense is debited for $200, and Cash is credited for $200. If it covered multiple future months, Prepaid Advertising would be used initially."
                ),
                ScenarioQuestion(
                    questionText = "This advertising cost of $200 will:",
                    choices = listOf("Increase AAA Software\'s assets", "Increase AAA Software\'s liabilities", "Decrease AAA Software\'s net income", "Have no effect on the financial statements"),
                    correctAnswerIndex = 2,
                    explanation = "The advertising cost is an expense, which reduces net income for the period. This, in turn, reduces retained earnings and thus equity."
                ),
                ScenarioQuestion(
                    questionText = "If the $200 was for a 4-month campaign paid fully upfront, what would be the advertising expense recognized *each month*?",
                    choices = listOf("$200", "$50", "$800", "$0 until campaign ends"),
                    correctAnswerIndex = 1,
                    explanation = "If $200 was for 4 months, the monthly expense would be $50 ($200 / 4 months). The initial payment would go to Prepaid Advertising, and $50 would be expensed each month."
                )
            )
        ),
        // Batch 2: Days 16-30
        16 to Scenario(
            dayNumber = 16,
            monthName = getMonthNameForDay(16),
            title = "Purchase of Advanced Software (Annual License)",
            narrative = "AAA Software purchases an advanced software development tool with an annual license costing $1,200. The payment is made today.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should the $1,200 payment for the annual software license be initially recorded?",
                    choices = listOf("Debit Prepaid Software License (or Prepaid Expense), Credit Cash", "Debit Software Expense, Credit Cash", "Debit Intangible Asset - Software, Credit Cash"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! The $1,200 is for a resource that will benefit the company for 12 months, so it\'s an asset (Prepaid Software License or Prepaid Expense)."
                ),
                ScenarioQuestion(
                    questionText = "What is the monthly expense recognized for this software license?",
                    choices = listOf("$1,200", "$100", "$0 until the end of the year"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The total cost of $1,200 is spread over 12 months, so the monthly expense is $100 ($1,200 / 12)."
                ),
                ScenarioQuestion(
                    questionText = "This process of allocating the cost of an asset over its useful life is known as?",
                    choices = listOf("Depreciation", "Amortization (for intangible assets/prepayments)", "Revenue Recognition"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! This is amortization. Depreciation is similar but typically used for tangible assets."
                )
            )
        ),
        17 to Scenario(
            dayNumber = 17,
            monthName = getMonthNameForDay(17),
            title = "Client Beta - New Project & 50% Upfront Payment",
            narrative = "AAA Software secures a new project with \'Client Beta\' worth $3,000. Client Beta pays 50% ($1,500) upfront today. Work will commence next week.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the $1,500 upfront payment from Client Beta recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Cash, Credit Unearned Revenue", "Debit Accounts Receivable, Credit Service Revenue"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash increases, and Unearned Revenue (a liability) increases because the service has not yet been performed."
                ),
                ScenarioQuestion(
                    questionText = "What is the impact on AAA Software\'s total liabilities from this transaction?",
                    choices = listOf("Liabilities decrease by $1,500", "Liabilities increase by $1,500", "No change in liabilities"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Unearned Revenue is a liability, and it increases by $1,500."
                ),
                ScenarioQuestion(
                    questionText = "How much revenue is recognized from Client Beta today?",
                    choices = listOf("$3,000", "$1,500", "$0"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! $0 revenue is recognized today as no service has been performed yet. Revenue is recognized when earned."
                )
            )
        ),
        18 to Scenario(
            dayNumber = 18,
            monthName = getMonthNameForDay(18),
            title = "Owner Withdrawal",
            narrative = "You, the owner of AAA Software, withdraw $500 from the business bank account for personal use.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the owner\'s withdrawal of $500 recorded?",
                    choices = listOf("Debit Salary Expense, Credit Cash", "Debit Owner\'s Drawings (or Owner\'s Withdrawals), Credit Cash", "Debit Accounts Payable, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Owner\'s Drawings (an equity account that reduces total equity) is debited, and Cash (asset) is credited."
                ),
                ScenarioQuestion(
                    questionText = "What is the effect of this withdrawal on the business\'s total equity?",
                    choices = listOf("Equity increases", "Equity decreases", "No effect on equity"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Owner\'s drawings reduce the owner\'s stake in the company, thus decreasing equity."
                ),
                ScenarioQuestion(
                    questionText = "Is an owner\'s withdrawal considered a business expense?",
                    choices = listOf("Yes, it\'s a cost of having an owner.", "No, it\'s a distribution of profits/capital, not an operating expense.", "Only if the business is profitable."),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Owner withdrawals are distributions of equity, not business expenses, and do not appear on the Income Statement."
                )
            )
        ),
        19 to Scenario(
            dayNumber = 19,
            monthName = getMonthNameForDay(19),
            title = "Office Cleaning Services (Paid on Account)",
            narrative = "AAA Software hires \'SparkleClean Co.\' for monthly office cleaning. The first cleaning is done today for $100. SparkleClean Co. sends an invoice, payable in 15 days.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should AAA Software record this transaction today?",
                    choices = listOf("Debit Cleaning Expense, Credit Cash", "Debit Cleaning Expense, Credit Accounts Payable", "Debit Prepaid Cleaning, Credit Accounts Payable"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cleaning Expense is debited for $100 (service consumed), and Accounts Payable is credited for $100 (obligation to pay later)."
                ),
                ScenarioQuestion(
                    questionText = "What type of account is Accounts Payable?",
                    choices = listOf("Asset", "Liability", "Equity", "Expense"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Payable represents a short-term debt or obligation to others."
                ),
                ScenarioQuestion(
                    questionText = "Does this transaction affect AAA Software\'s cash balance today?",
                    choices = listOf("Yes, cash decreases by $100", "No, cash is unaffected today", "Yes, cash increases by $100"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! No cash is paid today. Cash will be affected when the invoice is paid later."
                )
            )
        ),
        20 to Scenario(
            dayNumber = 20,
            monthName = getMonthNameForDay(20),
            title = "Client Gamma Project - Phase 2 Progress",
            narrative = "Work continues on Client Gamma\'s project. Today, AAA Software determines that an additional $1,000 of the upfront retainer has been earned through services performed.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the recognition of this additional $1,000 of earned revenue recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Unearned Revenue, Credit Service Revenue", "Debit Accounts Receivable, Credit Service Revenue"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Unearned Revenue (liability) is debited (decreased) by $1,000, and Service Revenue (equity/income) is credited (increased) by $1,000."
                ),
                ScenarioQuestion(
                    questionText = "What is the new balance in the \'Unearned Revenue - Client Gamma\' account? (Previous balance was $1,200 after Day 10)",
                    choices = listOf("$1,200", "$1,000", "$200", "$2,200"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! The balance was $1,200. After earning $1,000 more, the new balance is $200 ($1,200 - $1,000)."
                ),
                ScenarioQuestion(
                    questionText = "How does this recognition of earned revenue affect AAA Software\'s total equity?",
                    choices = listOf("Equity increases by $1,000", "Equity decreases by $1,000", "No change in equity"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! Service Revenue increases, which leads to an increase in Net Income, and thus an increase in Retained Earnings (a component of equity)."
                )
            )
        ),
        21 to Scenario(
            dayNumber = 21,
            monthName = getMonthNameForDay(21),
            title = "Telephone & Internet Bill (Paid)",
            narrative = "AAA Software pays its monthly telephone and internet bill of $150 via an automatic bank transfer.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the journal entry for this payment?",
                    choices = listOf("Debit Accounts Payable, Credit Cash", "Debit Telephone & Internet Expense, Credit Accounts Payable", "Debit Telephone & Internet Expense, Credit Cash"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Telephone & Internet Expense is debited for $150, and Cash is credited for $150."
                ),
                ScenarioQuestion(
                    questionText = "This $150 payment is an example of what kind of expense?",
                    choices = listOf("Cost of Goods Sold", "Financing Expense", "Operating Expense"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! It is an operating expense, necessary for the day-to-day running of the business."
                ),
                ScenarioQuestion(
                    questionText = "What is the impact on the accounting equation?",
                    choices = listOf("Assets decrease, Liabilities decrease", "Assets decrease, Equity decreases", "Assets increase, Equity decreases"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (Asset) decreases by $150, and Telephone & Internet Expense increases, which reduces Net Income and therefore Equity by $150."
                )
            )
        ),
        // Week 4 Start (Day 22-28)
        22 to Scenario(
            dayNumber = 22,
            monthName = getMonthNameForDay(22),
            title = "Work Commences: Client Beta Project",
            narrative = "AAA Software begins work on the Client Beta project. After the first day of work, it is estimated that $400 of the $1,500 unearned revenue for Client Beta has now been earned.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the $400 of earned revenue from Client Beta recorded?",
                    choices = listOf("Debit Cash $400, Credit Service Revenue $400", "Debit Unearned Revenue $400, Credit Service Revenue $400", "Debit Accounts Receivable $400, Credit Service Revenue $400"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Unearned Revenue (liability) is debited (decreased) by $400, and Service Revenue (equity/income) is credited (increased) by $400."
                ),
                ScenarioQuestion(
                    questionText = "What is the remaining balance in Client Beta's Unearned Revenue account after this? (Initial unearned was $1,500 on Day 17)",
                    choices = listOf("$1,500", "$1,100", "$400", "$0"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The initial unearned revenue was $1,500. After recognizing $400 as earned, $1,100 remains ($1,500 - $400)."
                ),
                ScenarioQuestion(
                    questionText = "This recognition of revenue is based on which accounting principle?",
                    choices = listOf("Matching Principle", "Conservatism Principle", "Revenue Recognition Principle (Accrual Basis)"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! The Revenue Recognition Principle dictates that revenue is recognized when it is earned, regardless of when cash is received."
                )
            )
        ),
        23 to Scenario(
            dayNumber = 23,
            monthName = getMonthNameForDay(23),
            title = "Purchase of Marketing Materials (On Credit)",
            narrative = "AAA Software orders and receives new brochures and business cards from \'PrintFast Inc.\' for $250. PrintFast Inc. provides the materials with an invoice due in 30 days.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this purchase be recorded today?",
                    choices = listOf("Debit Marketing Expense $250, Credit Cash $250", "Debit Prepaid Marketing $250, Credit Cash $250", "Debit Marketing Expense $250, Credit Accounts Payable $250"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Marketing Expense (or Office Supplies/Printing Expense) is debited for $250 as the materials are received and will be used, and Accounts Payable is credited for $250 because the payment is due later."
                ),
                ScenarioQuestion(
                    questionText = "What is the immediate impact on AAA Software's cash balance?",
                    choices = listOf("Cash decreases by $250", "Cash increases by $250", "No change to cash balance today"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Since the purchase is on credit, the cash balance is not affected today."
                ),
                ScenarioQuestion(
                    questionText = "This transaction increases both expenses and:",
                    choices = listOf("Assets", "Liabilities", "Equity"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Marketing Expense increases (which decreases equity), and Accounts Payable (a liability) increases."
                )
            )
        ),
        24 to Scenario(
            dayNumber = 24,
            monthName = getMonthNameForDay(24),
            title = "Monthly Bank Service Charges",
            narrative = "AAA Software's bank statement arrives, showing a $20 service charge for the month. The amount has been automatically deducted from the bank account.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this bank service charge recorded?",
                    choices = listOf("Debit Interest Expense, Credit Cash", "Debit Bank Service Charge Expense, Credit Cash", "Debit Accounts Payable, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Bank Service Charge Expense (or Bank Fees Expense) is debited for $20, and Cash is credited for $20."
                ),
                ScenarioQuestion(
                    questionText = "What is the impact of this transaction on AAA Software's net income?",
                    choices = listOf("Net income increases by $20", "Net income decreases by $20", "No effect on net income"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Expenses reduce net income. The Bank Service Charge is an expense, so it decreases net income by $20."
                ),
                ScenarioQuestion(
                    questionText = "This automatic deduction is often discovered through which accounting process?",
                    choices = listOf("Sales Invoicing", "Bank Reconciliation", "Inventory Count"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Bank reconciliation involves comparing the company's cash records to the bank statement to identify discrepancies, unrecorded transactions (like bank charges or interest), and errors."
                )
            )
        ),
        25 to Scenario(
            dayNumber = 25,
            monthName = getMonthNameForDay(25),
            title = "Payment to SparkleClean Co.",
            narrative = "AAA Software pays the $100 invoice received from SparkleClean Co. on Day 19 for office cleaning services. The payment is made via bank transfer.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the journal entry for this payment?",
                    choices = listOf("Debit Cleaning Expense, Credit Cash", "Debit Accounts Payable, Credit Cash", "Debit Cash, Credit Accounts Payable"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Payable (liability) is debited to decrease the amount owed, and Cash (asset) is credited to reflect the payment."
                ),
                ScenarioQuestion(
                    questionText = "How does this payment affect AAA Software's total liabilities?",
                    choices = listOf("Total liabilities increase by $100", "Total liabilities decrease by $100", "No change in total liabilities"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Paying off the Accounts Payable reduces total liabilities."
                ),
                ScenarioQuestion(
                    questionText = "Was Cleaning Expense affected by *this specific payment* transaction?",
                    choices = listOf("Yes, Cleaning Expense increases", "No, Cleaning Expense was recorded when the service was received", "Yes, Cleaning Expense decreases"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Cleaning Expense was already recorded on Day 19 when the service was performed and the invoice received (accrual basis). This payment transaction only affects assets and liabilities."
                )
            )
        ),
        26 to Scenario(
            dayNumber = 26,
            monthName = getMonthNameForDay(26),
            title = "Client Alpha - Small Follow-up Work (Cash)",
            narrative = "Client Alpha requests a small, urgent follow-up consultation. AAA Software provides 2 hours of service at $75/hour and is paid $150 in cash immediately upon completion.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this transaction with Client Alpha recorded?",
                    choices = listOf("Debit Cash, Credit Accounts Receivable", "Debit Cash, Credit Service Revenue", "Debit Accounts Receivable, Credit Service Revenue"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (asset) is debited for $150 because it was received immediately, and Service Revenue (equity/income) is credited for $150 as the service was performed."
                ),
                ScenarioQuestion(
                    questionText = "Since cash was received immediately, is an Accounts Receivable account used in this entry?",
                    choices = listOf("Yes, to track client activity", "No, Accounts Receivable is only for sales on credit", "Yes, but it's immediately credited"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Receivable is used when clients owe money for services already provided. Since cash was paid immediately, there is no amount receivable."
                ),
                ScenarioQuestion(
                    questionText = "What is the impact on AAA Software's total assets from this transaction?",
                    choices = listOf("Assets increase by $150 (Cash)", "Assets decrease by $150", "No net change in assets"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! Cash, an asset, increases by $150. This is balanced by an increase in Service Revenue (which increases equity)."
                )
            )
        ),
        27 to Scenario(
            dayNumber = 27,
            monthName = getMonthNameForDay(27),
            title = "Advance Payment for Conference Registration",
            narrative = "AAA Software pays $300 to register for an important industry conference that will take place in two months (Day 80 approximation).",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this $300 payment be recorded today?",
                    choices = listOf("Debit Conference Expense, Credit Cash", "Debit Prepaid Conference Fees (or Prepaid Expense), Credit Cash", "Debit Travel Expense, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Since the conference (the benefit) is in the future, the $300 is recorded as an asset, Prepaid Conference Fees (or a general Prepaid Expense)."
                ),
                ScenarioQuestion(
                    questionText = "When will the Conference Expense be recognized?",
                    choices = listOf("Today, when paid", "On the first day of the conference", "When the owner returns from the conference"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The expense should be recognized in the period the conference occurs and its benefit is derived, typically when the conference takes place."
                ),
                ScenarioQuestion(
                    questionText = "This treatment aligns with which accounting principle?",
                    choices = listOf("Revenue Recognition Principle", "Matching Principle", "Full Disclosure Principle"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Matching Principle aims to match expenses with the revenues they help generate, or to the period in which the benefit is consumed. The conference expense is matched to the period of the conference itself."
                )
            )
        ),
        28 to Scenario(
            dayNumber = 28,
            monthName = getMonthNameForDay(28),
            title = "End of Month: Adjusting Entry for Software License",
            narrative = "It's the end of the (approximate) first month since purchasing the annual software license on Day 16 for $1,200. An adjusting entry is needed.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the adjusting entry to recognize one month of software expense?",
                    choices = listOf("Debit Software Expense $1,200, Credit Prepaid Software License $1,200", "Debit Software Expense $100, Credit Prepaid Software License $100", "Debit Prepaid Software License $100, Credit Software Expense $100"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! One month of the license has been used ($1,200 / 12 months = $100). Software Expense is debited, and the asset Prepaid Software License is credited (decreased)."
                ),
                ScenarioQuestion(
                    questionText = "What is the balance of the \'Prepaid Software License\' account after this adjustment?",
                    choices = listOf("$1,200", "$1,100", "$100", "$0"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The initial prepaid amount was $1,200. After one month's expense of $100, the remaining asset balance is $1,100."
                ),
                ScenarioQuestion(
                    questionText = "Why are adjusting entries like this necessary?",
                    choices = listOf("To correct errors made during the month.", "To ensure revenues and expenses are recorded in the correct period (Accrual Basis).", "To prepare for cash payments."),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Adjusting entries are crucial for accrual basis accounting to accurately report financial performance and position by recognizing revenues when earned and expenses when incurred, regardless of cash flow timing."
                )
            )
        ),
        // Week 5 Start (Day 29-35)
        29 to Scenario(
            dayNumber = 29,
            monthName = getMonthNameForDay(29),
            title = "Client Gamma Project Completion & Final Billing",
            narrative = "AAA Software completes all work for Client Gamma. The remaining $200 of Unearned Revenue is now earned. Additionally, Client Gamma is billed for the final $3,000 of the $5,000 project value (Net 30 terms).",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the journal entry to recognize the remaining $200 of Unearned Revenue as earned?",
                    choices = listOf("Debit Unearned Revenue $200, Credit Service Revenue $200", "Debit Cash $200, Credit Service Revenue $200", "Debit Service Revenue $200, Credit Unearned Revenue $200"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! Unearned Revenue (liability) is debited to decrease it, and Service Revenue (equity/income) is credited to recognize the earnings."
                ),
                ScenarioQuestion(
                    questionText = "What is the journal entry to bill Client Gamma for the final $3,000?",
                    choices = listOf("Debit Cash $3,000, Credit Service Revenue $3,000", "Debit Accounts Receivable $3,000, Credit Service Revenue $3,000", "Debit Unearned Revenue $3,000, Credit Service Revenue $3,000"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Receivable (asset) is debited for $3,000 as Client Gamma now owes this amount, and Service Revenue is credited for $3,000 as this portion of the project is now earned and billed."
                ),
                ScenarioQuestion(
                    questionText = "What is the total Service Revenue recognized from Client Gamma *today* (from both parts of the narrative)?",
                    choices = listOf("$200", "$3,000", "$3,200", "$5,000"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! $200 from the retainer being earned plus $3,000 from the new billing equals $3,200 in Service Revenue recognized today."
                )
            )
        ),
        30 to Scenario(
            dayNumber = 30,
            monthName = getMonthNameForDay(30),
            title = "Payment for Marketing Materials from PrintFast Inc.",
            narrative = "AAA Software pays the $250 invoice to PrintFast Inc. for the marketing materials received on Day 23.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the journal entry for this payment?",
                    choices = listOf("Debit Marketing Expense $250, Credit Cash $250", "Debit Accounts Payable $250, Credit Cash $250", "Debit Prepaid Marketing $250, Credit Cash $250"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Payable (liability) is debited for $250 to reduce the amount owed, and Cash (asset) is credited for $250 for the payment made."
                ),
                ScenarioQuestion(
                    questionText = "How does this transaction affect AAA Software's total liabilities?",
                    choices = listOf("Increases by $250", "Decreases by $250", "No change"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Paying off the Accounts Payable liability reduces total liabilities."
                ),
                ScenarioQuestion(
                    questionText = "Was Marketing Expense affected by *this specific payment transaction*?",
                    choices = listOf("Yes, Marketing Expense increases", "No, Marketing Expense was recorded when the materials were received", "Yes, Marketing Expense decreases"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Marketing Expense was recorded on Day 23 when the materials were received and the obligation incurred. This transaction is just the settlement of that obligation."
                )
            )
        ),
        33 to Scenario(
            dayNumber = 33,            monthName = getMonthNameForDay(33),
            title = "Client Beta - Project Milestone & Revenue Recognition",
            narrative = "AAA Software reaches a significant milestone in the Client Beta project. Based on work completed, $700 of the previously unearned revenue for Client Beta is now considered earned. (Recall $1,100 was unearned after Day 22).",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the $700 of earned revenue from Client Beta recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Unearned Revenue, Credit Service Revenue", "Debit Accounts Receivable, Credit Service Revenue"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Unearned Revenue (liability) is debited (decreased) by $700, and Service Revenue (equity/income) is credited (increased) by $700."
                ),
                ScenarioQuestion(
                    questionText = "What is the new balance in Client Beta's Unearned Revenue account?",
                    choices = listOf("$1,100", "$700", "$400", "$0"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! The previous Unearned Revenue balance was $1,100. After recognizing $700 as earned, $400 remains ($1,100 - $700)."
                ),
                ScenarioQuestion(
                    questionText = "This revenue recognition impacts which financial statement directly by increasing net income?",
                    choices = listOf("Balance Sheet", "Income Statement", "Cash Flow Statement"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Recognizing revenue increases total revenues on the Income Statement, which in turn increases Net Income."
                )
            )
        ),
        34 to Scenario(
            dayNumber = 34,
            monthName = getMonthNameForDay(34),
            title = "Owner Makes Additional Investment",
            narrative = "To support further growth and upcoming larger projects, you, the owner, invest an additional $10,000 into AAA Software from your personal funds.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this additional owner investment recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Cash, Credit Owner\\'s Capital", "Debit Owner\\'s Drawings, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (asset) increases, and Owner\\'s Capital (equity) increases, reflecting the additional investment by the owner."
                ),
                ScenarioQuestion(
                    questionText = "What is the effect on the business\\'s total equity?",
                    choices = listOf("Equity decreases", "Equity increases", "No effect on equity"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! An owner\\'s investment increases the owner\\'s stake in the company, thus increasing equity."
                ),
                ScenarioQuestion(
                    questionText = "Is this transaction considered revenue for the business?",
                    choices = listOf("Yes, because cash increased", "No, it\\'s a capital contribution", "Only if the business is profitable"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Owner investments are contributions to capital (equity), not revenue generated from business operations."
                )
            )
        ),
        35 to Scenario(
            dayNumber = 35,
            monthName = getMonthNameForDay(35),
            title = "Client Beta Project Completion & Final Billing",
            narrative = "AAA Software completes all remaining work for Client Beta. The final $400 of Unearned Revenue from their initial $1,500 retainer is now earned. Additionally, Client Beta is billed for the remaining $1,500 of the $3,000 project value (Net 30 terms).",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the journal entry to recognize the final $400 of Unearned Revenue for Client Beta as earned?",
                    choices = listOf("Debit Unearned Revenue $400, Credit Service Revenue $400", "Debit Cash $400, Credit Service Revenue $400", "Debit Service Revenue $400, Credit Unearned Revenue $400"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! Unearned Revenue (liability) is debited to decrease it as the service is now fully provided, and Service Revenue (equity/income) is credited."
                ),
                ScenarioQuestion(
                    questionText = "What is the journal entry to bill Client Beta for the final $1,500 portion of the project?",
                    choices = listOf("Debit Cash $1,500, Credit Service Revenue $1,500", "Debit Accounts Receivable $1,500, Credit Service Revenue $1,500", "Debit Unearned Revenue $1,500, Credit Service Revenue $1,500"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Receivable (asset) is debited for $1,500 as Client Beta now owes this amount, and Service Revenue is credited as this work is now completed and billed."
                ),
                ScenarioQuestion(
                    questionText = "What is the total Service Revenue recognized from Client Beta *today* (from both parts of this scenario)?",
                    choices = listOf("$400", "$1,500", "$1,900", "$3,000"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! $400 from the retainer being fully earned plus $1,500 from the new billing equals $1,900 in Service Revenue recognized today for Client Beta."
                )
            )
        ),
        36 to Scenario(
            dayNumber = 36,
            monthName = getMonthNameForDay(36),
            title = "End-of-Month: Adjusting Entry for Prepaid Advertising",
            narrative = "It's the end of the month. AAA Software paid $200 for an advertising campaign on Day 15, assuming it was for one month. An adjusting entry is needed if it was initially recorded as Prepaid Advertising, or to confirm the expense if directly expensed.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "If the $200 on Day 15 was initially debited to 'Prepaid Advertising', what is the adjusting entry now?",
                    choices = listOf("Debit Advertising Expense $200, Credit Prepaid Advertising $200", "Debit Prepaid Advertising $200, Credit Advertising Expense $200", "No entry needed if already expensed"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! To recognize the portion of advertising used this month, Advertising Expense is debited and Prepaid Advertising (asset) is credited/decreased."
                ),
                ScenarioQuestion(
                    questionText = "If the $200 was debited directly to 'Advertising Expense' on Day 15, is an adjusting entry for *this specific item* needed now?",
                    choices = listOf("Yes, to move it to Prepaid", "No, it was already fully expensed in the correct period", "Yes, to reverse it"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! If it was entirely for the current month's benefit and expensed on Day 15, no further adjustment for *this specific item* is needed at month-end as it's already correctly expensed."
                ),
                ScenarioQuestion(
                    questionText = "Adjusting entries primarily ensure adherence to which accounting principle?",
                    choices = listOf("Cost Principle", "Matching Principle", "Conservatism Principle"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Matching Principle dictates that expenses are recognized in the same period as the revenues they help generate, or when the benefit is consumed."
                )
            )
        ),
        37 to Scenario(
            dayNumber = 37,
            monthName = getMonthNameForDay(37),
            title = "Receipt of Bank Loan",
            narrative = "To finance a planned expansion, AAA Software secures a short-term bank loan of $5,000. The cash is deposited into the business bank account today.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the receipt of this $5,000 bank loan recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Cash, Credit Notes Payable (or Bank Loan Payable)", "Debit Cash, Credit Owner's Capital"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (asset) increases by $5,000, and Notes Payable or Bank Loan Payable (a liability) increases by $5,000, representing the obligation to repay the loan."
                ),
                ScenarioQuestion(
                    questionText = "This transaction increases AAA Software's assets and also its:",
                    choices = listOf("Equity", "Expenses", "Liabilities"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Cash (asset) increases, and the obligation to repay the loan (liability) also increases."
                ),
                ScenarioQuestion(
                    questionText = "Is the principal amount of the loan ($5,000) considered income to the business?",
                    choices = listOf("Yes, because cash was received", "No, it's a borrowed fund that must be repaid", "Only if interest is not charged"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The loan principal is a liability, not income. Only the interest incurred on the loan is an expense."
                )
            )
        ),
        38 to Scenario(
            dayNumber = 38,
            monthName = getMonthNameForDay(38),
            title = "Payment of Interest on Bank Loan",
            narrative = "AAA Software makes its first monthly interest payment of $50 on the bank loan obtained on Day 37. This payment is for interest only.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this $50 interest payment recorded?",
                    choices = listOf("Debit Notes Payable, Credit Cash", "Debit Interest Expense, Credit Cash", "Debit Interest Receivable, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Interest Expense is debited for $50 to recognize the cost of borrowing, and Cash is credited for $50."
                ),
                ScenarioQuestion(
                    questionText = "What effect does this interest payment have on AAA Software's net income?",
                    choices = listOf("Increases net income by $50", "Decreases net income by $50", "No effect on net income"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Interest Expense reduces net income."
                ),
                ScenarioQuestion(
                    questionText = "Does this $50 payment reduce the principal amount of the loan?",
                    choices = listOf("Yes, by $50", "No, this payment is for interest only", "Yes, but only partially"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The narrative states this is an interest-only payment, so the loan principal (Notes Payable) remains unchanged by this specific transaction."
                )
            )
        ),
        39 to Scenario(
            dayNumber = 39,
            monthName = getMonthNameForDay(39),
            title = "Client Epsilon - New Project (Services on Account)",
            narrative = "AAA Software completes a project for a new client, \\'Client Epsilon\\', for $1,200. An invoice is sent with Net 30 terms.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this transaction with Client Epsilon recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Accounts Receivable, Credit Service Revenue", "Debit Service Revenue, Credit Accounts Receivable"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Receivable (asset) is debited for $1,200 (amount owed by client), and Service Revenue (equity/income) is credited for $1,200 (revenue earned)."
                ),
                ScenarioQuestion(
                    questionText = "This transaction will increase AAA Software's total assets by:",
                    choices = listOf("$0 (Cash hasn't changed)", "$1,200 (due to Accounts Receivable)", "Net effect is zero on assets"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Accounts Receivable is an asset, so total assets increase by $1,200. Equity also increases due to revenue."
                ),
                ScenarioQuestion(
                    questionText = "When will cash be affected by this specific project with Client Epsilon?",
                    choices = listOf("Today, when invoiced", "When Client Epsilon pays the invoice", "Never, it's on account"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash will increase when Client Epsilon pays the invoice within the 30-day term."
                )
            )
        ),
        40 to Scenario(
            dayNumber = 40,
            monthName = getMonthNameForDay(40),
            title = "Purchase of Specialized Software (One-time, on credit)",
            narrative = "AAA Software purchases highly specialized, non-subscription software for $600 from \\'TechSolutions Co.\\' This software is expected to be used for several years. Payment is due in 45 days.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this software purchase be recorded?",
                    choices = listOf("Debit Software Expense, Credit Accounts Payable", "Debit Intangible Asset - Software, Credit Accounts Payable", "Debit Prepaid Software, Credit Accounts Payable"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Since the software will be used for several years, it's capitalized as an Intangible Asset. Accounts Payable is credited as it's purchased on credit."
                ),
                ScenarioQuestion(
                    questionText = "The cost of this software will be expensed over its useful life through a process called:",
                    choices = listOf("Depreciation", "Amortization", "Depletion"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Amortization is used to allocate the cost of intangible assets (like this software) over their useful lives."
                ),
                ScenarioQuestion(
                    questionText = "What is the immediate impact on AAA Software's cash balance?",
                    choices = listOf("Decreases by $600", "No change to cash balance today", "Increases by $600"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Since the purchase is on credit, the cash balance is not affected today."
                )
            )
        ),
        41 to Scenario(
            dayNumber = 41,
            monthName = getMonthNameForDay(41),
            title = "Payment to OfficeStyle Inc. for Furniture",
            narrative = "AAA Software pays the $800 invoice to OfficeStyle Inc. for the office furniture purchased on Day 32.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the journal entry for this payment?",
                    choices = listOf("Debit Office Furniture, Credit Cash", "Debit Office Expense, Credit Cash", "Debit Accounts Payable, Credit Cash"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Accounts Payable (liability) is debited for $800 to reduce the amount owed, and Cash (asset) is credited for $800 for the payment made."
                ),
                ScenarioQuestion(
                    questionText = "How does this transaction affect AAA Software's total assets?",
                    choices = listOf("Total assets decrease by $800", "Total assets increase by $800", "No net change in total assets"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! Cash (an asset) decreases by $800. Office Furniture (another asset) is unchanged by this payment of a liability."
                ),
                ScenarioQuestion(
                    questionText = "Was an expense recognized when *this payment* was made?",
                    choices = listOf("Yes, Office Expense of $800", "No, the asset was recorded previously, and expense (depreciation) occurs over time", "Yes, Furniture Expense of $800"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Office Furniture asset was recorded on Day 32. This transaction is merely settling the liability. Depreciation expense will be recorded over its useful life."
                )
            )
        ),
        42 to Scenario(
            dayNumber = 42,
            monthName = getMonthNameForDay(42),
            title = "End of Month: Adjusting Entry for Office Supplies Used",
            narrative = "It's (approximately) month-end. AAA Software started with $500 of supplies (Day 3). A count reveals $150 of supplies are still on hand.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How much Office Supplies were used during this period?",
                    choices = listOf("$150", "$500", "$350"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Supplies used = Beginning Supplies - Ending Supplies ($500 - $150 = $350)."
                ),
                ScenarioQuestion(
                    questionText = "What is the adjusting entry to record the supplies used?",
                    choices = listOf("Debit Supplies Expense $150, Credit Supplies $150", "Debit Supplies $350, Credit Supplies Expense $350", "Debit Supplies Expense $350, Credit Supplies $350"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Supplies Expense is debited for the amount used ($350), and the Supplies asset account is credited (reduced) by the same amount."
                ),
                ScenarioQuestion(
                    questionText = "What is the balance of the Supplies (asset) account after this adjustment?",
                    choices = listOf("$500", "$350", "$150", "$0"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! The Supplies account now reflects the amount physically on hand ($150)."
                )
            )
        ),
        43 to Scenario(
            dayNumber = 43,
            monthName = getMonthNameForDay(43),
            title = "Cash Receipt from Client Gamma (Final Payment)",
            narrative = "Client Gamma pays the remaining $2,000 on their outstanding invoice. (Recall $1,000 was paid on Day 31 from a $3,000 billing on Day 29).",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this $2,000 cash receipt recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Accounts Receivable, Credit Cash", "Debit Cash, Credit Accounts Receivable"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Cash (asset) increases by $2,000, and Accounts Receivable (asset) for Client Gamma decreases by $2,000."
                ),
                ScenarioQuestion(
                    questionText = "What is the Accounts Receivable balance from Client Gamma after this payment?",
                    choices = listOf("$2,000", "$1,000", "$0", "$3,000"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! With this final $2,000 payment, Client Gamma's Accounts Receivable balance is now $0."
                ),
                ScenarioQuestion(
                    questionText = "This transaction primarily affects which section of the Statement of Cash Flows?",
                    choices = listOf("Operating Activities", "Investing Activities", "Financing Activities"),
                    correctAnswerIndex = 0,
                    explanation = "Correct! Collections from customers (Accounts Receivable) are a primary component of cash flows from Operating Activities."
                )
            )
        ),
        44 to Scenario(
            dayNumber = 44,
            monthName = getMonthNameForDay(44),
            title = "Training Course Fee for Owner (Business Expense)",
            narrative = "AAA Software pays $400 for you, the owner, to attend an online advanced software development training course directly relevant to the business's services. Payment is made via bank transfer.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this $400 payment be recorded?",
                    choices = listOf("Debit Owner's Drawings, Credit Cash", "Debit Training Expense (or Professional Development Expense), Credit Cash", "Debit Prepaid Training, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Since the training is for the owner but directly benefits the business operations, it's a business expense (Training Expense or Professional Development). Cash decreases."
                ),
                ScenarioQuestion(
                    questionText = "This $400 payment will affect the Income Statement by:",
                    choices = listOf("Increasing Revenue by $400", "Increasing Expenses by $400", "Having no effect on the Income Statement"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Training Expense increases total expenses, which reduces net income on the Income Statement."
                ),
                ScenarioQuestion(
                    questionText = "If this course was a personal hobby unrelated to AAA Software's business, how would the payment likely be treated?",
                    choices = listOf("Still as Training Expense", "As Owner's Drawings", "As a business loan to the owner"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! If for personal benefit unrelated to the business, it would be treated as an Owner's Drawing (a reduction in equity)."
                )
            )
        ),
        45 to Scenario(
            dayNumber = 45,
            monthName = getMonthNameForDay(45),
            title = "Small Repair Expense (Cash)",
            narrative = "A piece of office equipment required a minor repair costing $60. AAA Software paid for this repair in cash immediately.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this $60 cash payment for repairs recorded?",
                    choices = listOf("Debit Equipment Asset, Credit Cash", "Debit Repair & Maintenance Expense, Credit Cash", "Debit Accounts Payable, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Repair & Maintenance Expense is debited for $60 as it's a cost of maintaining current operational capacity, and Cash is credited for $60."
                ),
                ScenarioQuestion(
                    questionText = "This repair cost is considered an operating expense because:",
                    choices = listOf("It increases the value of the equipment.", "It extends the life of the equipment significantly.", "It keeps the equipment in normal working order without enhancing it significantly."),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Routine repairs and maintenance that keep assets in normal working condition are expensed. Significant enhancements or life-extending repairs might be capitalized."
                ),
                ScenarioQuestion(
                    questionText = "How does this $60 expense affect AAA Software's equity?",
                    choices = listOf("Equity increases by $60", "Equity decreases by $60", "No effect on equity"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Expenses reduce Net Income, and a reduction in Net Income leads to a reduction in Retained Earnings, which is a component of equity."
                )
            )
        ),
        46 to Scenario(
            dayNumber = 46,
            monthName = getMonthNameForDay(46),
            title = "Client Beta Pays Final Invoice",
            narrative = "Client Beta pays their outstanding invoice of $1,500 that was billed on Day 35. The payment is received today.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the journal entry for receiving this $1,500 payment from Client Beta?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Cash, Credit Accounts Receivable", "Debit Accounts Payable, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (asset) increases, and Accounts Receivable (asset) for Client Beta decreases."
                ),
                ScenarioQuestion(
                    questionText = "What is the balance of Accounts Receivable for Client Beta after this payment?",
                    choices = listOf("$1,500", "$0", "$3,000"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Since they paid the full $1,500 outstanding, their Accounts Receivable balance is now $0."
                ),
                ScenarioQuestion(
                    questionText = "Did this transaction affect AAA Software's total revenue for the period?",
                    choices = listOf("Yes, revenue increases by $1,500", "No, revenue was recognized when Client Beta was billed", "Yes, cash sales always increase revenue immediately"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Revenue was recognized when the service was completed and Client Beta was billed (Day 35). This is just the collection of that receivable."
                )
            )
        ),
        47 to Scenario(
            dayNumber = 47,
            monthName = getMonthNameForDay(47),
            title = "Advance Payment for Next Month's Rent",
            narrative = "To ensure the office space is secured, AAA Software pays $1,000 for next month's rent a bit early.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How should this $1,000 advance rent payment be recorded?",
                    choices = listOf("Debit Rent Expense, Credit Cash", "Debit Prepaid Rent, Credit Cash", "Debit Accounts Payable, Credit Rent Expense"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Since the rent is for a future period (next month), it's recorded as an asset (Prepaid Rent). Cash decreases."
                ),
                ScenarioQuestion(
                    questionText = "When will Rent Expense be recognized for this $1,000 payment?",
                    choices = listOf("Today, when paid", "At the end of this current month", "During the next month, when the rent period applies"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! The Rent Expense will be recognized in the next month, to which the rent payment pertains, via an adjusting entry (Debit Rent Expense, Credit Prepaid Rent)."
                ),
                ScenarioQuestion(
                    questionText = "What type of account is Prepaid Rent?",
                    choices = listOf("Liability", "Asset", "Expense", "Equity"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Prepaid Rent is a current asset, representing a future benefit (use of office space) for which payment has been made."
                )
            )
        ),
        48 to Scenario(
            dayNumber = 48,
            monthName = getMonthNameForDay(48),
            title = "Donation to Local Charity",
            narrative = "AAA Software makes a cash donation of $100 to a local registered charity.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this $100 cash donation recorded?",
                    choices = listOf("Debit Owner's Drawings, Credit Cash", "Debit Miscellaneous Expense, Credit Cash", "Debit Charitable Contributions Expense, Credit Cash"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Charitable Contributions Expense (or Donations Expense) is debited, and Cash is credited."
                ),
                ScenarioQuestion(
                    questionText = "This charitable donation will:",
                    choices = listOf("Increase net income", "Decrease net income", "Have no effect on net income, as it's not an operating expense"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Charitable contributions made by the business are typically considered an expense, thus reducing net income."
                ),
                ScenarioQuestion(
                    questionText = "Are business donations always tax-deductible?",
                    choices = listOf("Yes, all donations reduce taxes equally.", "Not necessarily; rules vary by jurisdiction and type of charity.", "No, donations are never tax-deductible for businesses."),
                    correctAnswerIndex = 1,
                    explanation = "Correct! While often deductible, the specifics of tax deductibility for charitable donations can vary based on tax laws, the recipient organization's status, and limits."
                )
            )
        ),
        49 to Scenario(
            dayNumber = 49,
            monthName = getMonthNameForDay(49),
            title = "End of Month: Adjusting Entry for Depreciation of Furniture",
            narrative = "It's (approximately) month-end. Office furniture was purchased on Day 32 for $800. Assume a useful life of 5 years (60 months) and no salvage value. Calculate one month's depreciation.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "What is the monthly depreciation expense for the office furniture? ($800 / 60 months)",
                    choices = listOf("$16.00", "$13.33", "$80.00", "$60.00"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Monthly depreciation = Cost / Useful life in months = $800 / 60 = $13.33 (approximately)."
                ),
                ScenarioQuestion(
                    questionText = "What is the adjusting entry to record one month of depreciation?",
                    choices = listOf("Debit Office Furniture, Credit Depreciation Expense", "Debit Depreciation Expense, Credit Accumulated Depreciation - Furniture", "Debit Depreciation Expense, Credit Office Furniture"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Depreciation Expense is debited, and Accumulated Depreciation - Furniture (a contra-asset account) is credited."
                ),
                ScenarioQuestion(
                    questionText = "What is the book value of the furniture after this first month of depreciation? (Cost - Accumulated Depreciation)",
                    choices = listOf("$800.00", "$786.67", "$13.33", "$720.00"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Book Value = Cost ($800) - Accumulated Depreciation ($13.33) = $786.67."
                )
            )
        ),
        50 to Scenario(
            dayNumber = 50,
            monthName = getMonthNameForDay(50),
            title = "Collection from Client Epsilon",
            narrative = "Client Epsilon, who was invoiced $1,200 on Day 39 (Net 30 terms), pays their bill in full today.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is the receipt of $1,200 from Client Epsilon recorded?",
                    choices = listOf("Debit Cash, Credit Service Revenue", "Debit Cash, Credit Accounts Receivable", "Debit Accounts Receivable, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Cash (asset) increases by $1,200, and Accounts Receivable (asset) for Client Epsilon decreases by $1,200."
                ),
                ScenarioQuestion(
                    questionText = "This transaction increases cash and decreases another asset. What is the net effect on total assets?",
                    choices = listOf("Total assets increase by $1,200", "Total assets decrease by $1,200", "Total assets remain unchanged"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! One asset (Cash) increases, while another asset (Accounts Receivable) decreases by the same amount. Thus, total assets are unchanged by this specific transaction."
                ),
                ScenarioQuestion(
                    questionText = "Was Service Revenue affected by this cash collection?",
                    choices = listOf("Yes, Service Revenue increases by $1,200", "No, Service Revenue was recognized when the invoice was sent on Day 39", "Yes, Service Revenue decreases as the receivable is cleared"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Service Revenue was already recognized on Day 39 when the service was performed and invoiced (accrual basis accounting)."
                )
            )
        ),
        51 to Scenario(
            dayNumber = 51,
            monthName = getMonthNameForDay(51),
            title = "Owner Withdrawal (Large, for Taxes/Personal)",
            narrative = "You, the owner, withdraw $2,500 from the business for estimated personal income taxes and other personal needs.",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "How is this $2,500 owner withdrawal recorded?",
                    choices = listOf("Debit Salary Expense, Credit Cash", "Debit Owner's Drawings, Credit Cash", "Debit Tax Expense, Credit Cash"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Owner's Drawings (or Owner's Withdrawals), an equity account, is debited for $2,500, and Cash (asset) is credited for $2,500."
                ),
                ScenarioQuestion(
                    questionText = "What is the impact of this withdrawal on the business's total equity?",
                    choices = listOf("Equity increases by $2,500", "Equity decreases by $2,500", "No direct effect on equity"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! Owner's Drawings reduce the owner's capital/equity in the business."
                ),
                ScenarioQuestion(
                    questionText = "Is the business's 'Tax Expense' account affected by the owner's *personal* income tax withdrawal?",
                    choices = listOf("Yes, the business pays the owner's taxes.", "No, personal income taxes of the owner are separate from the business's operating tax expenses (if any, for a sole proprietorship).", "Only if the business is incorporated."),
                    correctAnswerIndex = 1,
                    explanation = "Correct! For a sole proprietorship, the owner's personal income taxes are their personal responsibility. The withdrawal is a distribution of equity, not a business tax expense shown on the business's income statement."
                )
            )
        ),
        52 to Scenario(
            dayNumber = 52,
            monthName = getMonthNameForDay(52),
            title = "End of Simulation: Year-End Review & Planning",
            narrative = "It's the end of the 52-day (simulated year) journey for AAA Software! It's time to review the financial statements (Income Statement, Balance Sheet, Statement of Cash Flows), assess performance, and plan for the next 'year'. What an adventure!",
            questions = listOf(
                ScenarioQuestion(
                    questionText = "Which financial statement best summarizes a company's financial performance (revenues and expenses) over a specific period?",
                    choices = listOf("Balance Sheet", "Income Statement", "Statement of Cash Flows"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Income Statement (also known as Profit & Loss or P&L) shows revenues, expenses, and the resulting net income or loss over a period."
                ),
                ScenarioQuestion(
                    questionText = "Which statement shows a company's assets, liabilities, and equity at a specific point in time?",
                    choices = listOf("Income Statement", "Balance Sheet", "Trial Balance"),
                    correctAnswerIndex = 1,
                    explanation = "Correct! The Balance Sheet provides a snapshot of the company's financial position (Assets = Liabilities + Equity) on a specific date."
                ),
                ScenarioQuestion(
                    questionText = "The process of transferring net income (or loss) and owner's drawings to the owner's capital account at year-end is part of:",
                    choices = listOf("Daily transaction recording", "Adjusting entries", "Closing entries"),
                    correctAnswerIndex = 2,
                    explanation = "Correct! Closing entries are made at the end of an accounting period to zero out temporary accounts (revenues, expenses, drawings) and transfer their balances to permanent equity accounts (Owner's Capital)."
                )
            )
        )
    )

    fun getScenarioForDay(dayNumber: Int): Scenario? {
        return allScenarios[dayNumber]
    }
}
