package com.example.news

import android.app.Application
import com.example.news.di.app.ApplicationComponent
import com.example.news.di.app.ApplicationModule
import com.example.news.di.app.DaggerApplicationComponent

class MyApplication: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .build()
    }

}