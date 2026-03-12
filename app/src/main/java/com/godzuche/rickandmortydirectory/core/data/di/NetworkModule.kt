package com.godzuche.rickandmortydirectory.core.data.di

import com.godzuche.rickandmortydirectory.core.data.remote.util.createHttpClient
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.api.CharacterRemoteDataSource
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.api.KtorCharacterRemoteDataSource
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientEngine> { CIO.create() }

    single {
        createHttpClient(
            engine = get(),
        )
    }

    single<CharacterRemoteDataSource> {
//        val scope = get<CoroutineScope>(named<ApplicationScope>())
        val ioDispatcher = get<CoroutineDispatcher>(named(RickAndMortyDispatchers.IO))

        KtorCharacterRemoteDataSource(
            client = get(),
            ioDispatcher = ioDispatcher,
        )
    }
}