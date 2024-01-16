# SpaceX Rocket Launch App

Welcome to the SpaceX Rocket Launch App! This Android application allows users to explore a list of SpaceX rocket launches and view detailed information about each launch. The app is built using Jetpack Compose and follows the MVVM (Model-View-ViewModel) architecture. It integrates Retrofit for API calls, Room for offline storage of launch details, Compose Navigation for navigation within the app, and Hilt for dependency injection.

## Features

- **Rocket Launch List**: View a list of SpaceX rocket launches with essential information.
- **Launch Details**: Tap on a specific launch to see detailed information, including the rocket name, launch date, mission details, and more.
- **Offline Mode**: Launch details are stored offline using Room, allowing users to access information even without an internet connection.
- **Dependency Injection**: Hilt is used for dependency injection, ensuring a clean and modular codebase.
- **Responsive UI**: The app is built using Jetpack Compose, providing a modern and responsive user interface.

## Tech Stack

- **Jetpack Compose**: Modern Android UI toolkit for building native UIs.
- **MVVM Architecture**: Separation of concerns with Model, View, and ViewModel.
- **Retrofit**: HTTP client for making API calls to the SpaceX API.
- **Room**: Local database for caching launch details.
- **Compose Navigation**: Navigation component for handling navigation within the app.
- **Hilt**: Dependency injection library for Android that reduces boilerplate code.


