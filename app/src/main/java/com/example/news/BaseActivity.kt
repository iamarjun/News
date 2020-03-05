package com.example.news

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.news.di.controller.ControllerComponent
import com.example.news.di.controller.ControllerModule
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    val controllerComponent: ControllerComponent by lazy {
        (application as MyApplication).applicationComponent
            .controllerComponent(ControllerModule(this))
    }

}