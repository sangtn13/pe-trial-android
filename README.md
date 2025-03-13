# Real Estate Land Management App

Overview

This is a Real Estate Land Management Application developed using Android (Kotlin, Jetpack Compose) with SQLiteDatabase. The app allows users to store, edit, view, and delete land records, as well as navigate to land locations using Google Maps.

Features

Land List Screen

Displays all saved land properties.

Shows essential details such as name, price, and address.

Supports searching by land name or address.

Clicking on an item navigates to the Land Detail Screen.

Land Detail Screen

Displays full details of the selected land.

Shows the location on the map based on stored GPS coordinates.

Provides options to Edit or Delete the land record.

Add/Edit Land Screen

Allows users to enter or modify land details:

Name

Price

Address

GPS Coordinates (entered manually or selected from a map)

Additional details

Includes a Save button.

Map Integration

Users can select a location by:

Entering GPS coordinates manually.

Choosing a location on an interactive map.

The Land Detail Screen displays land locations on Google Maps.

Delete Land Record

Users can delete a land record from the Detail Screen.

A confirmation prompt is displayed before deletion.

Technologies Used

Kotlin

Jetpack Compose

SQLiteDatabase

Google Maps API

Navigation Component

ViewModel (MVVM Architecture)

LiveData & Coroutine

Installation Guide

Prerequisites

Install Android Studio (Latest Version).

Set up an Android Emulator or connect a real Android device.

Steps to Run the Project

Clone this repository:

git clone [https://github.com/SangTran13/pe-trial-android.git]

Open the project in Android Studio.

Sync Gradle and install dependencies.

Obtain a Google Maps API Key from Google Cloud Console.

Add your API key to AndroidManifest.xml:

<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY_HERE" />

Run the project on an emulator or a real device.

Project Structure

├── app
│   ├── src/main/java/com/qe170193/
│   │   ├── data/
│   │   │   ├── db/LandDatabase.kt
│   │   │   ├── db/LandDao.kt
│   │   │   ├── model/Land.kt
│   │   ├── repository/LandRepository.kt
│   │   ├── ui/
│   │   │   ├── view/LandListScreen.kt
│   │   │   ├── view/LandDetailScreen.kt
│   │   │   ├── view/AddEditLandScreen.kt
│   │   │   ├── view/MapPickerScreen.kt
│   │   │   ├── viewmodel/LandViewModel.kt
│   │   ├── LandNavHost.kt
│   │   ├── MainActivity.kt
│   ├── AndroidManifest.xml
│   ├── build.gradle.kts

Notes

Ensure you have Google Play Services installed if running on a physical device.

The app uses SQLite for offline storage, meaning all land records are saved locally on the device.

The Google Maps API key is required for displaying maps.

Author

Sang Tran Ngoc

Contact: tranngocsang147877@gmail.com

GitHub: [https://github.com/sangtran13]

License

This project is licensed under the MIT License - see the LICENSE file for details.
