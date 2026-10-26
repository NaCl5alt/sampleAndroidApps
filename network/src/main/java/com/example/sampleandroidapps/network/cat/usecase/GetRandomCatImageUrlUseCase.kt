package com.example.sampleandroidapps.network.cat.usecase

import com.example.sampleandroidapps.network.cat.CatImageRepository
import javax.inject.Inject

class GetRandomCatImageUrlUseCase @Inject constructor(
    private val catImageRepository: CatImageRepository
) {
    /**
     * 猫の画像のURLを取得するUseCase
     * 取得した一覧の1件目のURLを返す。取得に失敗した場合はその失敗を、一覧が空の場合は NoSuchElementException を Result.failure で返す
     */
    suspend operator fun invoke(): Result<String> {
        val catList = catImageRepository.getCatImages().getOrElse { return Result.failure(it) }
        val cat = catList.firstOrNull()
            ?: return Result.failure(NoSuchElementException("cat image list is empty"))
        return Result.success(cat.url)
    }
}
