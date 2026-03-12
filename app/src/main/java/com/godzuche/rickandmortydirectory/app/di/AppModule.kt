package com.godzuche.rickandmortydirectory.app.di

import com.godzuche.rickandmortydirectory.app.MainActivityViewModel
import com.godzuche.rickandmortydirectory.core.data.di.coroutineScopesModule
import com.godzuche.rickandmortydirectory.core.data.di.dataModule
import com.godzuche.rickandmortydirectory.core.data.di.dataStoreModule
import com.godzuche.rickandmortydirectory.core.data.di.dispatchersModule
import com.godzuche.rickandmortydirectory.core.data.di.networkModule
import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEventBus
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.CharacterListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        dispatchersModule,
        dataStoreModule,
        coroutineScopesModule,
        networkModule,
    )

    single {
        AppEventBus()
    }

    viewModelOf(::MainActivityViewModel)
    viewModelOf(::CharacterListViewModel)
}