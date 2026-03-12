package com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class OriginResponse(
    val name: String,
    val url: String
)