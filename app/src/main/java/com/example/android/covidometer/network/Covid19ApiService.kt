

package com.example.android.covidometer.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.covid19api.com/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface CovidApiService {
    @GET("total/country/{country}")
    fun getProperties(@Path("country") country : String, @Query("from") from_dt : String, @Query("to") to_dt : String):
            Deferred<List<Covid19Record>>
}

object CovidApi {
    val retrofitService : CovidApiService by lazy {
        retrofit.create(CovidApiService::class.java)
    }


}