package com.example.sampleandroidapps.util

import kotlinx.coroutines.test.runTest
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class ApiResultTest {
    @Test
    fun `レスポンスが成功のときはボディをResultで返す`() = runTest {
        val result = apiResult { Response.success(listOf("a", "b")) }

        assertEquals(listOf("a", "b"), result.getOrThrow())
    }

    @Test
    fun `レスポンスが失敗のときはステータスメッセージを持つIOExceptionを返す`() = runTest {
        val result = apiResult { errorResponse<List<String>>(code = 404, message = "Not Found") }

        val exception = result.exceptionOrNull()
        assertTrue(exception is IOException)
        assertEquals("Not Found", exception?.message)
    }

    @Test
    fun `成功してもボディが無いときはIOExceptionを返す`() = runTest {
        val result = apiResult { Response.success<String>(null) }

        assertTrue(result.exceptionOrNull() is IOException)
    }

    @Test
    fun `通信に失敗して例外が投げられたときは例外を投げずに失敗を返す`() = runTest {
        val networkError = IOException("Unable to resolve host")

        val result = apiResult<String> { throw networkError }

        assertSame(networkError, result.exceptionOrNull())
    }

    private fun <T> errorResponse(code: Int, message: String): Response<T> =
        Response.error(
            "".toResponseBody(),
            okhttp3.Response.Builder()
                .request(Request.Builder().url("https://example.com/").build())
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message(message)
                .build(),
        )
}
