package com.example.sampleandroidapps.feature.coil.screens.album.navigation

import androidx.navigation3.runtime.NavEntry
import com.example.sampleandroidapps.feature.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.feature.coil.screens.album.AlbumScreen

fun albumScreen(onNavigate: (CoilAppNavKey) -> Unit) = NavEntry(CoilAppNavKey.AlbumScreen) {
    AlbumScreen(onNavigate = onNavigate)
}
