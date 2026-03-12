package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import app.cash.turbine.test
import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEvent
import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEventBus
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharacterDetailsScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.fakes.FakeCharacterRepository
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Characters
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.model.Info
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var repository: FakeCharacterRepository
    private lateinit var appEventBus: AppEventBus
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeCharacterRepository()
        appEventBus = AppEventBus()
        viewModel = CharacterListViewModel(appEventBus, repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Loading`() = runTest {
        viewModel.characterListState.test {
            // stateIn with WhileSubscribed will emit initialValue immediately upon subscription
            assertEquals(CharacterListUiState.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCharacters success updates state to Success`() = runTest {
        // Given
        val characters = Characters(
            info = Info(count = 1, pages = 1, next = null, prev = null),
            results = emptyList()
        )
        repository.setQueryResult(Result.Success(characters))

        // When & Then
        viewModel.characterListState.test {
            // 1. Initial Loading
            assertEquals(CharacterListUiState.Loading, awaitItem())

            // onStart -> getCharacters() is triggered by the subscription
            advanceUntilIdle()

            // 2. Success state
            val state = awaitItem()
            assertTrue("Expected Success state but got $state", state is CharacterListUiState.Success)
        }
    }

    @Test
    fun `getCharacters error updates state to Error`() = runTest {
        // Given
        repository.setQueryResult(Result.Error(DataError.Remote.Server))

        // When & Then
        viewModel.characterListState.test {
            // 1. Initial Loading
            assertEquals(CharacterListUiState.Loading, awaitItem())

            advanceUntilIdle()

            // 2. Error state
            val state = awaitItem()
            assertTrue("Expected Error state but got $state", state is CharacterListUiState.Error)
        }
    }

    @Test
    fun `OnCharacterClicked action sends Navigate event`() = runTest {
        val characterUiState = CharacterUiState(
            id = 1, name = "Rick", species = "Human", image = "",
            gender = "Male", origin = "Earth", location = "Earth", status = "Alive"
        )

        viewModel.onAction(CharacterListAction.OnCharacterClicked(characterUiState))

        appEventBus.events.test {
            val event = awaitItem()
            assertTrue(event is AppEvent.Navigate)
            assertEquals(
                CharacterDetailsScreenNavKey(characterUiState),
                (event as AppEvent.Navigate).navKey
            )
        }
    }

//    @Test
//    fun `OnRefresh action triggers getCharacters`() = runTest {
//        // Given
//        val characters = Characters(
//            info = Info(count = 1, pages = 1, next = null, prev = null),
//            results = emptyList()
//        )
//        repository.setQueryResult(Result.Success(characters))
//
//        viewModel.characterListState.test {
//            // Skip initial events triggered by onStart
//            awaitItem() // Loading
//            advanceUntilIdle()
//            awaitItem() // Success
//
//            // When
//            viewModel.onAction(CharacterListAction.OnRefresh)
//
//            // Then
//            // OnRefresh forces getCharacters which updates to Loading then Success
//            assertEquals(CharacterListUiState.Loading, awaitItem())
//            advanceUntilIdle()
//            assertTrue(awaitItem() is CharacterListUiState.Success)
//        }
//    }
}
