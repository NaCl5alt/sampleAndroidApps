package com.example.sampleandroidapps.network.jsonPlaceholder.photo

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@Keep
@Serializable
@JsonClass(generateAdapter = false)
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

internal interface PhotoApi {
    @GET("/albums/{albumId}/photos")
    suspend fun getPhotos(
        @Path("albumId") albumId: Int
    ): Response<List<Photo>>
}
