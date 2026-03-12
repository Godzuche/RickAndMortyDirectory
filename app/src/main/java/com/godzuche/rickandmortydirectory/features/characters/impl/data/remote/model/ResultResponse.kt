package com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationResponse,
    val name: String,
    val origin: OriginResponse,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)