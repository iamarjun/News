package com.example.news.di.controller

import com.example.news.MainActivity
import com.example.news.NewsDetail
import com.example.news.ui.PlaceholderFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [ControllerModule::class]
)
interface ControllerComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: PlaceholderFragment)
    fun inject(newsDetail: NewsDetail)

}