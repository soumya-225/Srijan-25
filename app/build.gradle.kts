plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    //id("com.google.gms.google-services")
}

android {
    namespace = "com.iitism.srijan25"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iitism.srijan25"
        minSdk = 30
        targetSdk = 34
        versionCode = 6
        versionName = "1.6"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation (libs.okhttp.v4100)
    implementation(libs.androidx.junit)
    implementation (libs.gson)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.circleimageview)
    implementation(libs.glide)
    implementation(libs.gson)
    implementation(libs.picasso)
    implementation(libs.cronet.embedded)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    implementation(libs.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.security.crypto)

    // play store warning
    implementation(libs.soloader)


    implementation (libs.cloudinary.android)

// Download + Preprocess:
    implementation (libs.cloudinary.android.download)
    implementation (libs.cloudinary.android.preprocess)
    implementation (libs.dotsindicator)


    //Announcements and Notifications
    implementation(libs.firebase.messaging.ktx)
    implementation (libs.androidx.swiperefreshlayout)
    //implementation (libs.firebase.messaging)
    //implementation(platform(libs.firebase.bom))
    implementation(platform(libs.firebase.bom))

}