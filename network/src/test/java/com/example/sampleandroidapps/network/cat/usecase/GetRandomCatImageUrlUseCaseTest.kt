package com.example.sampleandroidapps.network.cat.usecase

import com.example.sampleandroidapps.network.cat.Cat
import com.example.sampleandroidapps.network.cat.CatImageRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class GetRandomCatImageUrlUseCaseTest {
    private val cat1 = Cat(id = "MTY3ODIyMQ", url = "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg", width = 1204, height = 1445)
    private val cat2 = Cat(id = "b2", url = "https://cdn2.thecatapi.com/images/b2.jpg", width = 500, height = 375)

    @Test
    fun `取得に成功すると1件目の画像URLを返す`() = runTest {
        val useCase = GetRandomCatImageUrlUseCase(FakeCatImageRepository(Result.success(listOf(cat1, cat2))))

        assertEquals(cat1.url, useCase().getOrThrow())
    }

    @Test
    fun `一覧が空のときはNoSuchElementExceptionで失敗を返す`() = runTest {
        val useCase = GetRandomCatImageUrlUseCase(FakeCatImageRepository(Result.success(emptyList())))

        assertTrue(useCase().exceptionOrNull() is NoSuchElementException)
    }

    @Test
    fun `取得に失敗したときはその失敗をそのまま返す`() = runTest {
        val error = IOException("Too Many Requests")
        val useCase = GetRandomCatImageUrlUseCase(FakeCatImageRepository(Result.failure(error)))

        assertSame(error, useCase().exceptionOrNull())
    }

    /** 決まった結果を返すだけのフェイク */
    private class FakeCatImageRepository(
        private val result: Result<List<Cat>>
    ) : CatImageRepository {
        override suspend fun getCatImages(): Result<List<Cat>> = result
    }
}
