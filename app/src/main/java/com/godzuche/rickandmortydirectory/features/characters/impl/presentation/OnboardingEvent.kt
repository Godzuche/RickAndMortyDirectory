package com.godzuche.rickandmortydirectory.features.onboarding.impl.presentation

sealed interface OnboardingEvent {
//    data object RequestRolePermission : OnboardingEvent
    data object ShowManualRoleSettingsGuidance : OnboardingEvent
    data object NavigateToCorePermissions : OnboardingEvent
    data object OnboardingSuccess : OnboardingEvent
    data class ShowPermissionDeniedMessage(val permission: String) : OnboardingEvent
}