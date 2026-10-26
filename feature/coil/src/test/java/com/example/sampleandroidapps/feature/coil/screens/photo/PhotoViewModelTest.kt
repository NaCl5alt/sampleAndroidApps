package com.example.sampleandroidapps.feature.coil.screens.photo

import com.example.sampleandroidapps.feature.coil.MainDispatcherRule
import com.example.sampleandroidapps.feature.coil.fake.FakeCatImageRepository
import com.example.sampleandroidapps.feature.coil.fake.FakePhotoRepository
import com.example.sampleandroidapps.network.cat.Cat
import com.example.sampleandroidapps.network.cat.usecase.GetRandomCatImageUrlUseCase
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.usecase.GetPhotoListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val photoRepository = FakePhotoRepository()
    private val catImageRepository = FakeCatImageRepository()

    private val album = Album(userId = 1, id = 3, title = "omnis laborum odio")
    private val photoList = listOf(
        Photo(
            albumId = 3,
            id = 101,
            title = "incidunt alias vel enim",
            url = "https://via.placeholder.com/600/e743b",
            thumbnailUrl = "https://via.placeholder.com/150/e743b",
        ),
        Photo(
            albumId = 3,
            id = 102,
            title = "eaque iste corporis tempora vero distinctio consequuntur nisi nesciunt",
            url = "https://via.placeholder.com/600/a393af",
            thumbnailUrl = "https://via.placeholder.com/150/a393af",
        ),
    )
    private val cat1 = Cat(id = "a1", url = "https://cdn2.thecatapi.com/images/a1.jpg", width = 500, height = 375)
    private val cat2 = Cat(id = "b2", url = "https://cdn2.thecatapi.com/images/b2.jpg", width = 640, height = 480)

    // 本物の UseCase にフェイクの Repository を渡して組み立てる
    private fun createViewModel() = PhotoViewModel(
        getPhotoListUseCase = GetPhotoListUseCase(photoRepository),
        getRandomCatImageUrlUseCase = GetRandomCatImageUrlUseCase(catImageRepository),
        ioDispatcher = mainDispatcherRule.testDispatcher,
        album = album,
    )

    @Test
    fun `UiStateの初期値には画面に渡されたアルバムが入る`() = runTest {
        photoRepository.result = Result.success(photoList)
        catImageRepository.enqueue(Result.success(listOf(cat1)))
        catImageRepository.enqueue(Result.success(listOf(cat2)))

        val viewModel = createViewModel()

        assertEquals(
            PhotoScreenUiState(album = album, photoList = emptyList()),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `写真ごとに猫の画像URLへ差し替えた一覧がUiStateに入る`() = runTest {
        photoRepository.result = Result.success(photoList)
        catImageRepository.enqueue(Result.success(listOf(cat1)))
        catImageRepository.enqueue(Result.success(listOf(cat2)))

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(
            listOf(
                photoList[0].copy(url = cat1.url, thumbnailUrl = cat1.url),
                photoList[1].copy(url = cat2.url, thumbnailUrl = cat2.url),
            ),
            viewModel.uiState.value.photoList,
        )
        assertEquals(listOf(album.id), photoRepository.requestedAlbumIds)
    }

    @Test
    fun `猫の画像URLの取得に失敗した写真はURLが空文字になる`() = runTest {
        photoRepository.result = Result.success(photoList)
        catImageRepository.enqueue(Result.success(listOf(cat1)))
        catImageRepository.enqueue(Result.failure(IOException("Too Many Requests")))

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(
            listOf(
                photoList[0].copy(url = cat1.url, thumbnailUrl = cat1.url),
                photoList[1].copy(url = "", thumbnailUrl = ""),
            ),
            viewModel.uiState.value.photoList,
        )
    }

    @Test
    fun `写真一覧の取得に失敗したときは猫の画像URLを取得しない`() = runTest {
        photoRepository.result = Result.failure(IOException("Not Found"))

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(emptyList<Photo>(), viewModel.uiState.value.photoList)
        assertEquals(0, catImageRepository.callCount)
    }
}
