package com.example.android.matchescarddemo.data


import com.squareup.moshi.Json

data class Timezone(
    @Json(name = "description")
    val description: String,
    @Json(name = "offset")
    val offset: String
)