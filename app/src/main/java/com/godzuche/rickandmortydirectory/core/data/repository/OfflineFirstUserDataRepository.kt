package com.godzuche.rickandmortydirectory.core.data.repository

import com.godzuche.rickandmortydirectory.core.data.datastore.RickAndMortyPreferencesDataSource
import com.godzuche.rickandmortydirectory.core.domain.model.ThemeConfig
import com.godzuche.rickandmortydirectory.core.domain.model.UserPreferences
import com.godzuche.rickandmortydirectory.core.domain.repository.UserDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class OfflineFirstUserDataRepository(
    private val preferencesDataSource: RickAndMortyPreferencesDataSource,
    ioDispatcher: CoroutineDispatcher,
) : UserDataRepository {

    override val userPreferencesData: Flow<UserPreferences> =
        preferencesDataSource.userPreferencesData
            .flowOn(ioDispatcher)

    override suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        preferencesDataSource.setDarkThemeConfig(themeConfig)
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        preferencesDataSource.setDynamicColorPreference(useDynamicColor)
    }
}