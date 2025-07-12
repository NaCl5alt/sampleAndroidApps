package com.example.sampleandroidapps.network.cat.usecase

import com.example.sampleandroidapps.network.cat.CatService
import com.example.sampleandroidapps.util.NetworkUseCase
import javax.inject.Inject

class GetRandomCatImageUrlUseCase @Inject constructor(
    private val catService: CatService
) : NetworkUseCase() {
    /**
     * 猫の画像のURLを取得するUseCase
     * 失敗した場合は、IOExceptionをResult.failureで返す
     */
    suspend operator fun invoke(): Result<String> {
        val apiResult = apiResult { catService.getCatImage() }

        if (apiResult.isFailure) return Result.failure(apiResult.exceptionOrNull()!!)

        val catData = apiResult.getOrNull()?.firstOrNull()

        return if (catData == null) Result.failure(Exception("catData is null"))
        else Result.success(catData.url)
    }
}
