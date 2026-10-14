package com.example.sampleandroidapps.network.jsonPlaceholder.album.usecase

import com.example.sampleandroidapps.network.createRetrofit
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.album.AlbumService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetAlbumListUseCaseTest {
    private val server = MockWebServer()
    private lateinit var useCase: GetAlbumListUseCase

    @Before
    fun setUp() {
        server.start()
        useCase = GetAlbumListUseCase(AlbumService(server.createRetrofit()))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `取得に成功するとJSONをパースしたアルバム一覧を返す`() = runTest {
        server.enqueue(
            MockResponse().setBody(
                """
                [
                  {"userId": 1, "id": 1, "title": "quidem molestiae enim"},
                  {"userId": 1, "id": 2, "title": "sunt qui excepturi placeat culpa"}
                ]
                """.trimIndent()
            )
        )

        val result = useCase()

        assertEquals(
            listOf(
                Album(userId = 1, id = 1, title = "quidem molestiae enim"),
                Album(userId = 1, id = 2, title = "sunt qui excepturi placeat culpa"),
            ),
            result.getOrThrow(),
        )
        val request = server.takeRequest()
        assertEquals("GET", request.method)
        assertEquals("/albums", request.path)
    }

    @Test
    fun `HTTPエラーのときはIOExceptionで失敗を返す`() = runTest {
        server.enqueue(MockResponse().setResponseCode(500))

        val result = useCase()

        assertTrue(result.exceptionOrNull() is IOException)
    }
}
