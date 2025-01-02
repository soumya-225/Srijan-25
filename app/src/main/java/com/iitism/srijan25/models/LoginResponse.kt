package com.iitism.srijan25.models


data class LoginResponse(
    val success: Boolean? = null,
    val user: User? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
