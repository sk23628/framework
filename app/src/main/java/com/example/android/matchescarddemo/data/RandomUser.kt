package com.example.android.matchescarddemo.data


import androidx.room.Entity
import com.squareup.moshi.Json

data class RandomUser(


    @Json(name = "info")
    val info: Info,

    @Json(name = "results")
    val results: MutableList<Result>
)