plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("androidx.navigation.safeargs") version "2.8.5" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}