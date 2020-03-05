package com.example.news

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.di.controller.ControllerComponent
import com.example.news.di.controller.ControllerModule
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory


    val controllerComponent: ControllerComponent by lazy {
        (requireActivity().application as MyApplication).applicationComponent
            .controllerComponent(ControllerModule(activity!!))
    }
}