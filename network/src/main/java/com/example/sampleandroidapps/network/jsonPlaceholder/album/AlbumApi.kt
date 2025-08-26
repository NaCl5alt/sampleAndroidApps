package com.example.sampleandroidapps.network.jsonPlaceholder.album

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET

@Keep
@Serializable
@JsonClass(generateAdapter = false)
data class Album(
    val userId: Int,
    val id: Int,
    val title: String
)

internal interface AlbumApi {
    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>
}
