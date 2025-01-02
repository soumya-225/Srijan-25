package com.iitism.srijan25.models

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val isISM: Boolean,
    val college: String,
    val refCode: String?,
)
