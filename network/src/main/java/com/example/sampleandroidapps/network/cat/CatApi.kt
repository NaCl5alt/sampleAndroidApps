package com.example.sampleandroidapps.network.cat

import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.http.GET

@JsonClass(generateAdapter = false)
data class Cat(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)

interface CatApi {
    @GET("/v1/images/search?limit=1")
    suspend fun getCatImage(): Response<List<Cat>>
}
