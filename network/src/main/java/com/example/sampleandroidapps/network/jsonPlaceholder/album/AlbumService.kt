package com.example.sampleandroidapps.network.jsonPlaceholder.album

import com.example.sampleandroidapps.network.di.JsonPlaceholderRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumService @Inject constructor(
    @param:JsonPlaceholderRetrofit
    private val retrofit: Retrofit
) : AlbumApi {
    private val albumApi: AlbumApi by lazy { retrofit.create(AlbumApi::class.java) }

    override suspend fun getAlbums(): Response<List<Album>> = albumApi.getAlbums()
}
