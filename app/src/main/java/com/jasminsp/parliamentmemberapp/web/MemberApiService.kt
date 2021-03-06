package com.jasminsp.parliamentmemberapp.web


import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: Fetching parliament information from API

// Start of API
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

//Initializing the Api service lazily
object MemberApi {
    val retrofitService: MemberApiService by lazy {
        retrofit.create(MemberApiService::class.java)
    }
}