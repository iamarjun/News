package com.example.news

import android.os.Bundle
import androidx.activity.viewModels
import com.example.news.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: SectionsPagerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

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