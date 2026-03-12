package com.godzuche.rickandmortydirectory.app.di

import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEventBus
import org.koin.dsl.module

val navigationModule = module {
    single {
        AppEventBus()
    }
}