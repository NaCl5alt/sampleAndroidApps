package com.example.sampleandroidapps.network.jsonPlaceholder.photo.usecase

import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.PhotoService
import com.example.sampleandroidapps.util.NetworkUseCase
import javax.inject.Inject

class GetPhotoListUseCase @Inject constructor(
    private val photoService: PhotoService
) : NetworkUseCase() {
    /**
     * 写真のデータを一覧取得するUseCase
     * 失敗した場合は、IOExceptionをResult.failureで返す
     */
    suspend operator fun invoke(albumId: Int): Result<List<Photo>> =
        apiResult { photoService.getPhotos(albumId) }
}
