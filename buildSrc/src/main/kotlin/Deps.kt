object Deps {
    object Plugins {
        const val BuildGradle = "com.android.tools.build:gradle:${Version.buildGradle}"
        const val BuildKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kt}"
        const val Application = "com.android.application"
        const val Library = "com.android.library"
        const val KotlinAndroid = "kotlin-android"
        const val KotlinExt = "kotlin-android-extensions"
        const val KotlinKapt = "kotlin-kapt"
        const val JavaLib = "java-library"
        const val Kotlin = "kotlin"
    }

    object App {
        const val Id = "com.social.xteam"
        const val minSdk = 23
        const val compileSdk = 29
        const val targetSdk = 29
        const val buildTools = "29.0.3"
        const val VersionCode = 1
        const val VersionName = "1.0"
        const val Name = "XCovid"
    }

    object Kt {
        const val StdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kt}"
        const val StdJdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kt}"
        const val Serialize = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Version.Serialize}"
    }

    object Androidx {
        const val CoreKtx = "androidx.core:core-ktx:${Version.KtxCore}"
        const val AppCompat = "androidx.appcompat:appcompat:${Version.AppCompat}"
        const val Constraint = "androidx.constraintlayout:constraintlayout:${Version.Constraint}"
        const val NavFragment = "androidx.navigation:navigation-fragment-ktx:${Version.NavFragment}"
        const val NavUiKtx = "androidx.navigation:navigation-ui-ktx:${Version.NavUiKtx}"

        const val RoomRuntime = "androidx.room:room-runtime:${Version.Room}"
        const val RoomCompiler = "androidx.room:room-compiler:${Version.Room}"
        const val RoomKt = "androidx.room:room-ktx:${Version.Room}"

        const val Prefs = "androidx.preference:preference-ktx:${Version.Prefs}"
    }

    object Google {
        const val DebugKey = "AIzaSyDdC8LQejIZ1YCac4ow1QrP7bmDTqcfm_8"
        const val ReleaseKey = "google_maps_key"
        const val Map = "com.google.android.gms:play-services-maps:17.0.0" // v2
    }

    object Firebase {
        const val FireStore = "com.google.firebase:firebase-firestore-ktx:21.5.0"
    }

    object Common {
        val FileTree = mapOf("dir" to "libs", "include" to listOf("*.jar"))
        const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.LeakCanary}"
        const val Timber = "com.jakewharton.timber:timber:${Version.Timber}"
        const val Ssp = "com.intuit.ssp:ssp-android:1.0.6"
    }

    object Koin {
        const val Core = "org.koin:koin-core:${Version.Koin}"
        const val Scope = "org.koin:koin-androidx-scope:${Version.Koin}"
        const val ViewModel = "org.koin:koin-androidx-viewmodel:${Version.Koin}"
    }

    object Network {
        const val Retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
        const val Moshi = "com.squareup.moshi:moshi:${Version.Moshi}"
        const val MoshiGen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.Moshi}"
        const val Logger = "com.squareup.okhttp3:logging-interceptor:${Version.Interceptor}"
        const val Converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.5.0"
    }

    object Test {
        const val JUnit4 = "junit:junit:${Version.Junit}"
        const val ExtJunit = "androidx.test.ext:junit:${Version.ExtJunit}"
        const val Espresso = "androidx.test.espresso:espresso-core:${Version.Espresso}"
        const val Koin = "org.koin:koin-test:${Version.Koin}"
    }
}