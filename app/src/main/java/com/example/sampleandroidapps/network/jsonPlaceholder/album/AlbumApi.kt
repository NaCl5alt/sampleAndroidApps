package com.example.sampleandroidapps.network.jsonPlaceholder.album

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET

@Serializable
@JsonClass(generateAdapter = true)
data class Album(
    val userId: Int,
    val id: Int,
    val title: String
)

interface AlbumApi {
    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>
}
