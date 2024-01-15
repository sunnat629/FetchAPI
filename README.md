# Android Jetpack Compose App with Retrofit

## Overview
This repository contains a simple Android application developed using Jetpack Compose. The app demonstrates how to fetch and display data from a REST API using Retrofit, handle states with Kotlin Coroutines, and display images from URLs using the Coil library.

## Features
- **Jetpack Compose UI**: Modern UI design with the latest Compose toolkit.
- **Retrofit for Network Calls**: Utilizes Retrofit for making API calls.
- **Kotlin Coroutines**: Leverages Coroutines for asynchronous operations and state management.
- **Coil for Image Loading**: Uses Coil to load and display images from the web.
- **State Handling**: Implements state handling using mutable states in ViewModels.
- **Error Handling**: Robust error handling during network operations.

## Libraries Used
- `androidx.*`: Core AndroidX libraries and Compose UI toolkit.
- `com.squareup.retrofit2:retrofit`: For network operations.
- `com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter`: Converter for serialization with Retrofit.
- `com.squareup.okhttp3:logging-interceptor`: For logging network requests.
- `org.jetbrains.kotlinx:kotlinx-coroutines-android`: For managing background tasks with Kotlin Coroutines.
- `io.coil-kt:coil-compose`: For image loading in Compose.
- `com.github.skydoves:retrofit-adapters-*`: Custom adapters for Retrofit responses.

## Architecture

This application is built using the **Model-View-ViewModel (MVVM)** architecture, integrated with modern Android development tools and practices. The architecture is designed to provide a clear separation of concerns, promote a scalable and maintainable codebase, and enhance the developer and user experience.

### Key Components

- **ViewModel (`MainViewModel`)**: 
  - Manages the UI-related data and state in a lifecycle-aware manner.
  - Handles business logic, such as fetching data from the repository.
  - Communicates with the View through observable state (`mutableStateOf`).

- **View (`MainActivity`, `UserContent` Composable)**:
  - Responsible for rendering the UI and reacting to state changes.
  - Interacts with the ViewModel to send user actions and display data.
  - Built using Jetpack Compose, a modern, declarative UI toolkit.

- **Model (`GitHubUser`)**:
  - Represents the data structure for user profiles fetched from the API.

- **Repository (`GitHubRepository`, `GitHubRepositoryImpl`)**:
  - Abstracts the data source from the ViewModel.
  - Handles data fetching operations, providing a clean API to the ViewModel.
  - Uses Retrofit for efficient network communication.

- **Network Layer (`RetrofitBuilder`, `GitHubService`)**:
  - Configures Retrofit for network operations.
  - Defines the API endpoints.

### Design Patterns and Practices

- **Repository Pattern**: 
  - Encapsulates the logic for data retrieval and provides a clean separation between the data layer and the ViewModel.
  
- **State Management**:
  - Uses `mutableStateOf` for reactive UI updates, ensuring the UI is consistent with the app's state.

- **Error and Loading State Management**:
  - Manages loading and error states to enhance user experience.

- **Kotlin Coroutines**:
  - Handles asynchronous operations, providing a more readable and efficient way to manage background tasks.

### Best Practices

- **Lifecycle Awareness**: ViewModel's lifecycle-aware nature prevents memory leaks and efficiently manages UI-related data.
- **Testing**: Each component is designed to be easily testable, promoting reliable and maintainable code.
- **Dependency Injection**: (If used) Utilizes Dagger-Hilt for managing dependencies, enhancing scalability and maintainability.
- **Modularization**: (If applicable) The project structure supports modularization, making it easier to manage and scale the application.

## Setup
To run this project:
1. Clone this repository.
2. Open the project in Android Studio.
3. Run the project using an emulator or a physical device.

## Usage
The main functionality of the app is to fetch user data from a given API endpoint. The app uses Retrofit to make network requests, Coroutines for asynchronous operations, and Coil to load images.

1. Enter a username to fetch data for.
2. Press the "Fetch User Data" button.
3. View the displayed user information and image.

## Contribution
Contributions are welcome. Please feel free to submit a pull request or open issues to improve the app or suggest enhancements.
