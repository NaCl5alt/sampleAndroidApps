package com.example.sampleandroidapps.feature.coil.fake

import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.album.AlbumRepository

/**
 * テスト用の AlbumRepository
 * [result] に入れた結果をそのまま返す
 */
class FakeAlbumRepository(
    var result: Result<List<Album>> = Result.success(emptyList())
) : AlbumRepository {
    override suspend fun getAlbums(): Result<List<Album>> = result
}
