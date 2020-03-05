package com.example.news.di.app

import com.example.news.di.controller.ControllerComponent
import com.example.news.di.controller.ControllerModule
import com.example.news.di.network.NetworkModule
import com.example.news.di.viewModel.ViewModelModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun controllerComponent(controllerModule: ControllerModule): ControllerComponent
}