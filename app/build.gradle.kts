plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "com.fikrisandi.loginapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fikrisandi.loginapp"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

object Version {
    const val coreKtx = "1.12.0"
    const val appCompat = "1.6.1"
    const val material = "1.10.0"
    const val constraint = "2.1.4"
    const val navigation = "2.7.4"
    const val coroutine = "1.7.3"
    const val coroutineLifecycle = "2.6.2"
    const val ktor = "2.3.5"
    const val datastore = "1.0.0"
    const val paging3 = "3.2.1"
    const val coil = "2.4.0"
    const val junit = "4.13.2"
    const val androidJUnit = "1.1.5"
    const val espresso = "3.5.1"
}

dependencies {


    //core
    implementation("androidx.core:core-ktx:${Version.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Version.appCompat}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //ui
    implementation("com.google.android.material:material:${Version.material}")

    //constraint
    implementation("androidx.constraintlayout:constraintlayout:${Version.constraint}")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.coroutineLifecycle}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.coroutineLifecycle}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Version.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Version.navigation}")

    //paging
    implementation("androidx.paging:paging-runtime-ktx:${Version.paging3}")

    //datastore
    implementation("androidx.datastore:datastore-preferences:${Version.datastore}")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    //ktor
    implementation("io.ktor:ktor-client-core:${Version.ktor}")
    implementation("io.ktor:ktor-client-okhttp:${Version.ktor}")
    implementation("io.ktor:ktor-client-content-negotiation:${Version.ktor}")
    implementation("io.ktor:ktor-serialization-gson:${Version.ktor}")
    implementation("io.ktor:ktor-client-auth:${Version.ktor}")
    implementation("io.ktor:ktor-client-logging:${Version.ktor}")

    //coil
    implementation("io.coil-kt:coil:${Version.coil}")

    //test
    testImplementation("junit:junit:${Version.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Version.androidJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.espresso}")
}