package dev.mitigate.dogapp.repository

import dev.mitigate.dogapp.BuildConfig
import dev.mitigate.dogapp.models.DogModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.api-ninjas.com/v1/"
private const val API_KEY = BuildConfig.API_KEY

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okhttpClient = OkHttpClient.Builder()
    .addInterceptor(Interceptor { chain ->
        //Interceptor for modifying the request to add the API_KEY
        val request = chain.request()

        val newRequest: Request = request.newBuilder()
            .addHeader("X-Api-Key", API_KEY)
            .build()
        chain.proceed(newRequest)
    })
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okhttpClient)
    .build()

interface DogApiService{
    @GET("dogs")
    suspend fun getDogs(@Query("name") name: String = " "): List<DogModel>
}

object DogApi{
    fun create(): DogApiService {
        return retrofit.create(DogApiService::class.java)
    }
}