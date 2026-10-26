package com.example.sampleandroidapps.util

import retrofit2.Response
import java.io.IOException

/**
 * Retrofit の呼び出し結果を [Result] に変換する
 *
 * - [Response.isSuccessful] が true でボディがあれば、ボディを Result.success で返す
 * - HTTP エラーのときやボディが無いときは、IOException を Result.failure で返す
 * - 通信そのものに失敗して IOException が投げられたときも、例外を投げずに Result.failure で返す
 */
suspend fun <T : Any> apiResult(
    call: suspend () -> Response<T>
): Result<T> {
    val response = try {
        call()
    } catch (e: IOException) {
        return Result.failure(e)
    }
    val body = response.body()
    return when {
        !response.isSuccessful -> Result.failure(IOException(response.message()))
        body == null -> Result.failure(IOException("response body is null"))
        else -> Result.success(body)
    }
}
