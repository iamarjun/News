package com.example.news

import android.app.Application
import com.yayandroid.locationmanager.LocationManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        LocationManager.enableLog(true)
    }
}