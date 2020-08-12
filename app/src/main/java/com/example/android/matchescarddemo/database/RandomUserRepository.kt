package com.example.android.matchescarddemo.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.matchescarddemo.data.Result

class RandomUserRepository(private val randomUserActionDAO: RandomUserActionDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<MutableList<Result>> = randomUserActionDAO.getRandomUsers()

    suspend fun insert(results: MutableList<Result>) {
        randomUserActionDAO.insertAll(results)
    }

    suspend fun update(userId: Int, userEmail: String, userStatus: String){
        randomUserActionDAO.updateStatus(userId, userEmail, userStatus)
    }
}