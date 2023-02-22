plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}


android {
    compileSdk = 31
    buildToolsVersion = "31.0.0"

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
    implementation("com.google.android.gms:play-services-location:20.0.0")
    implementation("com.huawei.hms:location:6.9.0.300")
}


configure<PublishingExtension> {
    afterEvaluate {
        publications.create<MavenPublication>("maven") {
            from(components["release"])
            groupId = "com.github.waterboys"
            artifactId = "LocationKit"
            version = "1.1.2"
            pom.packaging = "aar"
            //artifact("$buildDir/outputs/aar/locationkit-release.aar")
        }
        repositories {
            mavenLocal()
        }
    }
}
