package com.iitism.srijan25.services

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.io.File

interface GoogleFormApi {
    @FormUrlEncoded
    @POST("/forms/d/e/1FAIpQLSdzeskAtAzjFL5v5sU3O_DNr9EodeddQI5OhAC1tb-7cfbOig/formResponse")
    fun sendFormData(
        @Field("entry.717387633") name: String,
        @Field("entry.1989037605") emailId: String,
        @Field("entry.1483577754") admissionNumber: String,
        @Field("entry.1401482088") hostel: String,
        @Field("entry.1690578168") roomNumber: String,
        @Field("entry.2113007457") contactNumber: String,
        @Field("entry.887676747") size: String,
        @Field("entry.699952840") image: File?
    ): Call<Void>
}