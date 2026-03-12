package com.godzuche.rickandmortydirectory.features.characters.impl.domain

import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Characters

interface CharacterRepository {
    suspend fun getCharacters(): Result<Characters, DataError.Remote>
}