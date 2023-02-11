plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}


android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()

        }
    }

    defaultConfig {
        minSdk = 21

        //testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles 'consumer-rules.pro'
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


dependencies {

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.huawei.hms:location:6.9.0.300")

}


configure<PublishingExtension> {
    publications.create<MavenPublication>("androidSdk") {
        groupId = "com.github.waterboys"
        artifactId = "LocationKit"
        version = "1.1.0"
        pom.packaging = "aar"
        artifact("$buildDir/outputs/aar/locationkit-release.aar")
    }
    repositories {
        mavenLocal()
    }
}

