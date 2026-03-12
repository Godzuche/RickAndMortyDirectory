package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.godzuche.rickandmortydirectory.core.presentation.UiText
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Characters
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.CharacterResult
import kotlinx.serialization.Serializable

@Stable
sealed interface CharacterListUiState {
    data object Loading : CharacterListUiState

    @Immutable
    data class Success(val characters: List<CharacterUiState>) : CharacterListUiState
    data class Error(val message: UiText?) : CharacterListUiState
}
