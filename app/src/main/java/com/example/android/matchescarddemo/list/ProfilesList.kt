package com.example.android.matchescarddemo.list

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.android.matchescarddemo.R
import com.example.android.matchescarddemo.data.Result
import com.example.android.matchescarddemo.databinding.FragmentOverviewBinding
import com.example.android.matchescarddemo.databinding.ProfilesListFragmentBinding
import com.example.android.matchescarddemo.detailedprofile.DetailedProfileActivity
import com.example.android.matchescarddemo.overview.MatchesCardInterface
import com.example.android.matchescarddemo.overview.MatchesCardRecyclerViewAdapter
import com.example.android.matchescarddemo.overview.OverviewViewModel
import com.facebook.drawee.backends.pipeline.Fresco

class ProfilesList : Fragment(), MatchesCardInterface {

    companion object {
        fun newInstance() = ProfilesList()
    }

    var mContext : Context? = null

    private val viewModel: ProfilesListViewModel by lazy {
        ViewModelProviders.of(this).get(ProfilesListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = ProfilesListFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        Fresco.initialize(activity);

        val adapter = MatchesCardRecyclerViewAdapter(mContext, this)
        binding.cardRecyclerView.setHasFixedSize(true)
        binding.cardRecyclerView.adapter = adapter

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setUsers(it)
            }
        })


//        var snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(binding.cardRecyclerView)

        makeAPICall()

        setHasOptionsMenu(true)

        return binding.root
    }

    fun makeAPICall(){
        viewModel.getRandomUsersForMatchesCards()
    }

    override fun updateUser(userId: Int, userEmail: String, userStatus: String) {
        viewModel.updateUser(userId, userEmail, userStatus)
    }

    override fun showProfileDetails(result: Result) {
        val intent = Intent(activity, DetailedProfileActivity::class.java)
        intent.putExtra("result", result.toString())
        startActivity(intent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}