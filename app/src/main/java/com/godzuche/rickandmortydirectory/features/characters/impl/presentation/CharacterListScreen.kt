package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components.RickAndMortyCharacterCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(
    characterListViewModel: CharacterListViewModel = koinViewModel(),
) {
    val characterListUiState by characterListViewModel.characterListState.collectAsStateWithLifecycle()

    CharacterListScreenContent(
        characterListUiState = characterListUiState,
        onAction = characterListViewModel::onAction,
    )
}

@Composable
fun CharacterListScreenContent(
    characterListUiState: CharacterListUiState,
    onAction: (CharacterListAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            when (characterListUiState) {
                is CharacterListUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is CharacterListUiState.Error -> {
                    Text(
                        text = characterListUiState.message?.asString()
                            ?: "An error occurred. Please refresh",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp),
                    )
                }

                is CharacterListUiState.Success -> {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(150.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalItemSpacing = 16.dp,
                        contentPadding = PaddingValues(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp,
                            bottom = 8.dp,
                        )
                    ) {
                        items(
                            items = characterListUiState.characters,
                            key = { it.id }
                        ) { character ->
                            Card(
                                modifier = Modifier.animateItem(),
                                onClick = {
                                    onAction(
                                        CharacterListAction.OnCharacterClicked(character)
                                    )
                                },
                                elevation = CardDefaults
                                    .cardElevation(defaultElevation = 4.dp),
                            ) {
                                RickAndMortyCharacterCard(
                                    character = character,
                                    onClick = {
                                        onAction(
                                            CharacterListAction.OnCharacterClicked(character)
                                        )
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun CharactersListScreenPreview() = RickAndMortyDirectoryTheme {
    CharacterListScreenContent(
        characterListUiState = characterListPreviewData,
        onAction = {},
    )
}

private val characterListPreviewData = CharacterListUiState.Success(
    characters = listOf(
        CharacterUiState(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            origin = "Earth (C-137)",
            location = "Citadel of Ricks",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        ),
        CharacterUiState(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            gender = "Male",
            origin = "Earth (C-137)",
            location = "Citadel of Ricks",
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        ),
        CharacterUiState(
            id = 3,
            name = "Summer Smith",
            status = "Alive",
            species = "Human",
            gender = "Female",
            origin = "Earth (C-137)",
            location = "Earth (C-137)",
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
        ),
    )
)
