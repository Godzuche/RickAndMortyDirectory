package com.godzuche.rickandmortydirectory.core.data.model

import com.godzuche.rickandmortydirectory.core.domain.model.ThemeConfig
import kotlinx.serialization.Serializable


/**
 * Class summarizing user settings/preferences data
 */
@Serializable
data class UserPreferencesData(
    val themeConfig: ThemeConfig = ThemeConfig.FOLLOW_SYSTEM,
    val useDynamicColor: Boolean = false,
)