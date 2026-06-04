# FitQuest: Shadow Rising
## Solo Leveling-inspired Fitness RPG for Android

A fully free, gamified fitness and productivity tracker with no subscriptions or paywalls.

---

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 34
- Min device: Android 8.0 (API 26)

### Build & Run
```bash
# Clone/open the project in Android Studio
# Sync Gradle → Build → Run on emulator or device

# Or via command line with Android CLI:
android create empty-activity --name="FitQuest RPG" --output=./
android run
```

### Via Gradle directly
```bash
cd /Users/soumensen/Documents/Fitness_App
./gradlew assembleDebug
./gradlew installDebug   # with device connected
./gradlew test           # run unit tests
```

---

## Project Structure

```
FitQuestRPG/
├── app/src/main/java/com/fitquest/rpg/
│   ├── FitQuestApp.kt              # Hilt application class
│   ├── MainActivity.kt             # Entry point + nav graph
│   ├── core/
│   │   ├── data/
│   │   │   ├── local/              # Room DB, DAOs, Entities
│   │   │   ├── remote/             # Wger API service
│   │   │   └── repository/         # Repository implementations
│   │   ├── domain/
│   │   │   └── model/              # XpAlgorithm, Attribute, UserProfile, Task
│   │   └── di/                     # Hilt modules
│   ├── features/
│   │   ├── onboarding/             # 5-step questionnaire
│   │   ├── dashboard/              # Main RPG dashboard
│   │   ├── attributes/             # XP & attribute detail screen
│   │   └── store/                  # Action Points reward store
│   └── ui/
│       ├── theme/                  # Dark RPG Material 3 theme
│       └── components/             # Shared composables
└── app/src/main/assets/
    ├── exercises.json              # 15 bundled exercises (offline fallback)
    └── diet_advice.json            # Diet plans by phase & diet style
```

---

## Key Algorithms

### XP Curve
```kotlin
xpRequired(level) = 100 × level × ln(level + 1) × 1.5
```
- Logarithmic slow-burn curve
- Level 10 → ~3 weeks | Level 50 → ~6 months | Level 100 → ~20 months

### Progressive Overload (4-Week Cycle)
| Week | Effect |
|------|--------|
| 1 | Base volume |
| 2 | +5% reps |
| 3 | +1 set |
| 4 | Deload (–20%) |
Each month, base volume increments automatically.

---

## API Integration

**Wger Workout Manager API** — Free, no key required
- Base URL: `https://wger.de/api/v2/`
- Used for: exercise database by muscle group
- Fallback: `assets/exercises.json` (fully offline)

---

## Attribute System

| Attribute | Driven By |
|-----------|-----------|
| 💪 Strength | Resistance exercises |
| 🤸 Flexibility | Stretching, yoga |
| 🏃 Stamina | Cardio, running |
| ⚡ Energy | Sleep, hydration habits |
| 🧠 Intelligence | Reading, coding, meditation |

---

## Economy

- **Action Points (AP)** earned per completed task (5–50 AP/task)
- **7-day streak bonus**: +100 AP
- **Spend AP** in the Reward Store on predefined or custom reward cards

---

## Running Tests

```bash
./gradlew test
```

Tests cover:
- XP curve validation (levels 1–100)
- Progression timing (early fast, end-game slow)
- Progressive overload deload weeks
- Volume growth over months
