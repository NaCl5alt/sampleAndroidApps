package com.example.sampleandroidapps.network.cat

import com.example.sampleandroidapps.util.apiResult
import javax.inject.Inject

/**
 * 猫の画像のデータを提供する Repository（データレイヤの入口）
 * 失敗した場合は、IOException を Result.failure で返す
 */
interface CatImageRepository {
    suspend fun getCatImages(): Result<List<Cat>>
}

internal class DefaultCatImageRepository @Inject constructor(
    private val catService: CatService
) : CatImageRepository {
    override suspend fun getCatImages(): Result<List<Cat>> =
        apiResult { catService.getCatImage() }
}
