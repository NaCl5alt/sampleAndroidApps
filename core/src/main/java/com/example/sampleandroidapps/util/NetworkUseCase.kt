package com.example.sampleandroidapps.util

import retrofit2.Response
import java.io.IOException

open class NetworkUseCase {
    /**
     * @return [Response.isSuccessful]がtrueであれば、response.body()をResult.successで返す。それ以外は、IOExceptionをResult.failureで返す。
     */
    suspend fun <T : Any> apiResult(
        call: suspend () -> Response<T>
    ): Result<T> {
        val response = call.invoke()
        return if (response.isSuccessful) Result.success(response.body() as T)
        else Result.failure(IOException(response.message()))
    }
}
