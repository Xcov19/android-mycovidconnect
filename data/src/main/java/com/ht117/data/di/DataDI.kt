package com.ht117.data.di

import com.ht117.data.location.LocationLocalDataSource
import com.ht117.data.location.LocationRemoteDataSource
import com.ht117.data.location.LocationRepository
import com.ht117.domain.location.LocationDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

val module = module {
    single { LocationLocalDataSource(get()) }
    single { LocationRemoteDataSource(get()) }
    single<LocationDataSource> { LocationRepository(get(), get()) }
}

fun getDataModules(): List<Module> {
    return listOf(module)
}