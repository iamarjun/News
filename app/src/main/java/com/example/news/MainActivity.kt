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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        controllerComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewPager = binding.viewPager
        tabs = binding.tabs

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.newsSource.observe(this, EventObserver {
            it.sources?.let {
                adapter = SectionsPagerAdapter(
                    it,
                    this,
                    supportFragmentManager
                )
                viewPager.adapter = adapter
                tabs.setupWithViewPager(viewPager)
            }
        })

        viewModel.error.observe(this, EventObserver {
            showToast(it)
        })
    }
}