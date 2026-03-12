package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.godzuche.rickandmortydirectory.R
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components.CharacterShimmerItem
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components.RickAndMortyCharacterCard
import io.github.aakira.napier.Napier
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(150.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalItemSpacing = 16.dp,
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.fillMaxSize(),
                        userScrollEnabled = false // Disable scrolling during loading for better UX
                    ) {
                        items(10) {
                            CharacterShimmerItem()
                        }
                    }
                }

                is CharacterListUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.error_24dp),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp),
                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Something went wrong with the multiverse portal :(",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = characterListUiState.message?.asString()
                                ?: "An unknown multiverse error occurred. Please refresh.",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { onAction(CharacterListAction.OnRefresh) },
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.refresh_24dp),
                                contentDescription = "Retry",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Retry")
                        }
                    }
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
                            key = { it.id },
                            contentType = { "character" },
                        ) { character ->
                            Card(
                                modifier = Modifier.animateItem(),
                                onClick = {
                                    Napier.d("Character clicked: $character")
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
