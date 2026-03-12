package com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.godzuche.rickandmortydirectory.core.designsystem.theme.AliveColor
import com.godzuche.rickandmortydirectory.core.designsystem.theme.DeadColor
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme
import com.godzuche.rickandmortydirectory.features.characters.impl.presentation.CharacterUiState

@Composable
internal fun RickAndMortyCharacterCard(
    character: CharacterUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme
                .colorScheme
                .surfaceVariant.copy(alpha = 0.5f),
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column {
            Box(contentAlignment = Alignment.BottomStart) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image of ${character.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.FillWidth,
//                    placeholder = painterResource(R.drawable.loading),
//                    error = painterResource(R.drawable.error),
//                    fallback = painterResource(R.drawable.null_image),
                )

                // Species
                Surface(
                    color = MaterialTheme
                        .colorScheme
                        .secondaryContainer
                        .copy(alpha = 0.85f),
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = character.species,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }

            // Info Section
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Status Indicator Dot
                    val statusColor = when (character.status.lowercase()) {
                        "alive" -> AliveColor
                        "dead" -> DeadColor
                        else -> Color.Gray
                    }

                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(statusColor)
                    )

                    Text(
                        text = character.status,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun RickAndMortyCharacterCardPreview() = RickAndMortyDirectoryTheme {
    RickAndMortyCharacterCard(
        character = characterPreviewData,
        onClick = {},
    )
}

internal val characterPreviewData = CharacterUiState(
    id = 2,
    name = "Morty Smith",
    status = "Alive",
    species = "Human",
    gender = "Male",
    origin = "Earth (C-137)",
    location = "Citadel of Ricks",
    image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
)