package com.example.news.ui.master

import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.example.news.model.Article
import com.example.news.ui.detail.NewsDetailActivity
import com.example.news.ui.location.LocationDialogFragment
import com.example.news.ui.newsSource.SourceListDialogFragment
import com.example.news.util.BindingUtils.getCountryName
import com.example.news.util.EqualSpacingItemDecoration
import com.example.news.util.viewBinding
import com.yayandroid.locationmanager.base.LocationBaseActivity
import com.yayandroid.locationmanager.configuration.Configurations
import com.yayandroid.locationmanager.configuration.LocationConfiguration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class NewsActivity : LocationBaseActivity() {

    private val viewModel: NewsViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private val bottomSheet: LocationDialogFragment by lazy { LocationDialogFragment.newInstance() }

    private var job: Job? = null

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

        getLocation()

        binding.newsList.apply {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            addItemDecoration(EqualSpacingItemDecoration(32))
            adapter = newsAdapter
        }

        binding.sourceFab.setOnClickListener {
            SourceListDialogFragment.newInstance().show(supportFragmentManager, "dialog")
        }

        viewModel.country.observe(this) {
            job?.cancel()
            job = lifecycleScope.launch {
                viewModel.getNewsStream(country = it).collectLatest {
                    newsAdapter.submitData(it)
                }
            }
        }

        viewModel.sources.observe(this) {
            job?.cancel()
            job = lifecycleScope.launch {
                viewModel.getNewsStream(sources = it).collectLatest {
                    newsAdapter.submitData(it)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.location -> {
                bottomSheet.show(supportFragmentManager, "dialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onLocationChanged(location: Location) {
        Timber.d("$location")
        val country = getCountryName(this, location)
        Timber.d("$country")
        country?.let { viewModel.setCountry(it) }
    }

    override fun onLocationFailed(type: Int) {
        Timber.d("$type")
    }

    override fun getLocationConfiguration(): LocationConfiguration =
        Configurations.defaultConfiguration(
            "Gimme the permission!",
            "Would you mind to turn GPS on?"
        )
}