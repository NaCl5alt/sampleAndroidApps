package com.example.sampleandroidapps.feature.coil.fake

import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.PhotoRepository

/**
 * テスト用の PhotoRepository
 * [result] に入れた結果を返し、要求されたアルバムIDを [requestedAlbumIds] に記録する
 */
class FakePhotoRepository(
    var result: Result<List<Photo>> = Result.success(emptyList())
) : PhotoRepository {
    val requestedAlbumIds = mutableListOf<Int>()

    override suspend fun getPhotos(albumId: Int): Result<List<Photo>> {
        requestedAlbumIds += albumId
        return result
    }
}
