package com.example.sampleandroidapps.feature.coil.navigation

import androidx.navigation3.runtime.NavKey
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import kotlinx.serialization.Serializable

sealed interface CoilAppNavKey : NavKey {
    @Serializable
    data object AlbumScreen : CoilAppNavKey

    @Serializable
    data class PhotoScreen(val album: Album) : CoilAppNavKey

    @Serializable
    data class PhotoDetailScreen(val photo: Photo) : CoilAppNavKey
}
