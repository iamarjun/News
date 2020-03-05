package com.example.news.di.controller

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class ControllerModule(private val activity: FragmentActivity) {

    @Provides
    fun context(): Context = activity

    @Provides
    fun activity(): Activity = activity

    @Provides
    fun fragmentManager(): FragmentManager = activity.supportFragmentManager

}