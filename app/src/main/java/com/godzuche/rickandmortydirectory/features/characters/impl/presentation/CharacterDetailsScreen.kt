package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components.CharacterDetailsContent
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components.CharacterHeroSection
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components.characterPreviewData

@Composable
fun CharacterDetailsScreen(
    characterUiState: CharacterUiState,
) {
    CharacterDetailsScreenContent(
        characterUiState = characterUiState,
    )

}

@Composable
fun CharacterDetailsScreenContent(
    characterUiState: CharacterUiState,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        CharacterHeroSection(
            image = characterUiState.image,
            name = characterUiState.name,
            species = characterUiState.species,
        )

        CharacterDetailsContent(
            status = characterUiState.status,
            gender = characterUiState.gender,
            origin = characterUiState.origin,
            location = characterUiState.location,
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun CharacterDetailsScreenPreview() = RickAndMortyDirectoryTheme {
    CharacterDetailsScreenContent(
        characterUiState = characterPreviewData,
    )
}
