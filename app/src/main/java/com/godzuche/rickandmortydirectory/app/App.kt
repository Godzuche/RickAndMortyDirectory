package com.godzuche.rickandmortydirectory.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.godzuche.rickandmortydirectory.app.navigation.NavigationRoot
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme

@Composable
fun App() {
    RickAndMortyDirectoryTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            NavigationRoot(
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}