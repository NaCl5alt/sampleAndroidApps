package com.example.sampleandroidapps.network.cat.usecase

import com.example.sampleandroidapps.network.cat.CatService
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

class GetRandomCatImageUrlUseCaseTest {
    private val server = MockWebServer()
    private lateinit var useCase: GetRandomCatImageUrlUseCase

    @Before
    fun setUp() {
        server.start()
        useCase = GetRandomCatImageUrlUseCase(CatService(server.createRetrofit()))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `取得に成功すると1件目の画像URLを返す`() = runTest {
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

        val result = useCase()

        assertEquals("https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg", result.getOrThrow())
        assertEquals("/v1/images/search?limit=1", server.takeRequest().path)
    }

    @Test
    fun `結果が空配列のときは失敗を返す`() = runTest {
        server.enqueue(MockResponse().setBody("[]"))

        val result = useCase()

        assertEquals("catData is null", result.exceptionOrNull()?.message)
    }

    @Test
    fun `HTTPエラーのときはIOExceptionで失敗を返す`() = runTest {
        server.enqueue(MockResponse().setResponseCode(429))

        val result = useCase()

        assertTrue(result.exceptionOrNull() is IOException)
    }
}
