package com.godzuche.rickandmortydirectory.core.domain.repository

import com.godzuche.rickandmortydirectory.core.domain.model.ThemeConfig
import com.godzuche.rickandmortydirectory.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    /**
     * Stream of [UserPreferences]
     */
    val userPreferencesData: Flow<UserPreferences>

    suspend fun setThemeConfig(themeConfig: ThemeConfig)

    /**
     * Sets the preferred dynamic color config.
     */
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}