package com.godzuche.rickandmortydirectory.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.godzuche.rickandmortydirectory.core.data.datastore.RickAndMortyPreferencesSerializer
import com.godzuche.rickandmortydirectory.core.data.model.UserPreferencesData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DATA_STORE_FILE_NAME = "user_preferences.json"

val dataStoreModule = module {
    single { RickAndMortyPreferencesSerializer }

    single<DataStore<UserPreferencesData>> {
        val ioDispatcher = get<CoroutineDispatcher>(named(DendDispatchers.IO))
        val scope = get<CoroutineScope>(named<ApplicationScope>())
        val userPreferencesSerializer = get<RickAndMortyPreferencesSerializer>()

        DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = {
                    // return default value
                    UserPreferencesData()
                }
            ),
            migrations = listOf(),
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
            produceFile = {
                androidContext().dataStoreFile(DATA_STORE_FILE_NAME)
            },
        )
    }
}