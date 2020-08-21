package com.ht117.yukute.di

import com.ht117.data.di.getDataModules
import com.ht117.domain.di.getDomainModules
import com.ht117.yukute.ui.screen.home.HomeViewModel
import com.ht117.yukute.ui.screen.landing.LandingViewModel
import com.ht117.yukute.ui.screen.login.LoginViewModel
import com.ht117.yukute.ui.screen.map.MapViewModel
import com.ht117.yukute.ui.screen.signup.SignupFragment
import com.ht117.yukute.ui.screen.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val module = module {
    viewModel { HomeViewModel() }
    viewModel { LandingViewModel() }
    viewModel { LoginViewModel() }
    viewModel { MapViewModel() }
    viewModel { SignupViewModel() }
}

fun getDI(): List<Module> {
    val domainModules = getDomainModules()
    val dataModules = getDataModules()
    return mutableListOf(module).also {
        it.addAll(domainModules)
        it.addAll(dataModules)
    }
}