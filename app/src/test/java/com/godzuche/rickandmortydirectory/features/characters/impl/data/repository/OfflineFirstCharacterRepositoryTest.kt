package com.godzuche.rickandmortydirectory.features.characters.impl.data.repository

import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import com.godzuche.rickandmortydirectory.features.characters.fakes.FakeCharacterRemoteDataSource
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.CharactersResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.InfoResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class OfflineFirstCharacterRepositoryTest {

    private lateinit var repository: OfflineFirstCharacterRepository
    private lateinit var remoteDataSource: FakeCharacterRemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = FakeCharacterRemoteDataSource()
        repository = OfflineFirstCharacterRepository(remoteDataSource)
    }

    @Test
    fun `getCharacters returns success when remote data source is successful`() = runTest {
        // Given
        val remoteResponse = CharactersResponse(
            info = InfoResponse(count = 10, pages = 1, next = null, prev = null),
            results = emptyList()
        )
        remoteDataSource.setQueryResult(Result.Success(remoteResponse))

        // When
        val result = repository.getCharacters()

        // Then
        assertTrue(result is Result.Success)
        assertEquals(10, (result as Result.Success).data.info.count)
    }

    @Test
    fun `getCharacters returns error when remote data source fails`() = runTest {
        // Given
        remoteDataSource.setQueryResult(Result.Error(DataError.Remote.NoInternet))

        // When
        val result = repository.getCharacters()

        // Then
        assertTrue(result is Result.Error)
        assertEquals(DataError.Remote.NoInternet, (result as Result.Error).error)
    }
}
