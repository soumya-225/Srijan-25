package com.iitism.srijan25.retrofit

import com.iitism.srijan25.models.Announcement
import retrofit2.Call
import retrofit2.http.GET

interface AnnouncementApi {
    @GET("/api/announcements")
    fun getAnnouncements(): Call<List<Announcement>>
}