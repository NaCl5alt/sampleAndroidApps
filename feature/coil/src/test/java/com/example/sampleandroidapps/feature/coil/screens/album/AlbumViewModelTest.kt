package com.example.sampleandroidapps.feature.coil.screens.album

import com.example.sampleandroidapps.feature.coil.MainDispatcherRule
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.album.usecase.GetAlbumListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAlbumListUseCase: GetAlbumListUseCase = mockk()

    private val albumList = listOf(
        Album(userId = 1, id = 1, title = "quidem molestiae enim"),
        Album(userId = 1, id = 2, title = "sunt qui excepturi placeat culpa"),
    )

    private fun createViewModel() = AlbumViewModel(
        getAlbumListUseCase = getAlbumListUseCase,
        ioDispatcher = mainDispatcherRule.testDispatcher,
    )

    @Test
    fun `アルバム一覧の取得が終わるまではUiStateは初期値のまま`() = runTest {
        coEvery { getAlbumListUseCase() } returns Result.success(albumList)

        val viewModel = createViewModel()

        assertEquals(AlbumScreenUiState.default, viewModel.uiState.value)
    }

    @Test
    fun `アルバム一覧の取得に成功するとUiStateに反映される`() = runTest {
        coEvery { getAlbumListUseCase() } returns Result.success(albumList)

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(albumList, viewModel.uiState.value.albumList)
    }

    @Test
    fun `アルバム一覧の取得に失敗したときはUiStateが空のまま`() = runTest {
        coEvery { getAlbumListUseCase() } returns Result.failure(IOException("Internal Server Error"))

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(emptyList<Album>(), viewModel.uiState.value.albumList)
    }
}
