package com.ht117.yukute.di

import com.ht117.data.di.getDataModules
import com.ht117.domain.di.getDomainModules
import org.koin.core.module.Module
import org.koin.dsl.module

val module = module {
}

fun getDI(): List<Module> {
    val domainModules = getDomainModules()
    val dataModules = getDataModules()
    return mutableListOf(module).also {
        it.addAll(domainModules)
        it.addAll(dataModules)
    }
}