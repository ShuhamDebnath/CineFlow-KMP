🎬 CineFlow KMP
===============

**CineFlow KMP** is a modern Movie Discovery App built with **Kotlin Multiplatform (KMP)**, sharing logic and UI between **Android** and **iOS**. It demonstrates industry-standard practices including Clean Architecture, MVVM, and Offline-First data synchronization.

🚀 Tech Stack
-------------

-   **Language:** Kotlin (100%)

-   **UI:** Compose Multiplatform (Material 3)

-   **Architecture:** Clean Architecture (Presentation -> Domain -> Data) + MVVM

-   **Networking:** Ktor 3.0 (Content Negotiation, Logging)

-   **Database:** Room KMP (Offline Caching)

-   **Dependency Injection:** Koin 4.0

-   **Image Loading:** Coil 3.0

-   **Navigation:** Jetbrains Navigation Compose

-   **Asynchronous:** Coroutines & Flow

✨ Features
----------

-   **Discover Movies:** Browse Popular and Top Rated movies.

-   **Search:** Find movies by title.

-   **Offline First:** Caches data locally using Room; app works without internet.

-   **Details:** View movie overview, release date, and rating.

-   **Dark/Light Mode:** Fully supported by Material 3.

🛠️ Setup & Run
---------------

### Prerequisites

-   Android Studio Ladybug (or newer)

-   Xcode 15+ (for iOS)

-   JDK 17 or 21

[TMDB API Key](https://www.themoviedb.org/documentation/api "null")

### Steps

1.  Clone the repo:

    ```
    git clone [https://github.com/ShuhamDebnath/CineFlow-KMP.git](https://github.com/ShuhamDebnath/CineFlow-KMP.git)

    ```

2.  Open `composeApp/src/commonMain/kotlin/com/cineflow/utils/Constants.kt` and add your TMDB API Key.

3.  Sync Gradle project.

4.  **Android:** Select `composeApp` configuration and run on an Emulator.

5.  **iOS:** Open `iosApp/iosApp.xcodeproj` in Xcode or run via KMP plugin in Android Studio.

🏗️ Architecture
----------------

The app follows **Clean Architecture** with strict separation of concerns:

1.  **Data Layer:** API (Ktor), Database (Room), and Repository Implementation.

2.  **Domain Layer:** Use Cases and Repository Interfaces (Pure Kotlin, no android/compose dependencies).

3.  **Presentation Layer:** ViewModels (MVVM) and Composable UI.

📄 License
----------

This project is for educational purposes. Data provided by [TMDB](https://www.themoviedb.org/ "null").