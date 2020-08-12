package com.example.android.matchescarddemo.data


import com.squareup.moshi.Json

data class Street(
    @Json(name = "name")
    val name: String,
    @Json(name = "number")
    val number: Int
)