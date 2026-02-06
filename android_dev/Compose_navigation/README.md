# Compose Navigation - Social Links App

A Jetpack Compose Android application featuring social media links and an About Me screen.

## Features

- 🔗 4 buttons for connecting to social media:
  - **Instagram**: Opens Instagram app or falls back to web browser
  - **LinkedIn**: Opens LinkedIn app or falls back to web browser
  - **GitHub**: Opens GitHub profile in browser
  - **About Me**: Navigates to an information screen about you

- 📱 Native app integration for Instagram and LinkedIn
- 🎨 Material Design 3 (Material You) theming
- 🧭 Navigation using Jetpack Navigation Compose

## How to Customize

### 1. Update Social Media Links

Edit `app/src/main/java/com/example/compose_navigation/HomeScreen.kt`:

**Instagram** (line 36):
```kotlin
Uri.parse("instagram://user?username=YOUR_INSTAGRAM_USERNAME")
// and line 43:
Uri.parse("https://instagram.com/YOUR_INSTAGRAM_USERNAME")
```
Replace `YOUR_INSTAGRAM_USERNAME` with your actual Instagram username.

**LinkedIn** (line 60):
```kotlin
Uri.parse("linkedin://profile/YOUR_LINKEDIN_ID")
// and line 67:
Uri.parse("https://linkedin.com/in/YOUR_LINKEDIN_ID")
```
Replace `YOUR_LINKEDIN_ID` with your LinkedIn profile ID (the part after `/in/` in your LinkedIn URL).

**GitHub** (line 84):
```kotlin
Uri.parse("https://github.com/YOUR_GITHUB_USERNAME")
```
Replace `YOUR_GITHUB_USERNAME` with your GitHub username.

### 2. Update About Me Information

Edit `app/src/main/java/com/example/compose_navigation/AboutMeScreen.kt`:

- **Name** (line 48): Replace "Your Name" with your actual name
- **Bio** (line 60-63): Update the bio text with your own description
- **Skills** (line 73-79): Add or modify your skills
- **Education** (line 90-99): Update with your college/university and program details
- **Interests** (line 112): Add your personal interests

## Project Structure

```
app/src/main/java/com/example/compose_navigation/
├── MainActivity.kt       # Main activity and navigation setup
├── Screen.kt            # Navigation routes sealed class
├── HomeScreen.kt        # Home screen with social media buttons
└── AboutMeScreen.kt     # About Me information screen
```

## Dependencies

- Jetpack Compose with Material Design 3
- Navigation Compose for screen navigation
- Activity Compose for integration with ComponentActivity

## Building the Project

1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Run the app on an emulator or physical device

## Technical Details

### Navigation

The app uses Jetpack Navigation Compose with two screens:
- `home`: HomeScreen (start destination)
- `about_me`: AboutMeScreen

### Deep Linking

The app includes `<queries>` in AndroidManifest.xml to enable deep linking to:
- Instagram app (`com.instagram.android`)
- LinkedIn app (`com.linkedin.android`)

This allows the buttons to open the native apps if installed, or fall back to the web browser.

## Requirements

- Android Studio Hedgehog or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 36
- Kotlin 2.0.21
- Gradle 9.0.0

## License

This is an educational project for Android Development coursework.

---

**Note**: Remember to replace all placeholder values (YOUR_INSTAGRAM_USERNAME, YOUR_LINKEDIN_ID, YOUR_GITHUB_USERNAME, etc.) with your actual information before running the app!
