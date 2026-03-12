# Rick and Morty Directory - Technical Assessment

A modern Android application built as a technical assessment, demonstrating the implementation of a character directory using the [Rick and Morty API](https://rickandmortyapi.com/).

## 🚀 Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose (Declarative UI)
- **Architecture:** Clean Architecture + MVI (Model-View-Intent)
- **Async:** Kotlin Coroutines & Flow
- **Dependency Injection:** Koin
- **Networking:** Ktor Client
- **Navigation:** Jetpack Navigation 3 (State-based, typed destinations)
- **Testing:** JUnit 4, Kotlinx Coroutines Test, Turbine (Flow testing)

## 🏗️ Architecture & Design Decisions

### Clean Architecture
The project is organized into layers to ensure separation of concerns and testability:
- **Presentation:** Contains Compose UI, ViewModels, and UI state models.
- **Domain:** The "truth" of the app, containing business models and repository interfaces.
- **Data:** Implementation of repository interfaces, remote data sources (Ktor), and mappers.
- **Core:** Shared infrastructure including networking configuration, design system, and utility classes.

### MVI & State Management
I chose **MVI (Model-View-Intent)** combined with **Kotlin Flow** to achieve a Unidirectional Data Flow (UDF). This ensures:
- **Predictability:** The UI state is a single source of truth, making debugging easier.
- **Consistency:** All user actions (Actions) are processed through a central point in the ViewModel.

### Navigation 3
This project utilizes the experimental **Navigation 3** library. 
- **State-based:** Navigation is treated as a piece of state, making it highly predictable.
- **Scene Strategy:** Using the scene-based approach allows the app to easily scale to larger screens (tablets/foldables) by swapping or nesting navigation strategies without rewriting core logic.

### Dependency Injection (Koin)
**Koin** was selected for its simplicity and "Kotlin-first" approach. Its DSL-based configuration allows for quick setup and lightweight dependency management.

### Networking (Ktor)
**Ktor Client** was chosen for its modern, asynchronous-first design and native support for Kotlin Serialization.

## 🛠️ Project Structure

```text
app
 ├─ app (Application setup, Main navigation)
 │   ├─ di (App-wide Koin modules)
 │   ├─ navigation (Main NavHost and Scene logic)
 ├─ features
 │   ├─ characters
 │   │   ├─ api (Navigation keys/interfaces)
 │   │   └─ impl (data, domain, presentation)
 ├─ core
 │   ├─ data (Shared Networking, Safe API calls)
 │   ├─ domain (Common errors, Result types)
 │   ├─ presentation (Common UI messaging)
 │   └─ designsystem (Themes, Typography)
```

## 🧪 Testing
The project includes unit tests for core business logic:
- **ViewModels:** Tested using **Turbine** to verify UI state transitions (Loading → Success/Error).
- **Mappers:** Ensuring data integrity during transformation from API responses to Domain models.
- **Repositories:** Verified using manual **Fakes** for Repositories and DataSources to provide deterministic test environments.

## 📈 Future Enhancements
Given more time, the following features would be implemented:
- **Local Caching:** Implementation of **Room** database to support offline-first capabilities.
- **Paging 3:** Integration with the Paging library for smoother infinite scrolling.

## ⚙️ Build & Installation

### Option 1: Direct APK Download (Quick Test)
For immediate testing without building from source, you can download and install the debug APK here:

[Download APK](https://github.com/Godzuche/RickAndMortyDirectory/releases/download/v1.0.0/rick-morty-character-directory-debug.apk)

Make sure installation from unknown sources is enabled on your Android device.

### Option 2: Build from Source
1.  **Clone the repository.**
2.  **Open in Android Studio** (recent versions recommended).
3.  **Sync Gradle.**
4.  **Run** on an emulator or physical device (API 24+).
