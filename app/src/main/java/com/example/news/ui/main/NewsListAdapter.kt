package com.example.news.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.ArticleItemBinding
import com.example.news.model.Article
import com.example.news.ui.util.GlideApp

class NewsListAdapter(private val articles: List<Article?>) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private lateinit var binding: ArticleItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article = articles[position]

        GlideApp.with(holder.itemView)
            .load(article?.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.ic_news_dark)
            .into(holder.thumbnail)

        holder.headline.text = article?.title

        //holder.timestamp.text = BindingUtils.getElapsedTime(Date(article?.publishedAt).time)

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = binding.thumbnail
        val headline: TextView = binding.headlines
        val timestamp: TextView = binding.timestamp
    }
}