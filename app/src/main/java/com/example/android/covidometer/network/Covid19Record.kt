
package com.example.android.covidometer.network

/*        val id : String,
        val type : String,
        val price : Double,
        @Json(name = "img_src") val imgSrcUrl : String*/

data class Covid19Record(

    val Confirmed : Int,
    val Deaths : Int,
    val Recovered : Int,
    val Active : Int,
    val Country : String,
    val Date : String
)
