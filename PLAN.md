# AAA Software Development Plan

This document outlines the development plan for the "AAA Software" Android application.

## Overall Progress

*   [X] **Phase 1: Project Setup & Core Infrastructure**
*   [X] **Phase 2: Scenario Engine & Game Loop (Interactive Questions Complete)**
*   [ ] **Phase 3: Journal Entry & Financial Statements**
*   [ ] **Phase 4: UI Implementation (Screens 1-3)**
*   [ ] **Phase 5: Daily Notifications**
*   [ ] **Phase 6: Data Persistence (Room DB Setup & Pre-population)**
*   [ ] **Phase 7: Content - 52-Day Scenario Script**
*   [ ] **Phase 8: Testing & Refinement**

## Current Phase: Phase 3 & 4: Journal Entry UI & Logic (Screen 3)

### Steps:

1.  **[TODO]** Create `JournalEntryViewModel.kt`:
    *   It will manage UI state for the journal entry screen (description, selected debit/credit accounts, debit/credit amounts).
    *   It will need access to `AppDatabase` (specifically `AccountDao` to fetch accounts for dropdowns, and `JournalEntryDao`/`TransactionDao` to save entries).
    *   Expose a list of all accounts (`Flow<List<Account>>`) for selection.
    *   Implement functions to update description, selected accounts, and amounts.
    *   Implement a function `postJournalEntry()` that will:
        *   Validate the entry (e.g., debits = credits, accounts selected, amounts > 0).
        *   Create `JournalEntry` and `Transaction` objects.
        *   Save them to the database using the DAOs.
        *   Update `Account` balances.
        *   (Later) Trigger day advancement in `AppProgressRepository`.
2.  **[TODO]** Create `JournalEntryScreen.kt`:
    *   Implement the UI for Screen 3 as per the mockups:
        *   Text field for "Description".
        *   Dropdown (e.g., `ExposedDropdownMenuBox`) for "Debit Account" (populated from `JournalEntryViewModel`).
        *   Text field for "Debit Amount".
        *   Dropdown for "Credit Account".
        *   Text field for "Credit Amount".
        *   Display of current Debit/Credit totals (should always be equal if input is valid).
        *   "Post Journal Entry" button.
3.  **[TODO]** Implement Navigation:
    *   Modify `ScenarioViewModel`'s `proceedToJournalEntry()` to navigate to `JournalEntryScreen`.
    *   This will involve setting up Jetpack Compose Navigation with routes for `ScenarioScreen` and `JournalEntryScreen`.
    *   Update `MainActivity.kt` to host the `NavHost`.

---

## Detailed Phase Breakdown

### Phase 1: Project Setup & Core Infrastructure
*   [X] Review initial requirements.
*   [X] Create `PLAN.md` to track progress.
*   [X] Create `README.md` to summarize the project.
*   [X] Define base project structure (folders for MVVM, data, UI components, etc.).
*   [X] Add necessary dependencies to `build.gradle.kts` (Room, Coroutines, ViewModel, Compose Navigation, etc.).
*   [X] Set up basic MVVM architecture for a sample screen.
*   [X] Create initial Room Entity data classes (`Account`, `JournalEntry`, `Transaction`, `AppProgress`, and supporting enums).
*   [X] Create initial DAO interfaces for Room.
*   [X] Create Room Database class with TypeConverters.

### Phase 2: Scenario Engine & Game Loop
*   [X] **App Progress Tracking:**
    *   [X] Implement logic to read/write current day using Room (`AppProgress` entity). Initialize day to 1 if no progress exists.
*   [X] **Scenario Presentation:**
    *   [X] Create data structures/classes to hold scenario details (day, month, title, narrative, questions with choices, correct answer index, explanation).
    *   [X] Develop a `ScenarioRepository` or similar to fetch the current day's scenario. (Hardcoded Day 1).
    *   [X] Implement ViewModel (`ScenarioViewModel`) to manage and expose scenario data (scenario object, current question index, display mode) to the UI.
    *   [X] Basic UI (`ScenarioScreen`) to display scenario narrative (Screen 1 Mockup).
*   [X] **Interactive Multiple-Choice Questions (Implementation for Screen 2):**
    *   [X] UI (`QuestionView` in `ScenarioScreen`) to display current question text and multiple-choice options.
    *   [X] ViewModel (`ScenarioViewModel`) logic for answer selection, validation, and exposing feedback.
    *   [X] UI to display answer feedback (correctness and explanation).
    *   [X] Logic to navigate through questions and proceed after all questions are answered (currently calls placeholder `proceedToJournalEntry()`).
*   [ ] **Sequential Scenario Flow (Day Advancement):**
    *   [ ] Ensure `currentDay` in `AppProgress` is advanced after a day's full activities (scenario, questions, journal entry) are complete. Reset state for new day. (This will be implemented after journal entry posting).

### Phase 3: Journal Entry & Financial Statements
*   [ ] **Journal Entry Creation Logic:**
    *   [ ] ViewModel (`JournalEntryViewModel`) to handle user input for journal entries (description, debit account, debit amount, credit account, credit amount).
    *   [ ] Logic to create `JournalEntry` and `Transaction` objects.
    *   [ ] Save journal entries and transactions to Room database.
    *   [ ] Update `Account` balances in Room database based on transactions.
