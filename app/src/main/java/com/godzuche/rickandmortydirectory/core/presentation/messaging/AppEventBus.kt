package com.godzuche.rickandmortydirectory.core.presentation.messaging

import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed interface AppEvent {
    data class Navigate(val navKey: NavKey, val clearBackStack: Boolean = false) : AppEvent
    data class NavigateBack(val navKey: NavKey) : AppEvent
    data class ShowSnackbar(val message: String) : AppEvent
    data class ShowToast(val message: String) : AppEvent
    data class ShowDialog(val message: String) : AppEvent
}

class AppEventBus {
    private val eventChannel = Channel<AppEvent>()
    val events = eventChannel.receiveAsFlow()

    suspend fun sendEvent(event: AppEvent) {
        eventChannel.send(event)
    }
}