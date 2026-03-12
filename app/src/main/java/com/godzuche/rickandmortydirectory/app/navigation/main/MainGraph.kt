package com.godzuche.rickandmortydirectory.app.navigation.main

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.godzuche.rickandmortydirectory.app.navigation.rememberNavBackStack
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharactersListScreenNavKey
import com.godzuche.rickandmortydirectory.R
import com.godzuche.rickandmortydirectory.features.characters.api.navigation.CharacterDetailsScreenNavKey
import com.godzuche.rickandmortydirectory.features.characters.impl.navigation.characterDetailsEntry
import com.godzuche.rickandmortydirectory.features.characters.impl.navigation.charactersListEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
//    onboardingViewModel: OnboardingViewModel = koinActivityViewModel<OnboardingViewModel>(),
//    onOnboardingSuccess: () -> Unit,
) {
    val mainBackStack = rememberNavBackStack<NavKey>(CharactersListScreenNavKey)

//    var showSettingsDialog by remember { mutableStateOf(false) }
//
//    val context = LocalContext.current
//    LaunchedEffect(Unit) {
//        onboardingViewModel.events.collect { event ->
//            when (event) {
//                is OnboardingEvent.ShowManualRoleSettingsGuidance -> {
//                    showSettingsDialog = true
//                }
//
//                is OnboardingEvent.NavigateToCorePermissions -> {
//                    onboardingBackStack.add(CorePermissionsScreenNavKey)
//                }
//
//                is OnboardingEvent.OnboardingSuccess -> {
//////                    Toast.makeText(context, "Firewall activated!", Toast.LENGTH_SHORT).show()
////                    backStack.remove(OnboardingGraphNavKey)
////                    backStack.add(MainNavKey(showOnboardingSuccessMessage = true))
//                    onOnboardingSuccess()
//                }
//
//                is OnboardingEvent.ShowPermissionDeniedMessage -> {
//                    Toast.makeText(
//                        context,
//                        "\"${event.permission}\" Permission is required for app functionalities",
//                        Toast.LENGTH_LONG,
//                    ).show()
//                }
//            }
//        }
//    }
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

//    val dashboardUiState by dashboardViewModel.uiState.collectAsStateWithLifecycle()
//
//    val animatedBackgroundColor by animateColorAsState(
//        targetValue = if (dashboardUiState.firewallUiState.firewallState == FirewallState.ZEN) {
//            MaterialTheme.colorScheme.surfaceContainerLow
//        } else {
//            MaterialTheme.colorScheme.background
//        },
//        animationSpec = tween(durationMillis = 500),
//        label = "background_color_anim"
//    )
//
//    ObserveAsEvent(
//        flow = rulesViewModel.events,
//    ) { event ->
//        val uiEvent = when (event) {
//            is RulesUiEvent.RuleAdded -> {
//                val text =
//                    UiText.StringResource(
//                        R.string.rule_added_successfully,
//                        listOf(
//                            event.number,
//                            event.selectedRulesTab.title,
//                        )
//                    )
//                UiEvent.ShowSnackbar(text)
//            }
//
//            is RulesUiEvent.RuleRemoved -> {
//                val text =
//                    UiText.StringResource(
//                        R.string.rule_removed_successfully,
//                        listOf(
//                            event.contactLabel,
//                            event.selectedRulesTab.title,
//                        ),
//                    )
//
//                UiEvent.ShowSnackbar(
//                    message = text,
//                    snackbarAction = ShowSnackbarAction.UndoRemoveRule(
//                        undoAction = SnackbarAction.UNDO,
//                    ),
//                )
//            }
//
//            is RulesUiEvent.OperationFailed -> {
//                val text = event.error.toUiText()
//                UiEvent.ShowSnackbar(text)
//            }
//        }
//
//        uiEventBus.sendEvent(uiEvent)
//
//    }
//
//    ObserveAsEvent(
//        flow = uiEventBus.events,
//        uiEventBus,
//        snackbarHostState,
//        context,
//    ) { event ->
//        when (event) {
//            is UiEvent.ShowSnackbar -> {
//                val message = event.message.asString(context)
//                val actionLabel = event.snackbarAction?.action?.label
//
//                val result = snackbarHostState.showSnackbar(
//                    message = message,
//                    actionLabel = actionLabel,
//                    duration = if (actionLabel == null) {
//                        SnackbarDuration.Short
//                    } else SnackbarDuration.Short,
//                )
//
//                when (result) {
//                    SnackbarResult.ActionPerformed -> {
//                        when (event.snackbarAction) {
//                            // User tapped "Undo". Send the corresponding event back.
//                            is ShowSnackbarAction.UndoRemoveRule -> {
//                                if (event.snackbarAction.action == SnackbarAction.UNDO) {
//                                    // A better way would be to have a specific event for this.
//                                    // For now, let's call the ViewModel directly.
//                                    rulesViewModel.onUndoRemove()
//                                }
//                            }
//
//                            else -> Unit
//                        }
//                    }
//
//                    SnackbarResult.Dismissed -> {
//                        // Snackbar timed out or was dismissed
//                        when (event.snackbarAction) {
//                            is ShowSnackbarAction.UndoRemoveRule -> {
//                                if (event.snackbarAction.action == SnackbarAction.UNDO) {
//                                    rulesViewModel.onCommitRemove()
//                                }
//                            }
//
//                            else -> Unit
//                        }
//                    }
//                }
//            }
//        }
//    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
//        containerColor = animatedBackgroundColor,
        topBar = {
            val title = when (mainBackStack.last()) {
                CharactersListScreenNavKey -> "Characters"
                CharacterDetailsScreenNavKey -> "Character Details"
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
                                contentDescription = "Back",
                            )
                        }
                    }
                },
                title = {
                    Text(
                        text = title,
//                        style = MaterialTheme.typography.titleMedium,
                    )

//                    AnimatedVisibility(
//                        visible = backStack.last() == RulesNavKey,
//                        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
//                        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
//                    ) {
//                        Text(
//                            text = "Rules",
//                            style = MaterialTheme.typography.titleMedium,
//                        )
//                    }
//
//                    AnimatedVisibility(
//                        visible = backStack.last() == BottomSheetNavKey
//                        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
//                        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
//                    ) {
//                        Text(
//                            text = "Add Rule",
//                            style = MaterialTheme.typography.titleMedium,
//                        )
//                    }
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
                charactersListEntry(backStack = mainBackStack)
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