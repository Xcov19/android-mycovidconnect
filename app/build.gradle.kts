plugins {
    id(Deps.Plugins.Application)
    id(Deps.Plugins.KotlinAndroid)
    id(Deps.Plugins.KotlinExt)
}

android {
    compileSdkVersion(Deps.App.compileSdk)
    buildToolsVersion(Deps.App.buildTools)

    defaultConfig {
        applicationId = "com.ht117.yukute"
        minSdkVersion(Deps.App.minSdk)
        targetSdkVersion(Deps.App.targetSdk)
        versionCode = Deps.App.VersionCode
        versionName = Deps.App.VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(Deps.Common.FileTree))
    implementation(Deps.Androidx.Constraint)
    implementation(Deps.Androidx.NavFragment)
    implementation(Deps.Androidx.NavUiKtx)

    implementation(Deps.Koin.Scope)
    implementation(Deps.Koin.ViewModel)

    implementation(project(":data"))

    testImplementation(Deps.Test.JUnit4)
    androidTestImplementation(Deps.Test.ExtJunit)
    androidTestImplementation(Deps.Test.Espresso)
}
