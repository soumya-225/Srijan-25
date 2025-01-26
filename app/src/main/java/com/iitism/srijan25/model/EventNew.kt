package com.iitism.srijan25.model

import java.io.Serializable

data class EventNew(
    val name: String,
    val registerLink: String,
    val description: String,
    val rulebook: String,
    val venue: String,
    val prizePool: String,
    val timeFormatted: String,
    val posterUrl: String
) : Serializable

data class Category(
    val id: Int,
    val events: List<EventNew>
)