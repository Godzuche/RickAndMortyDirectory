package com.godzuche.rickandmortydirectory.features.characters.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharacterDetailsScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharactersListScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.CharacterDetailsScreen
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.CharacterListScreen

fun EntryProviderScope<NavKey>.charactersListEntry(
) {
    entry<CharactersListScreenNavKey> {
        CharacterListScreen()
    }
}

fun EntryProviderScope<NavKey>.characterDetailsEntry() {
    entry<CharacterDetailsScreenNavKey> { key ->
        CharacterDetailsScreen(
            characterUiState = key.characterUiState,
        )
    }
}
