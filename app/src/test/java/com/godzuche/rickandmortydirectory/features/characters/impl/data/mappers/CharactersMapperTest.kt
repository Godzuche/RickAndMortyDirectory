package com.godzuche.rickandmortydirectory.features.characters.impl.data.mappers

import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.CharactersResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.InfoResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.LocationResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.OriginResponse
import com.godzuche.rickandmortydirectory.features.characters.impl.data.remote.model.ResultResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class CharactersMapperTest {

    @Test
    fun `map CharactersResponse to domain model`() {
        // Given
        val response = CharactersResponse(
            info = InfoResponse(count = 1, pages = 1, next = "next_url", prev = null),
            results = listOf(
                ResultResponse(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "",
                    gender = "Male",
                    origin = OriginResponse(name = "Earth", url = ""),
                    location = LocationResponse(name = "Earth", url = ""),
                    image = "image_url",
                    episode = listOf("ep1"),
                    url = "url",
                    created = "date"
                )
            )
        )

        // When
        val domainModel = response.asDomainModel()

        // Then
        assertEquals(response.info.count, domainModel.info.count)
        assertEquals(response.results.size, domainModel.results.size)
        assertEquals(response.results[0].name, domainModel.results[0].name)
        assertEquals(response.results[0].location.name, domainModel.results[0].location)
    }
}
