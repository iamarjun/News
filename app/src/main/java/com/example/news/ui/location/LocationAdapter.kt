package com.example.news.ui.location

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.FragmentLocationDialogListDialogItemBinding
import com.example.news.model.Country

class LocationAdapter internal constructor(
    private val countries: List<Country>,
    private val interaction: Interaction? = null
) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    var selected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentLocationDialogListDialogItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        country.isSelected = selected == position
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class ViewHolder internal constructor(
        private val binding: FragmentLocationDialogListDialogItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("RestrictedApi")
        fun bind(country: Country) {
            binding.apply {
                selection.apply {
                    isChecked = country.isSelected
                    text = country.name
                    setTextColor(
                        if (country.isSelected) ContextCompat.getColor(
                            itemView.context,
                            R.color.colorPrimary
                        ) else ContextCompat.getColor(itemView.context, R.color.black)
                    )
                    setOnClickListener {
                        selected = absoluteAdapterPosition
                        notifyDataSetChanged()
                        interaction?.interact(country)
                    }
                }
            }
        }
    }


    interface Interaction {
        fun interact(country: Country)
    }
}