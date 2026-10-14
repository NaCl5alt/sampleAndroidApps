package com.example.sampleandroidapps.feature.coil.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/**
 * IO処理用のCoroutineDispatcher
 * ViewModelに注入しておくことで、テストではTestDispatcherに差し替えられるようにしている
 */
@Qualifier
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
internal object DispatcherProvideModule {
    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
