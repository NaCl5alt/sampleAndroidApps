package com.example.sampleandroidapps.network.jsonPlaceholder.album

import com.example.sampleandroidapps.network.createRetrofit
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DefaultAlbumRepositoryTest {
    private val server = MockWebServer()
    private lateinit var repository: DefaultAlbumRepository

    @Before
    fun setUp() {
        server.start()
        repository = DefaultAlbumRepository(AlbumService(server.createRetrofit()))
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

        val result = repository.getAlbums()

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

        val result = repository.getAlbums()

        assertTrue(result.exceptionOrNull() is IOException)
    }
}
