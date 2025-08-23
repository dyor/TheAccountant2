# AAA Software Development Plan

This document outlines the development plan for the "AAA Software" Android application.

## Overall Progress

*   [X] **Phase 1: Project Setup & Core Infrastructure**
*   [X] **Phase 2: Scenario Engine & Game Loop (Core Loop with Day Advancement Implemented)**
*   [X] **Phase 3: Journal Entry & Financial Statements (Basic Journal Entry Logic Implemented)**
*   [X] **Phase 4: UI Implementation (Screens 1-3 Foundational UI and Navigation Implemented)**
*   [ ] **Phase 5: Daily Notifications**
*   [X] **Phase 6: Data Persistence (Chart of Accounts Pre-population Complete)**
*   [ ] **Phase 7: Content - 52-Day Scenario Script**
*   [ ] **Phase 8: Testing & Refinement**

## Current Phase: Phase 7: Content Creation (Scenarios & Questions) & Phase 3: Financial Statements

### Next Steps:

1.  **[TODO - Phase 7]** Expand `ScenarioRepository.kt` to include scenarios and questions for more days (e.g., Days 2, 3, 4).
    *   This will allow testing the day advancement loop more thoroughly.
    *   Focus on simple transactions initially (e.g., paying rent, buying supplies, providing services).
2.  **[TODO - Phase 3]** Financial Statement Generation:
    *   Logic to query Room for necessary data (Revenues, Expenses, Assets, Liabilities, Equity).
    *   Calculate Net Income.
    *   Assemble Income Statement data.
    *   Assemble Balance Sheet data, ensuring it balances.
    *   ViewModels to prepare financial statement data for UI display.
3.  **[TODO - Phase 4]** Financial Statement Display UI:
    *   Compose UI to display Income Statement.
    *   Compose UI to display Balance Sheet.
    *   Navigation to these statement screens (perhaps from a main menu or after completing a month).

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
    *   [X] Create data structures/classes to hold scenario details.
    *   [X] Develop `ScenarioRepository` (Hardcoded Day 1 initially).
    *   [X] Implement `ScenarioViewModel` to manage scenario data.
    *   [X] Basic UI (`ScenarioScreen`) for narrative.
*   [X] **Interactive Multiple-Choice Questions (Screen 2):**
    *   [X] UI (`QuestionView` in `ScenarioScreen`) for questions & choices.
    *   [X] `ScenarioViewModel` logic for selection, validation, feedback.
    *   [X] UI for answer feedback.
    *   [X] Logic to navigate through questions.
*   [X] **Sequential Scenario Flow (Day Advancement):**
    *   [X] `currentDay` in `AppProgress` is advanced after journal entry.
    *   [X] `ScenarioScreen` reloads with new day's scenario.

### Phase 3: Journal Entry & Financial Statements
*   [X] **Journal Entry Creation Logic:**
    *   [X] `JournalEntryViewModel` handles user input for journal entries.
    *   [X] Logic to create `JournalEntry` and `Transaction` objects.
    *   [X] Save entries and transactions to Room.
    *   [X] Update `Account` balances.
*   [ ] **Monthly "Book-Closing" Cycle:**
    *   [ ] Implement logic to detect when a "month" (approx. 4 days) has passed.
    *   [ ] Trigger book-closing process.
*   [ ] **Financial Statement Generation:**
    *   [ ] Logic to query Room for necessary data.
    *   [ ] Calculate Net Income.
    *   [ ] Assemble Income Statement & Balance Sheet data.
    *   [ ] ViewModels to prepare financial statement data.

### Phase 4: UI Implementation (Screens 1-3)
*   [X] **Screen 1: The Scenario**
*   [X] **Screen 2: Guided Questions** (Integrated into `ScenarioScreen`)
*   [X] **Screen 3: The Hybrid Journal Entry UI**
    *   [X] Compose UI for Description, Account selection, Amount input.
    *   [X] Display Debit/Credit totals.
    *   [X] "Post Journal Entry" button.
*   [ ] **Financial Statement Display UI:**
    *   [ ] Compose UI to display Income Statement & Balance Sheet.
*   [X] **Navigation:**
    *   [X] Implemented Jetpack Compose Navigation between `ScenarioScreen` and `JournalEntryScreen`.
    *   [X] Navigation back to `ScenarioScreen` after journal posting, triggering new day.

### Phase 5: Daily Notifications
*   [ ] **Notification Scheduling:**
    *   [ ] Implement `NotificationManager` logic.
    *   [ ] Schedule daily notification.
    *   [ ] Handle notification permissions.

### Phase 6: Data Persistence (Room DB Setup & Pre-population)
*   [X] **Room Entities, DAOs, Database Finalized**
*   [X] **AppProgress Initialization**
*   [X] **Chart of Accounts Pre-population:**
    *   [X] Defined standard Chart of Accounts.
    *   [X] Implemented pre-population logic in `AppDatabase.kt`.

### Phase 7: Content - 52-Day Scenario Script
*   [X] **Scenario Data Structure:** Done in Phase 2
*   [ ] **Implement Full Script:**
    *   [X] **Days 1 (Month 1):** Investment (done in `ScenarioRepository`)
    *   [ ] **Rest of Days 1-4 (Month 1):** Rent, Computer Purchase. (Next step)
    *   [ ] **Days 5-13 (Months 2-3):** First clients, Unearned Revenue, Accounts Receivable.
    *   [ ] ... (other days) ...
*   [X] **Question & Answer Content:** (Day 1 questions added)
    *   [ ] Develop at least three multiple-choice questions for each of the 52 scenarios.

### Phase 8: Testing & Refinement
*   [ ] **Unit Tests, UI Tests, Manual Testing, Bug Fixing, etc.**

---
*This plan will be updated as development progresses.*
