# 📱 MeetMax


## Features
- User authentication with Google
- MVVM architecture
- Jetpack Compose UI

## Tech Stack
- Kotlin
- Jetpack Compose
- Hilt (DI)
- Retrofit
- Room

## Screenshots
DOWNLOAD DEBUG APK FROM HERE: 
- https://drive.google.com/file/d/1enz5gvHVhKygjDyZsXo_Qkm6CIvwe0tP/view?usp=sharing

## ⚡ Setup
1. Clone the repository
   ```bash
   git clone https://github.com/MizanurRemon/MeetMax
   
2. Replace google-services.json file generated from firebase
3. Create a web client id from Google Cloud and replace WEB_CLIENT_ID. 
To find follow this- 
app -> core -> common -> src -> main -> java -> util -> Constants.kt
4. Now go to terminal, run './gradlew signingReport', now app is ready for google authentication
4. Now build the app.

