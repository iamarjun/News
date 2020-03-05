package com.example.news

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.news.databinding.ActivityMainBinding
import com.example.news.util.showToast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.momentsnap.android.EventObserver

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: SectionsPagerAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        controllerComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewPager = binding.viewPager
        tabs = binding.tabs
        fab = binding.fab

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java).apply {
            getNewsSource()
        }

        viewModel.newsSource.observe(this, EventObserver { newSource ->
            newSource?.let {
                it.sources?.let {
                    adapter = SectionsPagerAdapter(
                        it,
                        this,
                        supportFragmentManager
                    )
                    viewPager.adapter = adapter
                    tabs.setupWithViewPager(viewPager)
                }
            }
        })

        viewModel.error.observe(this, EventObserver {
            showToast(it)
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}