package com.example.sampleandroidapps.network.di

import com.example.sampleandroidapps.network.cat.CatImageRepository
import com.example.sampleandroidapps.network.cat.DefaultCatImageRepository
import com.example.sampleandroidapps.network.jsonPlaceholder.album.AlbumRepository
import com.example.sampleandroidapps.network.jsonPlaceholder.album.DefaultAlbumRepository
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.DefaultPhotoRepository
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Repository のインターフェースに実装を結びつける
 * 利用する側（UseCase）はインターフェースだけに依存し、テストではフェイクに差し替えられる
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryBindModule {
    @Binds
    abstract fun bindAlbumRepository(repository: DefaultAlbumRepository): AlbumRepository

    @Binds
    abstract fun bindPhotoRepository(repository: DefaultPhotoRepository): PhotoRepository

    @Binds
    abstract fun bindCatImageRepository(repository: DefaultCatImageRepository): CatImageRepository
}
