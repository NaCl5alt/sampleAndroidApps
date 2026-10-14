package com.example.sampleandroidapps.network

import com.example.sampleandroidapps.network.di.NetworkProvideModule
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

/**
 * MockWebServer に接続する Retrofit を作る
 * Converter はアプリ本体（NetworkProvideModule）と同じものを使い、JSON のパースまで本番と同じ設定で確認する
 */
internal fun MockWebServer.createRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(url("/"))
        .addConverterFactory(NetworkProvideModule.provideNullOnEmptyConverterFactory())
        .addConverterFactory(NetworkProvideModule.provideMoshiConverterFactory())
        .build()
