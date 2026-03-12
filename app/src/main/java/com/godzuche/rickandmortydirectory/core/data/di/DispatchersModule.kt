package com.godzuche.rickandmortydirectory.core.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {

    single<CoroutineDispatcher>(named(RickAndMortyDispatchers.IO)) {
        Dispatchers.IO
    }

    single<CoroutineDispatcher>(named(RickAndMortyDispatchers.Default)) {
        Dispatchers.Default
    }
}

enum class RickAndMortyDispatchers {
    Default,
    IO,
}