

package com.example.android.covidometer

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.covidometer.network.Covid19Record
import com.example.android.covidometer.overview.CovidApiStatus
import com.example.android.covidometer.overview.DailyNumbersAdapter


@BindingAdapter("listDate")
fun bindRecyclerView(recyclerView : RecyclerView, data : List<Covid19Record>?){
    val adapter = recyclerView.adapter as DailyNumbersAdapter

    adapter.submitList(data?.reversed())
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: CovidApiStatus?) {

    when (status) {
        CovidApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        CovidApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        CovidApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }


}