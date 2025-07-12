package com.example.sampleandroidapps.coil.screens.photoDetails.navigation

import androidx.navigation3.runtime.NavEntry
import com.example.sampleandroidapps.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.coil.screens.photoDetails.PhotoDetailScreen

fun photoDetailScreen(key: CoilAppNavKey.PhotoDetailScreen) = NavEntry(key) {
    PhotoDetailScreen(key.photo)
}
