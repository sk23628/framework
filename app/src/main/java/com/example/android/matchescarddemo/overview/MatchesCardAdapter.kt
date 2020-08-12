package com.example.android.matchescarddemo.overview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.matchescarddemo.data.RandomUser
import com.example.android.matchescarddemo.data.Result
import com.example.android.matchescarddemo.databinding.MatchesCardViewItemBinding


class MatchesCardAdapter(val matchesCardInterface: MatchesCardInterface) : ListAdapter<Result,
        MatchesCardAdapter.MatchesCardViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.userId == newItem.userId
        }
    }


    class MatchesCardViewHolder private constructor(private var binding:
                                MatchesCardViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {

        val textAccept: TextView = binding.textView
        val textDecline: TextView = binding.textView2

        fun bind(randomUser: Result) {

            if(randomUser.userStatus != "available"){
                if(randomUser.userStatus == "Member Accepted"){
                    binding.textView.text = "Member Accepted"
                    binding.textView2.visibility = View.GONE
                } else if(randomUser.userStatus == "Member Declined"){
                    binding.textView2.text = "Member Declined"
                    binding.textView.visibility = View.GONE
                }
            }

            binding.randomUser = randomUser
//
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): MatchesCardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MatchesCardViewItemBinding.inflate(layoutInflater, parent, false)
                return MatchesCardViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesCardViewHolder {
//        var binding = MatchesCardViewItemBinding.inflate(LayoutInflater.from(parent.context))
//        return MatchesCardViewHolder(binding)
        return MatchesCardViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: MatchesCardViewHolder, position: Int) {
        var randomUserObj = getItem(position)
        holder.bind(randomUserObj)

        holder.textAccept.setOnClickListener {
            if(holder.textAccept.text == "Accept") {
                holder.textAccept.text = "Member Accepted"
                holder.textAccept.visibility = View.GONE
                val userStatus = "Member Accepted"
                matchesCardInterface.updateUser(randomUserObj.userId, randomUserObj.email, userStatus)
            } else {
                matchesCardInterface.updateUser(randomUserObj.userId, randomUserObj.email, "available")
            }
        }

        holder.textDecline.setOnClickListener {
            if(holder.textDecline.text == "Decline") {
                holder.textDecline.text = "Member Declined"
                holder.textDecline.visibility = View.GONE
                val userStatus = "Member Declined"
                matchesCardInterface.updateUser(randomUserObj.userId, randomUserObj.email, userStatus)
            } else {
                matchesCardInterface.updateUser(randomUserObj.userId, randomUserObj.email, "available")
            }
        }

    }

}


