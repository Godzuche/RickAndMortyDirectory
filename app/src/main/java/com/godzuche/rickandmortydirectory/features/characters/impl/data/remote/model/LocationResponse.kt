package com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val name: String,
    val url: String
)