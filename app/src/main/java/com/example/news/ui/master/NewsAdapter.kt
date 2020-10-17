package com.example.news.ui.master

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.NewsItemBinding
import com.example.news.model.Article
import com.example.news.util.BindingUtils.getElapsedTime
import com.example.news.util.BindingUtils.stringToDate
import com.example.news.util.GlideApp

class NewsAdapter(private val interaction: OnNewsClickListener? = null) :
    PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(differCallback) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(
                    parent.context,
                ),
                parent,
                false
            ),
            interaction
        )
    }

    inner class NewsViewHolder(
        private val binding: NewsItemBinding,
        private val interaction: OnNewsClickListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article?) {

            binding.apply {
                headlines.text = item?.title
                description.text = item?.content
                timestamp.text = getElapsedTime(stringToDate(item?.publishedAt)?.time!!)
                GlideApp.with(itemView)
                    .load(item?.urlToImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_news_dark)
                    .into(thumbnail)
                root.setOnClickListener {
                    interaction?.onNewsClick(item)
                }
            }
        }

    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Article>() {

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.publishedAt == newItem.publishedAt
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnNewsClickListener {
        fun onNewsClick(article: Article?)
    }
}