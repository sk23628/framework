package com.example.android.matchescarddemo.data


import com.squareup.moshi.Json

data class Location(
    @Json(name = "city")
    val city: String
){
//    @Json(name = "coordinates")
//    val coordinates: Coordinates,
//    @Json(name = "country")
//    val country: String,
//    @Json(name = "postcode")
//    val postcode: String,
//    @Json(name = "state")
//    val state: String,
//    @Json(name = "street")
//    val street: Street,
//    @Json(name = "timezone")
//    val timezone: Timezone
}