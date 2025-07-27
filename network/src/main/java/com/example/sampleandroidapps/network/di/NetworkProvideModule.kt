package com.example.sampleandroidapps.network.di

import android.content.Context
import coil3.ImageLoader
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.example.sampleandroidapps.network.BuildConfig
import com.example.sampleandroidapps.util.NullOnEmptyConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class JsonPlaceholderRetrofit

@Qualifier
annotation class CatApiRetrofit

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkProvideModule {
    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )

    @Singleton
    @Provides
    fun provideNullOnEmptyConverterFactory(): NullOnEmptyConverterFactory =
        NullOnEmptyConverterFactory()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()

    @JsonPlaceholderRetrofit
    @Singleton
    @Provides
    fun provideJsonPlaceholderRetrofit(
        nullOnEmptyConverterFactory: NullOnEmptyConverterFactory,
        moshiConverterFactory: MoshiConverterFactory,
        httpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(moshiConverterFactory)
            .client(httpClient)
            .build()

    @CatApiRetrofit
    @Singleton
    @Provides
    fun provideCatApiRetrofit(
        nullOnEmptyConverterFactory: NullOnEmptyConverterFactory,
        moshiConverterFactory: MoshiConverterFactory,
        httpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(moshiConverterFactory)
            .client(httpClient)
            .build()

    @Singleton
    @Provides
    fun provideImageLoader(
        @ApplicationContext
        context: Context
    ): ImageLoader = ImageLoader.Builder(context)
        .crossfade(true)
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()
}
