package com.example.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.news.databinding.ActivityMainBinding
import com.example.news.util.showToast
import com.google.android.material.tabs.TabLayout
import com.momentsnap.android.EventObserver

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: SectionsPagerAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewPager = binding.viewPager
        tabs = binding.tabs

//        viewModel.newsSource.observe(this, EventObserver {
//            it.sources?.let {
//                adapter = SectionsPagerAdapter(
//                    it,
//                    this,
//                    supportFragmentManager
//                )
//                viewPager.adapter = adapter
//                tabs.setupWithViewPager(viewPager)
//            }
//        })

    }
}