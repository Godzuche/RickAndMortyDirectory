package com.godzuche.rickandmortydirectory.app.navigation.main

import android.widget.Toast
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.godzuche.rickandmortydirectory.R
import com.godzuche.rickandmortydirectory.app.navigation.rememberNavBackStack
import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEvent
import com.godzuche.rickandmortydirectory.core.presentation.messaging.AppEventBus
import com.godzuche.rickandmortydirectory.core.presentation.utils.ObserveAsEvent
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharacterDetailsScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharactersListScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.impl.navigation.characterDetailsEntry
import com.godzuche.rickandmortydirectory.features.characters.impl.navigation.charactersListEntry
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
    appEventBus: AppEventBus = koinInject(),
) {
    val mainBackStack = rememberNavBackStack<NavKey>(CharactersListScreenNavKey)

//    var showSettingsDialog by remember { mutableStateOf(false) }
//
//    val context = LocalContext.current
//
//    if (showSettingsDialog) {
//        RoleSettingsDialog(
//            onDismiss = { showSettingsDialog = false },
//            onGoToSettings = {
//                showSettingsDialog = false
//                val intent = Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS)
//                context.startActivity(intent)
//            }
//        )
//    }

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvent(
        flow = appEventBus.events,
        appEventBus,
        snackbarHostState,
        context,
    ) { event ->
        when (event) {
            is AppEvent.ShowSnackbar -> {
                snackbarHostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Short,
                )
            }

            is AppEvent.ShowToast -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }

            is AppEvent.ShowDialog -> {
                // Show dialog
            }

            is AppEvent.Navigate -> {
                if (event.clearBackStack) {
                    mainBackStack.clear()
                    mainBackStack.add(event.navKey)
                } else {
                    mainBackStack.add(event.navKey)
                }
            }

            is AppEvent.NavigateBack -> {
                mainBackStack.removeLastOrNull()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            val title = when (mainBackStack.last()) {
                CharactersListScreenNavKey -> stringResource(R.string.home)
                is CharacterDetailsScreenNavKey -> stringResource(R.string.details)
                else -> ""
            }

            TopAppBar(
                navigationIcon = {
                    if (mainBackStack.size > 1) {
                        IconButton(
                            onClick = {
                                mainBackStack.removeLastOrNull()
                            },
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.arrow_back_24dp),
                                contentDescription = stringResource(R.string.back),
                            )
                        }
                    }
                },
                title = {
                    Text(text = title)
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavDisplay(
            backStack = mainBackStack,
            onBack = { mainBackStack.removeLastOrNull() },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                charactersListEntry()
                characterDetailsEntry()
            },
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { it }
                ) + fadeIn() togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { -it }
                        ) + fadeOut()
            },
            popTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { -it }
                ) + fadeIn() togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { it }
                        ) + fadeOut()
            },
            predictivePopTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { -it }
                ) + fadeIn() togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { it }
                        ) + fadeOut()
            },
        )
    }

}