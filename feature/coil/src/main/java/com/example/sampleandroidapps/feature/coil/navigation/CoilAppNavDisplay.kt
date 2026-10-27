package com.example.sampleandroidapps.feature.coil.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.sampleandroidapps.feature.coil.screens.album.AlbumScreen
import com.example.sampleandroidapps.feature.coil.screens.photo.PhotoScreen
import com.example.sampleandroidapps.feature.coil.screens.photo.PhotoViewModel
import com.example.sampleandroidapps.feature.coil.screens.photoDetails.PhotoDetailScreen
import com.example.sampleandroidapps.feature.settings.SettingsScreen

@Composable
fun CoilAppNavDisplay() {
    val backstack = rememberNavBackStack(CoilAppNavKey.AlbumScreen)
    val addToBackStack: (NavKey) -> Unit = { key -> backstack.add(key) }

    NavDisplay(
        backStack = backstack,
        onBack = { backstack.removeLastOrNull() },
        // 画面（NavEntry）ごとに ViewModel を持たせるため、既定の状態保存のデコレータに ViewModelStore のデコレータを足す
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<CoilAppNavKey.AlbumScreen> {
                AlbumScreen(onNavigate = addToBackStack)
            }

            entry<CoilAppNavKey.PhotoScreen> { key ->
                PhotoScreen(
                    onNavigate = addToBackStack,
                    viewModel = hiltViewModel<PhotoViewModel, PhotoViewModel.Factory>(
                        key = key.album.id.toString()
                    ) { factory ->
                        factory.create(key.album)
                    }
                )
            }

            entry<CoilAppNavKey.PhotoDetailScreen> { key ->
                PhotoDetailScreen(key.photo)
            }

            // 設定画面も別の Activity にせず、同じ NavDisplay の画面として表示する
            entry<CoilAppNavKey.SettingsScreen> {
                SettingsScreen()
            }
        }
    )
}
