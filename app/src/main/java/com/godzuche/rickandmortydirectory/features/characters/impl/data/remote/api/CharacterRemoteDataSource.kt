package com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.api

import com.godzuche.rickandmortydirectory.core.data.remote.util.constructUrl
import com.godzuche.rickandmortydirectory.core.data.remote.util.safeCall
import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.CharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CharacterRemoteDataSource {
    suspend fun getCharacters(): Result<CharactersResponse, DataError.Remote>
}

class KtorCharacterRemoteDataSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val client: HttpClient
) : CharacterRemoteDataSource {
    override suspend fun getCharacters(): Result<CharactersResponse, DataError.Remote> {
        return withContext(ioDispatcher){
            safeCall<CharactersResponse> {
                client.get(constructUrl("/character"))
            }
        }
    }
}
