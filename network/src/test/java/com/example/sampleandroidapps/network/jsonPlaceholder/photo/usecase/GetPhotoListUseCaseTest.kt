package com.example.sampleandroidapps.network.jsonPlaceholder.photo.usecase

import com.example.sampleandroidapps.network.createRetrofit
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.PhotoService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetPhotoListUseCaseTest {
    private val server = MockWebServer()
    private lateinit var useCase: GetPhotoListUseCase

    @Before
    fun setUp() {
        server.start()
        useCase = GetPhotoListUseCase(PhotoService(server.createRetrofit()))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `指定したアルバムIDの写真一覧を取得してパースする`() = runTest {
        server.enqueue(
            MockResponse().setBody(
                """
                [
                  {
                    "albumId": 3,
                    "id": 101,
                    "title": "incidunt alias vel enim",
                    "url": "https://via.placeholder.com/600/e743b",
                    "thumbnailUrl": "https://via.placeholder.com/150/e743b"
                  }
                ]
                """.trimIndent()
            )
        )

        val result = useCase(albumId = 3)

        assertEquals(
            listOf(
                Photo(
                    albumId = 3,
                    id = 101,
                    title = "incidunt alias vel enim",
                    url = "https://via.placeholder.com/600/e743b",
                    thumbnailUrl = "https://via.placeholder.com/150/e743b",
                ),
            ),
            result.getOrThrow(),
        )
        assertEquals("/albums/3/photos", server.takeRequest().path)
    }

    @Test
    fun `HTTPエラーのときはIOExceptionで失敗を返す`() = runTest {
        server.enqueue(MockResponse().setResponseCode(404))

        val result = useCase(albumId = 999)

        assertTrue(result.exceptionOrNull() is IOException)
    }
}
