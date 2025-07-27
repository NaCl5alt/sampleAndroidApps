package com.example.sampleandroidapps.network.jsonPlaceholder.photo

import com.example.sampleandroidapps.network.di.JsonPlaceholderRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoService @Inject constructor(
    @param:JsonPlaceholderRetrofit
    private val retrofit: Retrofit
) : PhotoApi {
    private val photoApi: PhotoApi by lazy { retrofit.create(PhotoApi::class.java) }

    override suspend fun getPhotos(albumId: Int): Response<List<Photo>> =
        photoApi.getPhotos(albumId)
}
