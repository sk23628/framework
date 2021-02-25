package com.example.android.matchescarddemo.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.android.matchescarddemo.R
import com.example.android.matchescarddemo.data.Result
import com.facebook.drawee.view.SimpleDraweeView

class MatchesCardRecyclerViewAdapter internal constructor(
        context: Context?, matchesCardInterface: MatchesCardInterface
) : RecyclerView.Adapter<MatchesCardRecyclerViewAdapter.RandomUserViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var mResults = emptyList<Result>() // Cached copy of results
    private var mMatchesCardInterface = matchesCardInterface

    inner class RandomUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val user_name: TextView = itemView.findViewById(R.id.user_name)
//        val user_age: TextView = itemView.findViewById(R.id.user_age)
        val mSimpleDraweeView: SimpleDraweeView = itemView.findViewById(R.id.image_view_profile_pic)
        val profile_item_layout : ConstraintLayout = itemView.findViewById(R.id.profile_item_layout)
//        val txtAccept: TextView = itemView.findViewById(R.id.textView)
//        val txtDecline: TextView = itemView.findViewById(R.id.textView2)
//        val txtCity: TextView = itemView.findViewById(R.id.user_location)

        fun bind(randomUser : Result){
           /* if(randomUser.userStatus != "available"){
                if(randomUser.userStatus == "Member Accepted"){
                    txtAccept.text = "Member Accepted"
                    txtDecline.visibility = View.GONE
                } else if(randomUser.userStatus == "Member Declined"){
                    txtDecline.text = "Member Declined"
                    txtAccept.visibility = View.GONE
                }
            } else if(randomUser.userStatus == "available"){
                txtAccept.visibility = View.VISIBLE
                txtAccept.text = "Accept"
                txtDecline.visibility = View.VISIBLE
                txtDecline.text = "Decline"
            } */
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHolder {
        val itemView = inflater.inflate(R.layout.profile_user_item, parent, false)
        return RandomUserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RandomUserViewHolder, position: Int) {
        val current = mResults[position]
        holder.bind(current)
        holder.user_name.text = current.name.first + " " +current.name.last
//        holder.user_age.text = current.dob.age.toString()+" Yrs."
        holder.mSimpleDraweeView.setImageURI(current.picture.large)

        holder.profile_item_layout.setOnClickListener {
            mMatchesCardInterface.showProfileDetails(current)
        }

//        holder.txtCity.text = current.location.city

/*        holder.txtAccept.setOnClickListener {
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
        }*/
    }

    internal fun setUsers(users: List<Result>) {
        this.mResults = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = mResults.size
}



