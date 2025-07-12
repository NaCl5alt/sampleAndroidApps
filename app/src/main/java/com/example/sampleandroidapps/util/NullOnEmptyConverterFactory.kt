package com.example.sampleandroidapps.util

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * https://gist.github.com/jaredsburrows/26e2c2272f7dbea86a00abe4b26503a4
 */
class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation?>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, Any> =
            retrofit.nextResponseBodyConverter(this, type, annotations)
        return Converter {
            if (it.contentLength() == 0L) null else delegate.convert(it)
        }
    }
}
