package com.godzuche.rickandmortydirectory.core.data.di

import com.godzuche.rickandmortydirectory.core.data.datastore.RickAndMortyPreferencesDataSource
import com.godzuche.rickandmortydirectory.core.data.repository.OfflineFirstUserDataRepository
import com.godzuche.rickandmortydirectory.core.domain.repository.UserDataRepository
import com.godzuche.rickandmortydirectory.features.characters.impl.data.repository.OfflineFirstCharacterRepository
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::RickAndMortyPreferencesDataSource)
    single<UserDataRepository> {
        val ioDispatcher = get<CoroutineDispatcher>(named(RickAndMortyDispatchers.IO))

        OfflineFirstUserDataRepository(
            preferencesDataSource = get(),
            ioDispatcher = ioDispatcher,
        )
    }

    singleOf(::OfflineFirstCharacterRepository).bind<CharacterRepository>()
}