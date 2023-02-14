plugins {
    id("com.android.application")
    id("kotlin-android")
}

dependencies {


    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation(project(":locationkit"))

}

android {

    compileSdk = 31

    buildToolsVersion = "31.0.0"
    defaultConfig {
        applicationId = "idroid.android.samplelocationkit"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
