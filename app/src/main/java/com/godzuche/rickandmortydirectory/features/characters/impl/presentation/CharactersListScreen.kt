package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme

@Composable
fun CharactersListScreen(
    onCharacterSelected: (Character) -> Unit,
//    characters: List<Character> = emptyList(),
) {
//    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                /*.padding(innerPadding)
                .padding(horizontal = 32.dp)*/,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Character List",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )

            Button(
                onClick = {
//                    onCharacterClick("")
                }
            ) {
                Text("Navigate to Character Details")
            }

//                LazyVerticalStaggeredGrid(
//                    columns = StaggeredGridCells.Adaptive(150.dp),
//                    horizontalArrangement = Arrangement.spacedBy(16.dp),
//                    verticalItemSpacing = 16.dp,
////                    contentPadding = PaddingValues(
////                        start = innerPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
////                        end = innerPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
////                        top = innerPadding.calculateTopPadding() + 8.dp,
////                        bottom = innerPadding.calculateBottomPadding() + 8.dp,
////                    )
//                    contentPadding = PaddingValues(
//                        start = 8.dp,
//                        end = 8.dp,
//                        top = 8.dp,
//                        bottom = 8.dp,
//                    )
//                ) {
//                    items(
//                        items = characters,
//                        key = { it.id }
//                    ) { character ->
//                        Card(
//                            onClick = {
//                                onCharacterSelected(character)
//                            },
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                        ) {
//                            Image(
//                                painter = painterResource(character.drawable),
//                                contentScale = ContentScale.FillWidth,
//                                modifier = Modifier.fillMaxWidth(),
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }

        }
//    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun CharactersListScreenPreview() {
    RickAndMortyDirectoryTheme {
        CharactersListScreen(
            onCharacterSelected = {}
        )
    }
}
