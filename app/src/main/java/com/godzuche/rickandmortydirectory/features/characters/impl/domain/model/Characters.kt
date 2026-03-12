package com.godzuche.rickandmortydirectory.features.characters.impl.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Characters(
    val info: Info,
    val results: List<CharacterResult>
)
