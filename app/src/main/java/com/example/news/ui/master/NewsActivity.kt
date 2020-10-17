package com.example.news.ui.master

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.example.news.model.Article
import com.example.news.ui.base.BaseActivity
import com.example.news.ui.detail.NewsDetailActivity
import com.example.news.util.EqualSpacingItemDecoration
import com.example.news.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(object : NewsAdapter.OnNewsClickListener {
            override fun onNewsClick(article: Article?) {
                startActivity(NewsDetailActivity.getIntent(this@NewsActivity, article))
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.newsList.apply {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            addItemDecoration(EqualSpacingItemDecoration(32))
            adapter = newsAdapter
        }

        lifecycleScope.launch {
            viewModel.getNewsStream("in").collectLatest {
                newsAdapter.submitData(it)
            }
        }

    }
}