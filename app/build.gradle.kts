plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.expensetracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.expensetracker"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.coroutines.core.v1102)
    implementation(libs.kotlinx.coroutines.android.v163)
    implementation (libs.androidx.lifecycle.livedata.ktx) // Or the latest stable version
    implementation (libs.androidx.lifecycle.viewmodel.ktx) // For ViewModel integration
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2") // For lifecycle scope

    //for datastore
    implementation("androidx.datastore:datastore:1.1.7")
    implementation ("androidx.datastore:datastore-preferences:1.1.7")

//    implementation(libs.material)
}