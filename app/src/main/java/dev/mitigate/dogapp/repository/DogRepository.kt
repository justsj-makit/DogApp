package dev.mitigate.dogapp.repository

import dev.mitigate.dogapp.models.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class DogRepository {
    private val apiClient = DogApi.create()

    suspend fun getDogList(): List<DogModel> {
        return withContext(Dispatchers.IO) {
            try {
                apiClient.getDogs().also {
                    Timber.e("<<< api call, $it")
                }
            }
            catch (e: Exception){
                Timber.e(e)
                return@withContext listOf()
            }
        }
    }

}