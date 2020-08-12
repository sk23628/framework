package com.example.android.matchescarddemo

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.matchescarddemo.data.RandomUser
import com.example.android.matchescarddemo.data.Result
import com.example.android.matchescarddemo.overview.MatchesCardAdapter
import com.example.android.matchescarddemo.overview.RandomUserApiStatus
import com.facebook.drawee.view.SimpleDraweeView
import com.squareup.picasso.Picasso

@BindingAdapter("listDate")
fun bindRecyclerView(recyclerView : RecyclerView, data : List<Result>?){
    val adapter = recyclerView.adapter as MatchesCardAdapter
//    adapter.submitList(data?.reversed())
    adapter.submitList(data)
}

@BindingAdapter("image")
fun loadImage(view: SimpleDraweeView, url: String) {
    view.setImageURI(url)
//    Picasso.get().load(url).into(view);
}


@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: RandomUserApiStatus?) {

    when (status) {
        RandomUserApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        RandomUserApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        RandomUserApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }


}