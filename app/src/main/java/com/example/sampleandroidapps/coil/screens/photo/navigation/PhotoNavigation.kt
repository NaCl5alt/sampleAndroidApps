package com.example.sampleandroidapps.coil.screens.photo.navigation

import androidx.navigation3.runtime.NavEntry
import com.example.sampleandroidapps.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.coil.screens.photo.PhotoScreen

fun photoScreen(onNavigate: (CoilAppNavKey) -> Unit) = NavEntry(CoilAppNavKey.PhotoScreen) {
    PhotoScreen(onNavigate = onNavigate)
}
