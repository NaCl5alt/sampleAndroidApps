package com.example.sampleandroidapps.network.jsonPlaceholder.album.usecase

import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.album.AlbumService
import com.example.sampleandroidapps.util.NetworkUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAlbumListUseCase @Inject constructor(
    private val albumService: AlbumService
) : NetworkUseCase() {
    /**
     * アルバムのデータを一覧取得するUseCase
     * 失敗した場合は、IOExceptionをResult.failureで返す
     */
    suspend operator fun invoke(): Result<List<Album>> = apiResult { albumService.getAlbums() }
}
