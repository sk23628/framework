package com.example.android.matchescarddemo.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.matchescarddemo.R
import com.example.android.matchescarddemo.data.Result
import com.facebook.drawee.view.SimpleDraweeView


class MatchesCardRecyclerViewAdapter internal constructor(
        context: Context?, matchesCardInterface: MatchesCardInterface
) : RecyclerView.Adapter<MatchesCardRecyclerViewAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var mResults = emptyList<Result>() // Cached copy of words
    private var mMatchesCardInterface = matchesCardInterface

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val user_name: TextView = itemView.findViewById(R.id.user_name)
        val user_age: TextView = itemView.findViewById(R.id.user_age)
        val mSimpleDraweeView: SimpleDraweeView = itemView.findViewById(R.id.image_view_profile_pic)
        val txtAccept: TextView = itemView.findViewById(R.id.textView)
        val txtDecline: TextView = itemView.findViewById(R.id.textView2)

        fun bind(randomUser : Result){
            if(randomUser.userStatus != "available"){
                if(randomUser.userStatus == "Member Accepted"){
                    txtAccept.text = "Member Accepted"
                    txtDecline.visibility = View.GONE
                } else if(randomUser.userStatus == "Member Declined"){
                    txtDecline.text = "Member Declined"
                    txtAccept.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.matches_card_view_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = mResults[position]
        holder.bind(current)
        holder.user_name.text = current.name.first + " " +current.name.last
        holder.user_age.text = current.dob.age.toString()
        holder.mSimpleDraweeView.setImageURI(current.picture.large)

        holder.txtAccept.setOnClickListener {
            if(holder.txtAccept.text == "Accept") {
                val userStatus = "Member Accepted"
                mMatchesCardInterface.updateUser(current.userId, current.email, userStatus)
                holder.txtAccept.text = "Member Accepted"
                holder.txtDecline.visibility = View.GONE

            } else {
                val userStatus = "available"
                mMatchesCardInterface.updateUser(current.userId, current.email, "available")
                holder.txtAccept.visibility = View.VISIBLE
                holder.txtAccept.text = "Accept"
                holder.txtDecline.visibility = View.VISIBLE
                holder.txtDecline.text = "Decline"
            }
        }

        holder.txtDecline.setOnClickListener {
            if(holder.txtDecline.text == "Decline") {
                val userStatus = "Member Declined"
                mMatchesCardInterface.updateUser(current.userId, current.email, userStatus)
                holder.txtDecline.text = "Member Declined"
                holder.txtAccept.visibility = View.GONE

            } else {
                val userStatus = "available"
                mMatchesCardInterface.updateUser(current.userId, current.email, "available")
                holder.txtAccept.visibility = View.VISIBLE
                holder.txtAccept.text = "Accept"
                holder.txtDecline.visibility = View.VISIBLE
                holder.txtDecline.text = "Decline"
            }
        }
    }

    internal fun setUsers(users: List<Result>) {
        this.mResults = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = mResults.size
}



