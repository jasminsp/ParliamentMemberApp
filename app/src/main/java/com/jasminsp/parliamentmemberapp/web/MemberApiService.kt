package com.jasminsp.parliamentmemberapp.web

import androidx.lifecycle.LiveData
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

// create an instance of Moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// create an instance of Retrofit and pass an instance of MoshiConverter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

    // Gets the memberData from API to MemberApi service
    interface MemberApiService {
        @GET("mps.json") // end of API
        suspend fun getMemberRecords(): List<ParliamentData>
    }

//Initializing the Api service
object MemberApi {
    val retrofitService: MemberApiService by lazy {
        retrofit.create(MemberApiService::class.java)
    }
}