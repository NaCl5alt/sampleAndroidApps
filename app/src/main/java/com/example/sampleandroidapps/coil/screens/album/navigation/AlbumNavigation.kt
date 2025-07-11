package com.example.sampleandroidapps.coil.screens.album.navigation

import androidx.navigation3.runtime.NavEntry
import com.example.sampleandroidapps.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.coil.screens.album.AlbumScreen

fun albumScreen(onNavigate: (CoilAppNavKey) -> Unit) = NavEntry(CoilAppNavKey.AlbumScreen) {
    AlbumScreen(onNavigate = onNavigate)
}
