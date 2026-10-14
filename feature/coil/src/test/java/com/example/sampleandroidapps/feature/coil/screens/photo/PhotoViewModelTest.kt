package com.example.sampleandroidapps.feature.coil.screens.photo

import com.example.sampleandroidapps.feature.coil.MainDispatcherRule
import com.example.sampleandroidapps.network.cat.usecase.GetRandomCatImageUrlUseCase
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.usecase.GetPhotoListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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

    private val getPhotoListUseCase: GetPhotoListUseCase = mockk()
    private val getRandomCatImageUrlUseCase: GetRandomCatImageUrlUseCase = mockk()

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
    private val catImageUrl1 = "https://cdn2.thecatapi.com/images/a1.jpg"
    private val catImageUrl2 = "https://cdn2.thecatapi.com/images/b2.jpg"

    private fun createViewModel() = PhotoViewModel(
        getPhotoListUseCase = getPhotoListUseCase,
        getRandomCatImageUrlUseCase = getRandomCatImageUrlUseCase,
        ioDispatcher = mainDispatcherRule.testDispatcher,
        album = album,
    )

    @Test
    fun `UiStateの初期値には画面に渡されたアルバムが入る`() = runTest {
        coEvery { getPhotoListUseCase(album.id) } returns Result.success(photoList)
        coEvery { getRandomCatImageUrlUseCase() } returns Result.success(catImageUrl1)

        val viewModel = createViewModel()

        assertEquals(
            PhotoScreenUiState(album = album, photoList = emptyList()),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `写真ごとに猫の画像URLへ差し替えた一覧がUiStateに入る`() = runTest {
        coEvery { getPhotoListUseCase(album.id) } returns Result.success(photoList)
        coEvery { getRandomCatImageUrlUseCase() } returnsMany listOf(
            Result.success(catImageUrl1),
            Result.success(catImageUrl2),
        )

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(
            listOf(
                photoList[0].copy(url = catImageUrl1, thumbnailUrl = catImageUrl1),
                photoList[1].copy(url = catImageUrl2, thumbnailUrl = catImageUrl2),
            ),
            viewModel.uiState.value.photoList,
        )
        coVerify(exactly = 1) { getPhotoListUseCase(album.id) }
    }

    @Test
    fun `猫の画像URLの取得に失敗した写真はURLが空文字になる`() = runTest {
        coEvery { getPhotoListUseCase(album.id) } returns Result.success(photoList)
        coEvery { getRandomCatImageUrlUseCase() } returnsMany listOf(
            Result.success(catImageUrl1),
            Result.failure(IOException("Too Many Requests")),
        )

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(
            listOf(
                photoList[0].copy(url = catImageUrl1, thumbnailUrl = catImageUrl1),
                photoList[1].copy(url = "", thumbnailUrl = ""),
            ),
            viewModel.uiState.value.photoList,
        )
    }

    @Test
    fun `写真一覧の取得に失敗したときは猫の画像URLを取得しない`() = runTest {
        coEvery { getPhotoListUseCase(album.id) } returns Result.failure(IOException("Not Found"))

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(emptyList<Photo>(), viewModel.uiState.value.photoList)
        coVerify(exactly = 0) { getRandomCatImageUrlUseCase() }
    }
}
