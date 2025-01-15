package com.iitism.srijan25.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnnouncementRetrofitInstance {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://srijan2024.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val announcementService: AnnouncementService = retrofit.create(AnnouncementService::class.java)
}