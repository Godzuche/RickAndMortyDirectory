package com.godzuche.rickandmortydirectory.core.data.datastore

import androidx.datastore.core.DataStore
import com.godzuche.rickandmortydirectory.core.data.model.UserPreferencesData
import com.godzuche.rickandmortydirectory.core.domain.model.ThemeConfig
import com.godzuche.rickandmortydirectory.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.map

class RickAndMortyPreferencesDataSource(
    private val userPreferences: DataStore<UserPreferencesData>,
) {
    val userPreferencesData = userPreferences.data
        .map {
            UserPreferences(
                themeConfig = it.themeConfig,
                useDynamicColor = it.useDynamicColor,
            )
        }

    suspend fun setDarkThemeConfig(themeConfig: ThemeConfig) {
        userPreferences.updateData {
            it.copy(
                themeConfig = themeConfig,
            )
        }
    }

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        userPreferences.updateData {
            it.copy(
                useDynamicColor = useDynamicColor,
            )
        }
    }

}