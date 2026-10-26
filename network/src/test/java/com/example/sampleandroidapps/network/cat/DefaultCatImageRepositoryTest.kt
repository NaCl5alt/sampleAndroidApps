package com.example.sampleandroidapps.network.cat

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

class DefaultCatImageRepositoryTest {
    private val server = MockWebServer()
    private lateinit var repository: DefaultCatImageRepository

    @Before
    fun setUp() {
        server.start()
        repository = DefaultCatImageRepository(CatService(server.createRetrofit()))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `取得に成功するとJSONをパースした猫の画像の一覧を返す`() = runTest {
        server.enqueue(
            MockResponse().setBody(
                """
                [
                  {
                    "id": "MTY3ODIyMQ",
                    "url": "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg",
                    "width": 1204,
                    "height": 1445
                  }
                ]
                """.trimIndent()
            )
        )

        val result = repository.getCatImages()

        assertEquals(
            listOf(
                Cat(
                    id = "MTY3ODIyMQ",
                    url = "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg",
                    width = 1204,
                    height = 1445,
                ),
            ),
            result.getOrThrow(),
        )
        assertEquals("/v1/images/search?limit=1", server.takeRequest().path)
    }

    @Test
    fun `HTTPエラーのときはIOExceptionで失敗を返す`() = runTest {
        server.enqueue(MockResponse().setResponseCode(429))

        val result = repository.getCatImages()

        assertTrue(result.exceptionOrNull() is IOException)
    }
}
