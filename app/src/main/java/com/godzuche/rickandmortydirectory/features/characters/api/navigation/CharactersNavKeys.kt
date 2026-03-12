package com.godzuche.rickandmortydirectory.features.characters.api.navigation

import androidx.navigation3.runtime.NavKey
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.CharacterUiState
import kotlinx.serialization.Serializable

@Serializable
data object CharactersListScreenNavKey : NavKey

@Serializable
data class CharacterDetailsScreenNavKey(
    val characterUiState: CharacterUiState
) : NavKey