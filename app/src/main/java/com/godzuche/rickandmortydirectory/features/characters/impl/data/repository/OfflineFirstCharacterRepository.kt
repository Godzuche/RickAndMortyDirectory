package com.godzuche.rickandmortydirectory.features.characters.impl.data.repository

import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import com.godzuche.rickandmortydirectory.core.domain.utils.map
import com.godzuche.rickandmortydirectory.features.characters.impl.data.mappers.asDomainModel
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.api.CharacterRemoteDataSource
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.CharacterRepository
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Characters

class OfflineFirstCharacterRepository(
    private val remoteDataSource: CharacterRemoteDataSource,
//    private val localDataSource: CharacterLocalDataSource,
) : CharacterRepository {
    override suspend fun getCharacters(): Result<Characters, DataError.Remote> {
        return remoteDataSource.getCharacters()
            .map { it.asDomainModel() }
    }
}