package com.example.android.matchescarddemo.data


import com.squareup.moshi.Json

data class Picture(
    @Json(name = "large")
    val large: String,
    @Json(name = "medium")
    val medium: String,
    @Json(name = "thumbnail")
    val thumbnail: String
)