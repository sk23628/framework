package com.example.android.matchescarddemo.overview

import com.example.android.matchescarddemo.data.Result

interface MatchesCardInterface {

    fun updateUser(userId: Int, userEmail: String, userStatus: String)
    fun showProfileDetails(result: Result)
}