package com.github.amrmsaraya.albums.buildsrc

import org.gradle.api.JavaVersion

object Configuration {
    const val applicationId = "com.github.amrmsaraya.albums"
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 24
    private const val majorVersion = 1
    private const val minorVersion = 0
    private const val patchVersion = 0
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
    const val versionCode = 1
    const val jvmTarget = "17"
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
}