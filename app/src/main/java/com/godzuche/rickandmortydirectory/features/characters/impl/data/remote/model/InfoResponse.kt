package com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?,
)