package com.example.sampleandroidapps.network.jsonPlaceholder.photo

import com.example.sampleandroidapps.util.apiResult
import javax.inject.Inject

/**
 * 写真のデータを提供する Repository（データレイヤの入口）
 * 失敗した場合は、IOException を Result.failure で返す
 */
interface PhotoRepository {
    suspend fun getPhotos(albumId: Int): Result<List<Photo>>
}

internal class DefaultPhotoRepository @Inject constructor(
    private val photoService: PhotoService
) : PhotoRepository {
    override suspend fun getPhotos(albumId: Int): Result<List<Photo>> =
        apiResult { photoService.getPhotos(albumId) }
}
