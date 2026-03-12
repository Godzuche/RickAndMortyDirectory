package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

sealed interface CharacterListAction {
    data class OnCharacterClicked(val character: CharacterUiState) : CharacterListAction
}