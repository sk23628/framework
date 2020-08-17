package com.example.android.matchescarddemo.overview

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.android.matchescarddemo.databinding.FragmentOverviewBinding
import com.facebook.drawee.backends.pipeline.Fresco
import java.text.SimpleDateFormat
import java.util.*



class OverviewFragment : Fragment(), MatchesCardInterface {

    /**
     * Lazily initialize our [OverviewViewModel].
     */

    var mContext : Context? = null

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        binding.githubLinkText.movementMethod = LinkMovementMethod.getInstance()

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        Fresco.initialize(activity);

//        val adapter = MatchesCardAdapter(this)
        val adapter = MatchesCardRecyclerViewAdapter(mContext, this)
        binding.cardRecyclerView.setHasFixedSize(true)
        binding.cardRecyclerView.adapter = adapter

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setUsers(it)
            }
        })


        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.cardRecyclerView)

        makeAPICall()

        setHasOptionsMenu(true)

        return binding.root
    }


    /**
     * Inflates the overflow menu that contains filtering options.
     */

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.overflow_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }

    fun makeAPICall(){
        viewModel.getRandomUsersForMatchesCards()
    }

    override fun updateUser(userId: Int, userEmail: String, userStatus: String) {
        viewModel.updateUser(userId, userEmail, userStatus)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }


}


