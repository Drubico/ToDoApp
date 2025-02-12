// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false

    // injeccion de dependencias
    // url: https://developer.android.com/training/dependency-injection/hilt-android
    // url version: https://mvnrepository.com/artifact/com.google.dagger/hilt-android
    id ("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" // Update to a compatible version
}