package com.example.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class MainViewModel @ViewModelInject constructor(private val restApi: RestApi) : ViewModel() {

}