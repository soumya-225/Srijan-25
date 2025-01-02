package com.iitism.srijan25.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AnnouncementRetrofitInstance {
    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://srijan2024.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val announcementApi: AnnouncementApi = retrofit.create(AnnouncementApi::class.java)
}