*   [ ] **Monthly "Book-Closing" Cycle:**
    *   [ ] Implement logic to detect when a "month" (approx. 4 days) has passed.
    *   [ ] Trigger book-closing process.
*   [ ] **Financial Statement Generation:**
    *   [ ] Logic to query Room for necessary data (Revenues, Expenses, Assets, Liabilities, Equity).
    *   [ ] Calculate Net Income.
    *   [ ] Assemble Income Statement data.
    *   [ ] Assemble Balance Sheet data, ensuring it balances.
    *   [ ] ViewModels to prepare financial statement data for UI display.

### Phase 4: UI Implementation (Screens 1-3)
*   [X] **Screen 1: The Scenario** (Narrative part done in Phase 2)
    *   [X] Compose UI to display Day, Month, Title, Narrative.
    *   [X] "Let's Analyze This >" button.
*   [X] **Screen 2: Guided Questions** (Done in Phase 2, integrated into `ScenarioScreen`)
    *   [X] Compose UI to display questions, multiple-choice options.
    *   [X] UI to show feedback for correct/incorrect answers.
    *   [X] Navigation to next question / "Great! Now, let's make the journal entry" button (placeholder action).
*   [ ] **Screen 3: The Hybrid Journal Entry UI**
    *   [ ] Compose UI for Description input.
    *   [ ] Compose UI for Debit Account selection (Dropdown/Spinner), Amount input.
    *   [ ] Compose UI for Credit Account selection (Dropdown/Spinner), Amount input.
    *   [ ] Display Debit/Credit totals.
    *   [ ] "Post Journal Entry" button.
*   [ ] **Financial Statement Display UI:**
    *   [ ] Compose UI to display Income Statement.
    *   [ ] Compose UI to display Balance Sheet.
*   [ ] **Navigation:**
    *   [ ] Implement Jetpack Compose Navigation to move between screens (`ScenarioScreen` to `JournalEntryScreen`, and later to Financial Statements).

### Phase 5: Daily Notifications
*   [ ] **Notification Scheduling:**
    *   [ ] Implement `NotificationManager` logic.
    *   [ ] Schedule a daily, non-intrusive notification ("AAA Software: You have new business to attend to.").
    *   [ ] Ensure notification brings the user back into the app.
    *   [ ] Handle notification permissions (if targeting Android 13+).

### Phase 6: Data Persistence (Room DB Setup & Pre-population)
*   [X] **Room Entities (Refine/Finalize):** Done in Phase 1
*   [X] **Room DAOs (Refine/Finalize):** Done in Phase 1
*   [X] **Room Database (Refine/Finalize):** Done in Phase 1
*   [X] **AppProgress Initialization:** Done in Phase 2 (via AppDatabase callback)
*   [ ] **Chart of Accounts Pre-population:**
    *   [ ] Define a standard Chart of Accounts for a service business.
    *   [ ] Implement logic to pre-populate the `Account` table on first app launch (e.g., using `RoomDatabase.Callback` in `AppDatabase.kt`).

### Phase 7: Content - 52-Day Scenario Script
*   [X] **Scenario Data Structure:** Done in Phase 2
*   [ ] **Implement Full Script:**
    *   [X] **Days 1 (Month 1):** Investment (partially done in `ScenarioRepository`)
    *   [ ] **Rest of Days 1-4 (Month 1):** Rent, Computer Purchase.
    *   [ ] **Days 5-13 (Months 2-3):** First clients, Unearned Revenue, Accounts Receivable.
    *   [ ] **Days 14-26 (Months 4-6):** Growth, Cash Collection, Revenue Recognition.
    *   [ ] **Day 27 (Mid-Year):** Hire contractor, Accrued Expenses (Accounts Payable).
    *   [ ] **Days 28-39 (Months 7-9):** Pay contractor, Accrue new expenses.
    *   [ ] **Day 40 (Month 10):** Bad Debt Write-off.
    *   [ ] **Days 41-52 (Months 11-12):** Wrap-up, final bills, final book closing.
*   [X] **Question & Answer Content:** (Day 1 questions added in `ScenarioRepository`)
    *   [ ] Develop at least three multiple-choice questions for each of the 52 scenarios, along with clear explanations for correct answers.

### Phase 8: Testing & Refinement
*   [ ] **Unit Tests:**
    *   [ ] Test ViewModels.
    *   [ ] Test Repositories.
    *   [ ] Test Room DAOs.
    *   [ ] Test business logic (e.g., financial statement calculations).
*   [ ] **UI Tests (Espresso/Compose Testing):**
    *   [ ] Test critical user flows.
*   [ ] **Manual Testing:**
    *   [ ] Play through the entire 52-day cycle.
    *   [ ] Verify notification behavior.
    *   [ ] Check financial statement accuracy.
*   [ ] **Bug Fixing & Performance Optimization.**
*   [ ] **Code Cleanup & Documentation.**

---
*This plan will be updated as development progresses.*
