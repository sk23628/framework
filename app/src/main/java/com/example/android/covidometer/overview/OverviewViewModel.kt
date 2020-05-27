
package com.example.android.covidometer.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.covidometer.network.Covid19Record
import com.example.android.covidometer.network.CovidApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


import java.lang.Exception

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */

enum class CovidApiStatus { LOADING, ERROR, DONE }


class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<CovidApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<CovidApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Covid19Record>>()
    val properties : LiveData<List<Covid19Record>>
        get() = _properties

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main)


//    init {
//        getMarsRealEstateProperties()
//    }

    fun getMarsRealEstateProperties(from_date : String, to_date : String, country : String) {

        coroutineScope.launch {
            var getPropertiesDeferred = CovidApi.retrofitService.getProperties(country, from_date, to_date)

            try {
                _status.value = CovidApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _status.value = CovidApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = CovidApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }



//        MarsApi.retrofitService.getProperties().enqueue(
//            object : Callback<List<MarsProperty>> {
//                override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
//                    _response.value = "Failure"+ t.message
//                }
//
//                override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
//                    _response.value = "${response.body()?.size} Mars properties retrieved"
//                }
//
//            }
//        )
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

