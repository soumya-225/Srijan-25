package com.iitism.srijan25.model

data class User(
    val username: String,
    val password: String,
    val email: String,
    val college: String,
    val verified: Boolean = false,
    val contact: Long?,
    val isISM: Boolean = false,
    var isAmbassador: Boolean = false,
    val id: String
)
