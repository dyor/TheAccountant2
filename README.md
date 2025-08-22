# AAA Software - Android Accounting Learning App

AAA Software is a native Android application, built with Kotlin and Jetpack Compose, designed as an educational game to teach the fundamentals of bookkeeping and accounting. The app simulates managing the books for a fictional software consulting firm over a one-year period (represented as 52 in-app days).

## Core Concept

The primary learning mechanic is story-driven. Users are presented with daily business scenarios and must make correct accounting journal entries. The app is entirely for local use, with no backend or cloud services.

## Key Features

*   **52-Day Cycle:** Each real-world day can represent one week of business operations.
*   **Daily Scenarios:** Users receive a new business event narrative each day.
*   **Interactive Multiple-Choice Questions:** Before making a journal entry, users answer questions to reinforce accounting principles.
*   **Journal Entry Creation:** Users create journal entries (Debits/Credits) that update account balances.
*   **Monthly Book-Closing:** Every four in-app days, users "close the books" and review key financial statements (Income Statement, Balance Sheet).
*   **Daily Notifications:** A simple reminder to engage with the app.

## Technical Stack

*   **Language:** Kotlin
*   **UI Toolkit:** Jetpack Compose
*   **Architecture:** Model-View-ViewModel (MVVM)
*   **Database:** Room Persistence Library
*   **Asynchronous Programming:** Kotlin Coroutines and StateFlow
*   **Notifications:** Android `NotificationManager`

## Development Status

Refer to `PLAN.md` for a detailed development plan and current progress.
