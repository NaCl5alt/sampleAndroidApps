package com.example.sampleandroidapps.feature.coil.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.sampleandroidapps.feature.coil.screens.album.navigation.albumScreen
import com.example.sampleandroidapps.feature.coil.screens.photo.navigation.photoScreen
import com.example.sampleandroidapps.feature.coil.screens.photoDetails.navigation.photoDetailScreen

@Composable
fun CoilAppNavDisplay() {
    val backstack = rememberNavBackStack(CoilAppNavKey.AlbumScreen)
    val addToBackStack: (NavKey) -> Unit = { key -> backstack.add(key) }

    NavDisplay(
        backStack = backstack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key: CoilAppNavKey ->
            when (key) {
                CoilAppNavKey.AlbumScreen -> albumScreen(onNavigate = addToBackStack)

                is CoilAppNavKey.PhotoScreen -> photoScreen(
                    key,
                    onNavigate = addToBackStack
                )

                is CoilAppNavKey.PhotoDetailScreen -> photoDetailScreen(key)
            }
        } as (NavKey) -> NavEntry<NavKey>
    )
}
