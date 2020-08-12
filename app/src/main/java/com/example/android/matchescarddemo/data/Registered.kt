package com.example.android.matchescarddemo.data


import androidx.room.Ignore
import com.squareup.moshi.Json

data class Registered(
    @Json(name = "age")
    val age: Int,
    @Json(name = "date")
    val date: String
)