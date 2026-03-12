package com.godzuche.rickandmortydirectory.core.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ApplicationScope

val coroutineScopesModule = module {
    single<CoroutineScope>(named<ApplicationScope>()) {
        val defaultDispatcher = get<CoroutineDispatcher>(named(RickAndMortyDispatchers.Default))
        CoroutineScope(SupervisorJob() + defaultDispatcher)
    }
}