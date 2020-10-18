package com.example.news.ui.newsSource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.FragmentSourceListDialogListDialogItemBinding
import com.example.news.model.Source

class SourceAdapter internal constructor(
    private val sources: List<Source>,
    private val interaction: Interaction? = null
) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    internal var selected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentSourceListDialogListDialogItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), interaction
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = sources[position]
        holder.bind(source)
    }

    override fun getItemCount(): Int {
        return sources.size
    }

    inner class ViewHolder internal constructor(
        private val binding: FragmentSourceListDialogListDialogItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(source: Source) {
            binding.apply {
                selection.apply {
                    isChecked = source.isSelected
                    text = source.name
                    setTextColor(
                        if (source.isSelected) ContextCompat.getColor(
                            itemView.context,
                            R.color.blue_800
                        ) else ContextCompat.getColor(itemView.context, R.color.black)
                    )
                    setOnClickListener {
                        source.isSelected = !source.isSelected
                        notifyDataSetChanged()
                        interaction?.interact(source)
                    }
                }
            }
        }
    }

    interface Interaction {
        fun interact(source: Source)
    }
}