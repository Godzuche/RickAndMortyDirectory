package com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: InfoResponse,
    val results: List<ResultResponse>
)