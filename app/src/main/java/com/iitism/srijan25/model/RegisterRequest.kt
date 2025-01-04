package com.iitism.srijan25.model

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val isISM: Boolean,
    val college: String,
    val refCode: String?,
)
