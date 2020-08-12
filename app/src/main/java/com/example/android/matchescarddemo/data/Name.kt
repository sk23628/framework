package com.example.android.matchescarddemo.data


import com.squareup.moshi.Json

data class Name(
    @Json(name = "first")
    val first: String,
    @Json(name = "last")
    val last: String,
    @Json(name = "title")
    val title: String
)