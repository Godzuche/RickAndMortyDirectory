package com.godzuche.rickandmortydirectory.app.navigation.main

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

fun EntryProviderScope<NavKey>.mainGraphEntry() {
    entry<MainGraphNavKey> {
        MainGraph()
    }
}