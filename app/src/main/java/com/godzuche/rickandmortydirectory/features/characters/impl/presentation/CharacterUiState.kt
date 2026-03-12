package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.compose.runtime.Stable
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.CharacterResult
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Characters
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class CharacterUiState(
    val id: Int,
    val name: String,
    val species: String,
    val image: String,
    val gender: String,
    val origin: String,
    val location: String,

    // extra (not in the task description for the list items)
    val status: String,
)

fun CharacterResult.asUiState() = CharacterUiState(
    id = id,
    name = name,
    species = species,
    image = image,
    gender = gender,
    origin = origin,
    location = location,
    status = status,
)

fun Characters.asUiModel() = results.map(CharacterResult::asUiState)