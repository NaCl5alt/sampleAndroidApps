package com.example.sampleandroidapps.network.jsonPlaceholder.photo.usecase

import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.PhotoRepository
import javax.inject.Inject

class GetPhotoListUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    /**
     * 写真のデータを一覧取得するUseCase
     * 失敗した場合は、IOExceptionをResult.failureで返す
     */
    suspend operator fun invoke(albumId: Int): Result<List<Photo>> =
        photoRepository.getPhotos(albumId)
}
