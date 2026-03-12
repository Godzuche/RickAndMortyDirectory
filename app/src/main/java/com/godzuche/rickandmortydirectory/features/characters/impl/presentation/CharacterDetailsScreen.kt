package com.godzuche.rickandmortydirectory.features.characters.impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godzuche.rickandmortydirectory.core.designsystem.theme.RickAndMortyDirectoryTheme
import org.koin.compose.viewmodel.koinActivityViewModel

@Composable
fun CharacterDetailsScreen() {
//    val onboardingViewModel = koinActivityViewModel<OnboardingViewModel>()
//    val callScreeningRoleRequester = rememberRoleRequester { isGranted ->
//        onboardingViewModel.onRolePermissionResult(isGranted)
//    }

    CharacterDetailsScreenContent(
//        onGrantPermissionClick = {
//            if (callScreeningRoleRequester.isRoleHeld()) {
//                onboardingViewModel.onRolePermissionResult(true)
//            } else {
//                callScreeningRoleRequester.requestRole()
//            }
//        },
    )

}

@Composable
fun CharacterDetailsScreenContent(
//    onGrantPermissionClick: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Character Details",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun CharacterDetailsScreenPreview() = RickAndMortyDirectoryTheme {
    CharacterDetailsScreenContent()
}
