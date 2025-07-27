package com.example.sampleandroidapps.feature.coil.screens.photo.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import com.example.sampleandroidapps.feature.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.feature.coil.screens.photo.PhotoScreen
import com.example.sampleandroidapps.feature.coil.screens.photo.PhotoViewModel

fun photoScreen(key: CoilAppNavKey.PhotoScreen, onNavigate: (CoilAppNavKey) -> Unit) =
    NavEntry(key) {
        PhotoScreen(
            onNavigate = onNavigate,
            viewModel = hiltViewModel<PhotoViewModel, PhotoViewModel.Factory>
                (key = key.album.id.toString()) { factory ->
                factory.create(key.album)
            }
        )
    }
