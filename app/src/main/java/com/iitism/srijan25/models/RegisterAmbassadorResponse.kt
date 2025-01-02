package com.iitism.srijan25.models

data class RegisterAmbassadorResponse(
    val success: Boolean,
    val userdata: User,
    val refCode: AmbassadorData
)
