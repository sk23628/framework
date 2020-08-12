package com.example.android.matchescarddemo.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.matchescarddemo.databinding.GridViewItemBinding
import com.example.android.matchescarddemo.network.Covid19Record
import java.text.SimpleDateFormat
import java.util.*


class DailyNumbersAdapter : ListAdapter<Covid19Record,
        DailyNumbersAdapter.CovidPropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Covid19Record>() {
        override fun areItemsTheSame(oldItem: Covid19Record, newItem: Covid19Record): Boolean {
            return oldItem === newItem

        }

        override fun areContentsTheSame(oldItem: Covid19Record, newItem: Covid19Record): Boolean {
            return oldItem.Date == newItem.Date

        }
    }

    class CovidPropertyViewHolder(private var binding:
                                 GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(covid19Record: Covid19Record, covid19RecordNext : Covid19Record) {


            val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.US)
            val formattedDate = formatter.format(parser.parse(covid19Record.Date))

            binding.dateTxt.text = formattedDate+", Confirmed Cases : "+covid19Record.Confirmed

            if(covid19Record.Confirmed > covid19RecordNext.Confirmed){
                val casesDifference = covid19Record.Confirmed - covid19RecordNext.Confirmed
                binding.increaseTxt.text = "Increased by $casesDifference"
            }
            binding.property = covid19Record

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidPropertyViewHolder {
        var binding = GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return CovidPropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CovidPropertyViewHolder, position: Int) {
        val covidProperty = getItem(position)
        if(position < itemCount - 1) {
            val covidPropertyNext = getItem(position + 1)
            holder.bind(covidProperty, covidPropertyNext)
        }


    }

}


