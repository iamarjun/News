package com.example.news.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
//import com.example.news.dat/abinding.ArticleItemBinding
import com.example.news.model.Article
import com.example.news.util.BindingUtils
import com.example.news.util.GlideApp

//class NewsListAdapter(private val articles: List<Article?>, private val listener: OnNewsClickListener?) :
//    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
//
//    interface OnNewsClickListener {
//        fun onNewsClick(article: Article?)
//    }
//
//    private lateinit var binding: ArticleItemBinding
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding.root)
//    }
//
//    override fun getItemCount(): Int = articles.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val article = articles[position]
//
//        GlideApp.with(holder.itemView)
//            .load(article?.urlToImage)
//            .centerCrop()
//            .placeholder(R.drawable.ic_news_dark)
//            .into(holder.thumbnail)
//
//        holder.headline.text = article?.title
//
//        val date = BindingUtils.stringToDate(article?.publishedAt)?.time
//
//        date?.let {
//            holder.timestamp.text = "${BindingUtils.getElapsedTime(it)} ago"
//        }
//
//        holder.itemView.setOnClickListener {
//            listener?.onNewsClick(article)
//        }
//    }
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val thumbnail: ImageView = binding.thumbnail
//        val headline: TextView = binding.headlines
//        val timestamp: TextView = binding.timestamp
//    }
//}