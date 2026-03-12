package com.godzuche.rickandmortydirectory.features.characters.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharacterDetailsScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharactersListScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.CharacterDetailsScreen
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.CharactersListScreen

fun EntryProviderScope<NavKey>.charactersListEntry(
    backStack: NavBackStack<NavKey>,
) {
    entry<CharactersListScreenNavKey> {
        CharactersListScreen(
//            onGetStartedClick = {
//                backStack.add(CharacterDetailsScreenNavKey)
//            },
            onCharacterSelected = {}
        )
    }
}

fun EntryProviderScope<NavKey>.characterDetailsEntry() {
    entry<CharacterDetailsScreenNavKey> {
        CharacterDetailsScreen()
    }
}
