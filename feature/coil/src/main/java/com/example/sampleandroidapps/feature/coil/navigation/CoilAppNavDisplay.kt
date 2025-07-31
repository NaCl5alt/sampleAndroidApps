package com.example.sampleandroidapps.feature.coil.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.sampleandroidapps.feature.coil.screens.album.AlbumScreen
import com.example.sampleandroidapps.feature.coil.screens.photo.PhotoScreen
import com.example.sampleandroidapps.feature.coil.screens.photo.PhotoViewModel
import com.example.sampleandroidapps.feature.coil.screens.photoDetails.PhotoDetailScreen

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
                CoilAppNavKey.AlbumScreen -> NavEntry(key) {
                    AlbumScreen(onNavigate = addToBackStack)
                }

                is CoilAppNavKey.PhotoScreen -> NavEntry(key) {
                    PhotoScreen(
                        onNavigate = addToBackStack,
                        viewModel = hiltViewModel<PhotoViewModel, PhotoViewModel.Factory>
                            (key = key.album.id.toString()) { factory ->
                            factory.create(key.album)
                        }
                    )
                }

                is CoilAppNavKey.PhotoDetailScreen -> NavEntry(key) {
                    PhotoDetailScreen(key.photo)
                }
            }
        } as (NavKey) -> NavEntry<NavKey>
    )
}
