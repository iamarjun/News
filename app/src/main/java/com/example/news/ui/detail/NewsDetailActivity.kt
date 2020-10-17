package com.example.news.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.news.R
import com.example.news.databinding.ActivityNewsBinding
import com.example.news.model.Article
import com.example.news.ui.base.BaseActivity
import com.example.news.util.BindingUtils
import com.example.news.util.GlideApp
import com.example.news.util.viewBinding

class NewsDetailActivity : BaseActivity() {

    private val binding: ActivityNewsBinding by viewBinding(ActivityNewsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent?.getParcelableExtra<Article>(NEWS_ARTICLE)?.let { article ->
            binding.apply {
                title.text = article.title
                newsSource.text = article.title
                description.text = article.content
                timestamp.text = BindingUtils.stringToDate(article.publishedAt)?.toString()
                GlideApp.with(this@NewsDetailActivity)
                    .load(article.urlToImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_news_dark)
                    .into(newImage)
                seeFullStory.setOnClickListener {
                    startActivity(
                        NewsFullStoryActivity.getIntent(
                            this@NewsDetailActivity,
                            article.url
                        )
                    )
                }

            }
        }
    }

    companion object {
        private const val NEWS_ARTICLE = "NEWS_ARTICLE"

        fun getIntent(context: Context, article: Article?): Intent =
            Intent(context, NewsDetailActivity::class.java).also {
                it.putExtra(NEWS_ARTICLE, article)
            }

    }
}