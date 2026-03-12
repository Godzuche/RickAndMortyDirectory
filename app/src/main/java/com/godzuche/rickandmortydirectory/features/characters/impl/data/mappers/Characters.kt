package com.godzuche.rickandmortydirectory.features.characters.impl.data.mappers

import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.CharactersResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.InfoResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.ResultResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.CharacterResult
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Characters
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Info

fun InfoResponse.asDomainModel() = Info(
    count = count,
    next = next,
    pages = pages,
    prev = prev,
)

fun ResultResponse.asDomainModel() = CharacterResult(
    created = created,
    episode = episode,
    gender = gender,
    id = id,
    image = image,
    location = location.name,
    name = name,
    origin = origin.name,
    species = species,
    status = status,
    type = type,
    url = url,
)

fun List<ResultResponse>.asDomainModels() = map(ResultResponse::asDomainModel)

fun CharactersResponse.asDomainModel() = Characters(
    info = info.asDomainModel(),
    results = results.asDomainModels(),
)