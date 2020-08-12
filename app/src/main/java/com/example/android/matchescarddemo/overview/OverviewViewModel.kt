
package com.example.android.matchescarddemo.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.matchescarddemo.data.RandomUser
import com.example.android.matchescarddemo.data.Result
import com.example.android.matchescarddemo.database.RandomUserDatabase
import com.example.android.matchescarddemo.database.RandomUserRepository
import com.example.android.matchescarddemo.network.RandomUserApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */

enum class RandomUserApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<RandomUserApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<RandomUserApiStatus>
        get() = _status

//    private val _users = LiveData<MutableList<Result>>()
    var allUsers : LiveData<MutableList<Result>>
//        get() = _users

    private val _response = MutableLiveData<RandomUser>()
    val responseVal : LiveData<RandomUser>
        get() = _response

    private val randomUserRepository: RandomUserRepository

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main)
    private val coroutineScopeRoom = CoroutineScope(viewModelJob + Dispatchers.IO)


    init {
        val randomUserDao = RandomUserDatabase.getDatabase(application, coroutineScopeRoom).randomUserDao()
        randomUserRepository = RandomUserRepository(randomUserDao)
        allUsers = randomUserRepository.allUsers

    }


    fun getRandomUsersForMatchesCards() {

        coroutineScope.launch {
            var getUsersDeferred = RandomUserApi.retrofitService.getUsers(10)

            try {
                _status.value = RandomUserApiStatus.LOADING
                var Result = getUsersDeferred.await()
                _status.value = RandomUserApiStatus.DONE

                _response.value = Result
                insertData(responseVal)

            } catch (e: Exception) {
                _status.value = RandomUserApiStatus.ERROR

            }
        }
    }

    fun insertData(responseValue: LiveData<RandomUser>) = coroutineScopeRoom.launch {
        responseValue.value?.results?.let { randomUserRepository.insert(it) }
    }

    fun updateUser(userId: Int, userEmail: String, userStatus: String) = coroutineScopeRoom.launch {
       randomUserRepository.update(userId, userEmail, userStatus)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

