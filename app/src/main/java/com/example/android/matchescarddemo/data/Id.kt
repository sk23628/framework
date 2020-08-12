package com.example.android.matchescarddemo.data


import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class Id(
    @Json(name = "name")
    val name: String = "",

    @Json(name = "value")
    val value: Int = -1
)