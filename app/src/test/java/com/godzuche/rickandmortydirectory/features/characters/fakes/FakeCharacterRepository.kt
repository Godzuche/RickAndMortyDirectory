package com.godzuche.rickandmortydirectory.features.characters.fakes

import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.CharacterRepository
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Characters
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Info

class FakeCharacterRepository : CharacterRepository {
    private var result: Result<Characters, DataError.Remote> = Result.Success(
        Characters(info = Info(
            count = 0,
            pages = 0,
            next = null,
            prev = null,
        ), results = emptyList())
    )

    fun setQueryResult(result: Result<Characters, DataError.Remote>) {
        this.result = result
    }

    override suspend fun getCharacters(): Result<Characters, DataError.Remote> {
        return result
    }
}
