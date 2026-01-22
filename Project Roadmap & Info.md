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

This project is for educational purposes. Data provided by [TMDB](https://www.themoviedb.org/ "null").📽️ CineFlow KMP - Project Documentation
========================================

1\. Architecture Overview
-------------------------

We are using **Clean Architecture** combined with the **MVVM** pattern and **Offline-First** repository strategy.

### Layers

1.  **Presentation Layer (UI)**

    -   **Components:** Compose Multiplatform (Screens, Components).

    -   **State Management:** ViewModels holding `StateFlow` to drive the UI.

    -   **Responsibility:** Rendering data and handling user interactions.

2.  **Domain Layer (Business Logic)**

    -   **Components:** Use Cases (Interactors), Models (Business Objects), Repository Interfaces.

    -   **Responsibility:** Pure business logic. Independent of frameworks (no SQL, no HTTP).

3.  **Data Layer (Data Source)**

    -   **Components:** Ktor Services (Remote), Room DAO (Local), Repository Implementation, DTO Mappers.

    -   **Responsibility:** Fetching data from the network, caching it in the database, and providing a "Single Source of Truth" to the Domain layer.

### Offline-First Strategy

1.  UI requests data from the **Repository**.

2.  Repository **immediately** emits data from the **Local Database (Room)** (even if empty).

3.  Repository triggers a **Network Call (Ktor)** in the background.

4.  If network succeeds, data is saved to the Database.

5.  Room observes the database change and emits the **new data** to the UI automatically.

2\. Directory Structure (commonMain)
------------------------------------

```
com.cineflow
├── data
│   ├── local         # Room Database, DAOs, Entities
│   ├── remote        # Ktor Service, DTOs
│   ├── repository    # Repository Implementation
│   └── mappers       # DTO -> Domain mappers
├── domain
│   ├── model         # Pure Kotlin Models
│   ├── repository    # Interfaces
│   └── usecase       # Business Logic units
├── di                # Koin Modules
├── presentation
│   ├── screens       # Composable Screens (Home, Detail)
│   ├── components    # Reusable UI widgets
│   └── navigation    # NavGraph
└── utils             # Constants, NetworkResult, etc.

```

3\. Implementation Roadmap
--------------------------

### ✅ Step 1: Network Layer (Completed)

-   [x] Set up `libs.versions.toml`.

-   [x] Configure `Ktor 3.0` Client with Serialization.

-   [x] Create TMDB API DTOs (`MovieDto`).

-   [x] Create `TmdbApiService` interface and implementation.

-   [x] Set up Koin `networkModule`.

### 🔄 Step 2: Database & Offline Repository (Next)

-   [ ] Define Room Entities (`MovieEntity`).

-   [ ] Create Room DAO (`MovieDao`).

-   [ ] Configure Room Database (`AppDatabase`).

-   [ ] Create Domain Models (`Movie`).

-   [ ] Implement `MovieRepository` with caching logic (Local + Remote).

-   [ ] Create `databaseModule` and `repositoryModule` for Koin.

### 🔜 Step 3: Domain Logic (Use Cases)

-   [ ] Create `GetPopularMoviesUseCase`.

-   [ ] Create `SearchMoviesUseCase`.

-   [ ] Create `GetMovieDetailsUseCase`.

### 🔜 Step 4: Presentation - ViewModels

-   [ ] Create `HomeViewModel` (handles loading states, pagination).

-   [ ] Create `DetailViewModel`.

-   [ ] Set up Koin `viewModelModule`.

### 🔜 Step 5: Presentation - UI (Compose)

-   [ ] Build `MovieItem` component (Card with image).

-   [ ] Build `HomeScreen` with Grid layout.

-   [ ] Build `DetailScreen` with Backdrop and info.

-   [ ] Implement Navigation (Type-safe navigation).

### 🔜 Step 6: Polish & iOS Testing

-   [ ] Add Shimmer Loading effects.

-   [ ] Test on iOS Simulator.

-   [ ] Final bug fixes.

4\. Key Libraries
-----------------

-   **Ktor:** `io.ktor:ktor-client-core`

-   **Room:** `androidx.room:room-runtime` (KMP version)

-   **Koin:** `io.insert-koin:koin-compose`

-   **Coil:** `io.coil-kt.coil3:coil-compose`

-   **Navigation:** `org.jetbrains.androidx.navigation:navigation-compose`