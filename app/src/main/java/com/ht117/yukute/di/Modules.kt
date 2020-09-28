package com.ht117.yukute.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.GsonBuilder
import com.ht117.yukute.BuildConfig
import com.ht117.yukute.data.LocationDataSource
import com.ht117.yukute.data.local.Database
import com.ht117.yukute.data.local.LocationLocalDataSource
import com.ht117.yukute.data.remote.LocationRemoteDataSource
import com.ht117.yukute.data.remote.api.YakuteAPI
import com.ht117.yukute.data.remote.interceptor.AuthInterceptor
import com.ht117.yukute.repository.LocationRepository
import com.ht117.yukute.repository.UserRepository
import com.ht117.yukute.settings.Settings
import com.ht117.yukute.ui.screen.landing.LandingViewModel
import com.ht117.yukute.ui.screen.login.LoginViewModel
import com.ht117.yukute.ui.screen.map.MapViewModel
import com.ht117.yukute.ui.viewmodel.UserViewModel
import com.ht117.yukute.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private val appModule: Module = module {
    single { getSharedPrefs(androidApplication()) }
    single { getFusedLocationProvider(androidApplication()) }

}

private fun getSharedPrefs(application: Application): SharedPreferences =
    application.getSharedPreferences("xcov", Context.MODE_PRIVATE)

private fun getFusedLocationProvider(application: Application): FusedLocationProviderClient =
    LocationServices.getFusedLocationProviderClient(application)

private val networkingModule: Module = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = when (BuildConfig.BUILD_TYPE) {
            "release" -> HttpLoggingInterceptor.Level.NONE
            else -> HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(AuthInterceptor(get()))
            .build()
    }

    single {

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }
}

val apiModule: Module = module {
    single<YakuteAPI> { get<Retrofit>().create() }
}
val settingsModule: Module = module {

    single {
        androidContext().getSharedPreferences(
            Settings.YAKUTE_SETTINGS_NAME,
            Context.MODE_PRIVATE
        )
    }

    single {
        Settings(get())
    }
}

private val databaseModule: Module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "yakute-db"
        ).build()
    }
}

private val daoModule: Module = module {
    single { get<Database>().userDao() }
}

private val dataModule: Module = module {
    single { LocationLocalDataSource(get()) }
    single { LocationRemoteDataSource(get()) }
    single<LocationDataSource> { LocationRepository(get(), get()) }
}

private val repositoryModule: Module = module {
    single { UserRepository(get()) }
    single { LocationRepository(get(), get()) }
}

private val viewModelModule: Module = module {
    viewModel { UserViewModel(get()) }
    viewModel { LandingViewModel() }
    viewModel { MapViewModel(get()) }
}

val appModules: List<Module> = listOf(
    appModule,
    dataModule,
    networkingModule,
    apiModule,
    databaseModule,
    daoModule,
    repositoryModule,
    viewModelModule
)