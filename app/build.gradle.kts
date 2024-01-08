import com.android.build.gradle.tasks.AidlCompile

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.aidlapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aidlapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    flavorDimensions.addAll(listOf("dimension1","dimension2"))
    productFlavors {
        register("one") {
            dimension = "dimension1"
        }
        register("two") {
            dimension = "dimension1"
        }
        register("eleven") {
            dimension = "dimension2"
        }
        register("twelve") {
            dimension = "dimension2"
        }
    }
    buildFeatures {
        aidl = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    sourceSets {
        register("oneEleven") {
            aidl.srcDirs(
                "src/one/aidl",
                "src/eleven/aidl",
                "src/oneEleven/aidl",
            )
        }
        register("twoTwelve") {
            aidl.srcDirs(
                "src/two/aidl",
                "src/twelve/aidl",
                "src/twoTwelve/aidl",
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

tasks.register("runAidl") {
    dependsOn("assembleOneRelease","assembleTwoRelease")
}