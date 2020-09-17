plugins {
    id(Deps.Plugins.Application)
    id(Deps.Plugins.KotlinAndroid)
    id(Deps.Plugins.KotlinExt)
}

android {
    compileSdkVersion(Deps.App.compileSdk)
    buildToolsVersion(Deps.App.buildTools)

    defaultConfig {
        applicationId = Deps.App.Id
        minSdkVersion(Deps.App.minSdk)
        targetSdkVersion(Deps.App.targetSdk)
        versionCode = Deps.App.VersionCode
        versionName = Deps.App.VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resValue("string", "app_name", Deps.App.Name)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            resValue("string", "google_maps_key", Deps.Google.ReleaseKey)
        }

        getByName("debug") {
            resValue("string", "google_maps_key", Deps.Google.DebugKey)
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(Deps.Google.Location)
    implementation(Deps.Common.Ssp)

    implementation(project(":data"))
    debugImplementation(Deps.Common.LeakCanary)

    testImplementation(Deps.Test.JUnit4)
    androidTestImplementation(Deps.Test.ExtJunit)
    androidTestImplementation(Deps.Test.Espresso)
}
