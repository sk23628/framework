package com.example.android.matchescarddemo.data

import androidx.room.*
import com.squareup.moshi.Json


@Entity(tableName = "random_user_table")
data class Result(

    @PrimaryKey(autoGenerate = true)
    var userId : Int = 0,

    @Embedded(prefix = "random_user_")
//    @Json(name = "dob")
    var dob: Dob,

    @Embedded(prefix = "random_user_")
    @Json(name = "picture")
    val picture: Picture,

    @Embedded(prefix = "random_user_")
//    @Json(name = "name")
    var name: Name,

    @ColumnInfo(name = "random_user_email")
    @Json(name = "email")
    var email: String,

    @ColumnInfo(name = "random_user_status")
    var userStatus : String = "available",

    @Embedded(prefix = "random_user_")
    @Json(name = "location")
    val location: Location


){

//    @Ignore
//    @Json(name = "cell")
//    val cell: String,


//
//    @Ignore
//    @Json(name = "gender")
//    val gender: String,
//
//    @Ignore
//    @Json(name = "id")
//    val id: Id,
//
//    @Ignore
//    @Json(name = "location")
//    val location: Location,
//
//    @Ignore
//    @Json(name = "login")
//    val login: Login,
//    @Ignore
//    @Json(name = "nat")
//    val nat: String
//
//    @Ignore
//    @Json(name = "phone")
//    val phone: String,
//

//
//    @Ignore
//    @Json(name = "registered")
//    val registered: Registered,

}