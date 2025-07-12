package com.example.sampleandroidapps.network.jsonPlaceholder.photo

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Serializable
@JsonClass(generateAdapter = true)
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

interface PhotoApi {
    @GET("/albums/{albumId}/photos")
    suspend fun getPhotos(
        @Query("albumId") albumId: Int
    ): Response<List<Photo>>
}
