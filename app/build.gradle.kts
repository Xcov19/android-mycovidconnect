import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id(Deps.Plugins.Application)
    id(Deps.Plugins.KotlinAndroid)
    id(Deps.Plugins.KotlinExt)
    id(Deps.Plugins.kapt)
    id(Deps.Plugins.googleServices)
}

android {
    compileSdkVersion(Deps.App.compileSdk)
    buildToolsVersion(Deps.App.buildTools)

    android.buildFeatures.dataBinding = true
    android.buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.ht117.yukute"
        minSdkVersion(Deps.App.minSdk)
        targetSdkVersion(Deps.App.targetSdk)
        versionCode = Deps.App.VersionCode
        versionName = Deps.App.VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resValue("string", "app_name", Deps.App.Name)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "google_maps_key", project.property("mapsRelease") as String?
            )
        }

        getByName("debug") {
            resValue("string", "google_maps_key", project.property("mapsDebug") as String?
            )
        }
    }
}

kapt {
    arguments {
        arg("room.incremental", "true")
    }
}

dependencies {
    implementation(fileTree(Deps.Common.FileTree))
    implementation(Deps.Androidx.Constraint)
    implementation(Deps.Androidx.NavFragment)
    implementation(Deps.Androidx.NavUiKtx)

    implementation(Deps.Koin.Scope)
    implementation(Deps.Koin.ViewModel)
    implementation(Deps.Google.Map)
    implementation(Deps.Common.Ssp)
    implementation(Deps.Google.Location)

    implementation(Deps.Room.Room)
    kapt(Deps.Room.RoomCompiler)
    implementation(Deps.Room.RoomRuntime)

    implementation(Deps.Common.leakCanary)
    implementation(Deps.Common.stetho)
    implementation(Deps.Common.timber)
    implementation(Deps.Common.crashlytics)

    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.ohttp)
    implementation(Deps.Network.loggingInterceptor)
    implementation(Deps.Network.gson)

    implementation(Deps.Firebase.firestore)

    testImplementation(Deps.Test.JUnit4)
    androidTestImplementation(Deps.Test.ExtJunit)
    androidTestImplementation(Deps.Test.Espresso)
}