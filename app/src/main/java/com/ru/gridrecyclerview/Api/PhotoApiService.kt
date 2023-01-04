package com.ru.gridrecyclerview.Api

import com.ru.gridrecyclerview.model.data.GridData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://picsum.photos/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PhotoApiService {
    @GET("list?page=2&limit=30")
    suspend fun getData() : List<GridData>
}
object PhotoApi {
    val retrofitService : PhotoApiService by lazy {
        retrofit.create(PhotoApiService::class.java)
    }
}