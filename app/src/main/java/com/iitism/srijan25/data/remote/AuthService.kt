package com.iitism.srijan25.data.remote

import com.iitism.srijan25.model.LeaderboardResponse
import com.iitism.srijan25.model.LoginRequest
import com.iitism.srijan25.model.LoginResponse
import com.iitism.srijan25.model.OtpVerificationRequest
import com.iitism.srijan25.model.OtpVerificationResponse
import com.iitism.srijan25.model.RegisterAmbassadorRequest
import com.iitism.srijan25.model.RegisterAmbassadorResponse
import com.iitism.srijan25.model.RegisterRequest
import com.iitism.srijan25.model.RegisterResponse
import com.iitism.srijan25.model.ResendOtpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("api/register")
    fun register(
        @Body request: RegisterRequest,
        @Query("source") source: String = "mobile"
    ): Call<RegisterResponse>

    @POST("api/verifyotp-mobile")
    fun verifyOtp(
        @Body request: OtpVerificationRequest,
        @Query("source") source: String = "mobile"
    ): Call<OtpVerificationResponse>

    @POST("api/login")
    fun login(
        @Body loginRequest: LoginRequest,
        @Query("source") source: String = "mobile"
    ): Call<LoginResponse>

    @POST("api/resendotp")
    fun resendOtp(
        @Body resendOtpRequest: ResendOtpRequest,
        @Query("source") source: String = "mobile"
    ): Call<Void>

    @POST("/campus")
    fun registerAmbassador(
        @Body request: RegisterAmbassadorRequest,
        @Query("source") source: String = "mobile"
    ): Call<RegisterAmbassadorResponse>

    @GET("/leaderboard-mobile")
    fun getLeaderboard(
        @Query("source") source: String = "mobile"
    ): Call<LeaderboardResponse>

}