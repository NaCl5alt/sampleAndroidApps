package com.example.sampleandroidapps.network.jsonPlaceholder.album.usecase

import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.album.AlbumRepository
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    /**
     * アルバムのデータを一覧取得するUseCase
     * 失敗した場合は、IOExceptionをResult.failureで返す
     */
    suspend operator fun invoke(): Result<List<Album>> = albumRepository.getAlbums()
}
