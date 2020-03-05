package com.example.news.di.controller

import com.example.news.BaseActivity
import com.example.news.MainActivity
import com.example.news.ui.main.PlaceholderFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [ControllerModule::class]
)
interface ControllerComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: BaseActivity)
    fun inject(mainActivity: PlaceholderFragment)

}