package com.iitism.srijan25.model

import com.google.gson.annotations.SerializedName

data class SponsorData (
    @SerializedName("img") val img: String,
    @SerializedName("link") val link: String
)