import com.github.amrmsaraya.albums.buildsrc.Configuration

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.github.amrmsaraya.albums.data"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":network"))
    implementation(project(":domain"))

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Retrofit
    implementation(libs.retrofit)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler.dagger.ksp)
}