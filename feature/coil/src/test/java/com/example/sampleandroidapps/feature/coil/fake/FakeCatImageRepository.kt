package com.example.sampleandroidapps.feature.coil.fake

import com.example.sampleandroidapps.network.cat.Cat
import com.example.sampleandroidapps.network.cat.CatImageRepository
import java.io.IOException

/**
 * テスト用の CatImageRepository
 * [enqueue] で積んだ結果を、呼ばれた順に1つずつ返す。呼ばれた回数は [callCount] で確かめる
 */
class FakeCatImageRepository : CatImageRepository {
    private val results = ArrayDeque<Result<List<Cat>>>()

    var callCount = 0
        private set

    fun enqueue(result: Result<List<Cat>>) {
        results.addLast(result)
    }

    override suspend fun getCatImages(): Result<List<Cat>> {
        callCount++
        return results.removeFirstOrNull() ?: Result.failure(IOException("no result enqueued"))
    }
}
