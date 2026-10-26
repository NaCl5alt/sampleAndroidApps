package com.example.sampleandroidapps.network.jsonPlaceholder.album

import com.example.sampleandroidapps.util.apiResult
import javax.inject.Inject

/**
 * アルバムのデータを提供する Repository（データレイヤの入口）
 * 失敗した場合は、IOException を Result.failure で返す
 */
interface AlbumRepository {
    suspend fun getAlbums(): Result<List<Album>>
}

internal class DefaultAlbumRepository @Inject constructor(
    private val albumService: AlbumService
) : AlbumRepository {
    override suspend fun getAlbums(): Result<List<Album>> =
        apiResult { albumService.getAlbums() }
}
