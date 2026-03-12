package com.godzuche.rickandmortydirectory.app.di

import com.godzuche.rickandmortydirectory.app.MainActivityViewModel
import com.godzuche.rickandmortydirectory.core.data.di.coroutineScopesModule
import com.godzuche.rickandmortydirectory.core.data.di.dataModule
import com.godzuche.rickandmortydirectory.core.data.di.dataStoreModule
import com.godzuche.rickandmortydirectory.core.data.di.databaseModule
import com.godzuche.rickandmortydirectory.core.data.di.dispatchersModule
import com.godzuche.rickandmortydirectory.core.data.di.networkModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(
//        dataModule,
//        dispatchersModule,
//        dataStoreModule,
//        coroutineScopesModule

        dataModule,
        databaseModule,
        dispatchersModule,
        dataStoreModule,
        coroutineScopesModule,
        networkModule,
    )

    viewModelOf(::MainActivityViewModel)
//    viewModelOf(::OnboardingViewModel)
//    viewModelOf(::DashboardViewModel)
//    viewModelOf(::RulesViewModel)
//    viewModelOf(::ActivityViewModel)
//    single { UiEventBus() }
}