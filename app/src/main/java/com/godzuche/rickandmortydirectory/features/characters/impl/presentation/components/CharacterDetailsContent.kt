package com.godzuche.rickandmortydirectory.features.characters.impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godzuche.rickandmortydirectory.R
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme

@Composable
internal fun CharacterDetailsContent(
    status: String,
    gender: String,
    origin: String,
    location: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Quick Info Row (Status and Gender)
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoTile(
                label = "Status",
                value = status,
                icon = ImageVector.vectorResource(
                    R.drawable.info_24dp
                ),
                modifier = Modifier.weight(1f),
                valueColor = when (status.lowercase()) {
                    "alive" -> Color(0xFF4CAF50)
                    "dead" -> Color(0xFFF44336)
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            InfoTile(
                label = "Gender",
                value = gender,
                icon = ImageVector.vectorResource(
                    id = if (gender.lowercase() == "male") {
                        R.drawable.face_male_24dp
                    } else {
                        R.drawable.face_female_24dp
                    }
                ),
                modifier = Modifier.weight(1f),
            )
        }

        // Location Details
        DetailCard(
            title = "Origin",
            value = origin,
            icon = ImageVector.vectorResource(
                id = R.drawable.home_pin_24dp,
            )
        )

        DetailCard(
            title = "Last Known Location",
            value = location,
            icon = ImageVector.vectorResource(
                id = R.drawable.location_on_24dp,
            )
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun CharacterDetailsContentPreview() = RickAndMortyDirectoryTheme {
    CharacterDetailsContent(
        status = "Alive",
        gender = "Male",
        origin = "Earth (C-137)",
        location = "Citadel of Ricks",
    )
}