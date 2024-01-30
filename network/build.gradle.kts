import com.github.amrmsaraya.albums.buildsrc.Configuration

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.github.amrmsaraya.albums.network"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // for the sake of simplicity i added base url as a build config field
        // but in a real world application base url should be added in a native cpp file
        // and used as a native library to make it harder to reverse engineer the app
        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://jsonplaceholder.typicode.com\""
        )
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
        sourceCompatibility = Configuration.sourceCompatibility
        targetCompatibility = Configuration.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = Configuration.jvmTarget
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.adapter.result)
    implementation(libs.okhttp.interceptor)


    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler.dagger.ksp)
}