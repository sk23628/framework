package com.example.android.matchescarddemo.detailedprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.matchescarddemo.R

class DetailedProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_profile)
    }

    override fun onBackPressed() {
        finish()
    }
}