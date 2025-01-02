package com.iitism.srijan25.models


data class ScheduleModel(
    var eventName: String,
    var eventDate: String,
    var eventTime: String,
    var eventVenue : String,
    var eventDescription: String,
    var eventPosterUrl: String
)