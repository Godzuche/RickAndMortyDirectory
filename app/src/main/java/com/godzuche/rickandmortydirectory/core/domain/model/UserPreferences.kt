package com.godzuche.rickandmortydirectory.core.domain.model

data class UserPreferences(
    val themeConfig: ThemeConfig = ThemeConfig.FOLLOW_SYSTEM,
    val useDynamicColor: Boolean = false,
    val shouldHideOnboarding: Boolean = false,
    val firewallState: FirewallState = FirewallState.OFF,
)