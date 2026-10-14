package com.example.sampleandroidapps.util

import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class NullOnEmptyConverterFactoryTest {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/")
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(StringConverterFactory())
        .build()

    private val converter = retrofit.responseBodyConverter<String>(String::class.java, emptyArray())

    @Test
    fun `ボディが空のときはnullに変換する`() {
        assertNull(converter.convert("".toResponseBody()))
    }

    @Test
    fun `ボディがあるときは後続のConverterで変換する`() {
        assertEquals("hello", converter.convert("hello".toResponseBody()))
    }

    /** ResponseBody をそのまま文字列にするだけのテスト用 Converter */
    private class StringConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation?>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *> = Converter<ResponseBody, String> { it.string() }
    }
}
