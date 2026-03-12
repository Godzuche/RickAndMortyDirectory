package com.godzuche.rickandmortydirectory.features.characters.fakes

import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.api.CharacterRemoteDataSource
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.CharactersResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.InfoResponse

class FakeCharacterRemoteDataSource : CharacterRemoteDataSource {
    private var result: Result<CharactersResponse, DataError.Remote> = Result.Success(
        CharactersResponse(
            info = InfoResponse(
                count = 0,
                pages = 0,
                next = null,
                prev = null,
            ),
            results = emptyList()
        )
    )

    fun setQueryResult(result: Result<CharactersResponse, DataError.Remote>) {
        this.result = result
    }

    override suspend fun getCharacters(): Result<CharactersResponse, DataError.Remote> {
        return result
    }
}
