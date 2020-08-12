package com.example.android.matchescarddemo.data


import com.squareup.moshi.Json

data class Coordinates(
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String
)