package com.iitism.srijan25.model

import com.google.gson.annotations.SerializedName

data class SponsorData (
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("tier") val tier: String
)