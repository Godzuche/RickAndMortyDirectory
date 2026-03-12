package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godzuche.rickandmortydirectory.core.domain.utils.onError
import com.godzuche.rickandmortydirectory.core.domain.utils.onSuccess
import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEvent
import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEventBus
import com.godzuche.rickandmortydirectory.core.presentation.utils.toUiText
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharacterDetailsScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.impl.domain.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val appEventBus: AppEventBus,
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    private val _characterListState =
        MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    val characterListState = _characterListState
        .onStart {
            getCharacters()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = _characterListState.value,
        )


    fun onAction(action: CharacterListAction) {
        when (action) {
            is CharacterListAction.OnCharacterClicked -> {
                viewModelScope.launch {
                    appEventBus.sendEvent(AppEvent.Navigate(CharacterDetailsScreenNavKey(action.character)))
                }
            }

            is CharacterListAction.OnRefresh -> {
                getCharacters()
            }
        }
    }

    fun getCharacters() {
        viewModelScope.launch {
            _characterListState.update {
                CharacterListUiState.Loading
            }
            characterRepository.getCharacters()
                .onSuccess { characters ->
                    _characterListState.update {
                        CharacterListUiState.Success(
                            characters = characters.asUiModel(),
                        )
                    }
                }
                .onError { e ->
                    _characterListState.update {
                        CharacterListUiState.Error(e.toUiText())
                    }
                }

        }
    }
}