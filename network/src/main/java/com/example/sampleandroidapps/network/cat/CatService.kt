package com.example.sampleandroidapps.network.cat

import com.example.sampleandroidapps.network.di.CatApiRetrofit
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatService @Inject constructor(
    @param:CatApiRetrofit
    private val retrofit: Retrofit
) : CatApi {
    private val catApi: CatApi by lazy { retrofit.create(CatApi::class.java) }

    override suspend fun getCatImage() = catApi.getCatImage()
}
