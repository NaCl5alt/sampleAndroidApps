package com.example.sampleandroidapps.coil.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface CoilAppNavKey : NavKey {
    @Serializable
    data object AlbumScreen : CoilAppNavKey

    @Serializable
    data object PhotoScreen : CoilAppNavKey

    @Serializable
    data object PhotoDetailScreen : CoilAppNavKey
}
