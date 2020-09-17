package com.ht117.yukute.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ht117.data.di.getDataModules
import com.ht117.domain.di.getDomainModules
import com.ht117.yukute.ui.screen.home.HomeViewModel
import com.ht117.yukute.ui.screen.landing.LandingViewModel
import com.ht117.yukute.ui.screen.login.LoginViewModel
import com.ht117.yukute.ui.screen.map.MapViewModel
import com.ht117.yukute.ui.screen.signup.SignupViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {
    single {
        getSharedPrefs(androidApplication())
    }
    single {
        getFusedLocationProvider(androidApplication())
    }
}

val module = module {
    viewModel { HomeViewModel() }
    viewModel { LandingViewModel() }
    viewModel { LoginViewModel() }
    viewModel { MapViewModel(get()) }
    viewModel { SignupViewModel() }
}

fun getDI(): List<Module> {
    val domainModules = getDomainModules()
    val dataModules = getDataModules()
    return mutableListOf(appModule, module).also {
        it.addAll(domainModules)
        it.addAll(dataModules)
    }
}

fun getSharedPrefs(application: Application): SharedPreferences =
    application.getSharedPreferences("xcov", Context.MODE_PRIVATE)

fun getFusedLocationProvider(application: Application): FusedLocationProviderClient =
    LocationServices.getFusedLocationProviderClient(application)