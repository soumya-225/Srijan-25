package com.iitism.srijan25.api

import com.iitism.srijan25.model.Announcement
import retrofit2.Call
import retrofit2.http.GET

interface AnnouncementService {
    @GET("/api/announcements")
    fun getAnnouncements(): Call<List<Announcement>>
}