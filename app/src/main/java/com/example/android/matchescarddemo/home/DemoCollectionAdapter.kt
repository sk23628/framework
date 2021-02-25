package com.example.android.matchescarddemo.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.matchescarddemo.list.ProfilesList

class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfilesList()
            1 -> ProfilesList()
            2 -> ProfilesList()
            else -> ProfilesList()
        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}