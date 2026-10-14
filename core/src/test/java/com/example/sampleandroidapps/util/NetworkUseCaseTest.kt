package com.example.sampleandroidapps.util

import kotlinx.coroutines.test.runTest
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class NetworkUseCaseTest {
    private val useCase = object : NetworkUseCase() {}

    @Test
    fun `レスポンスが成功のときはボディをResultで返す`() = runTest {
        val result = useCase.apiResult { Response.success(listOf("a", "b")) }

        assertEquals(listOf("a", "b"), result.getOrThrow())
    }

    @Test
    fun `レスポンスが失敗のときはステータスメッセージを持つIOExceptionを返す`() = runTest {
        val result = useCase.apiResult { errorResponse<List<String>>(code = 404, message = "Not Found") }

        val exception = result.exceptionOrNull()
        assertTrue(exception is IOException)
        assertEquals("Not Found", exception?.message)
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
