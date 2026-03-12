package com.godzuche.rickandmortydirectory.core.data.di

import androidx.datastore.core.DataStore
import com.godzuche.rickandmortydirectory.core.data.remote.util.createHttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientEngine> { CIO.create() }

    single {
        createHttpClient(
            engine = get(),
        )
    }
}