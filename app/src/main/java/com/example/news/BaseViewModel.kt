package com.example.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.model.Category
import com.momentsnap.android.Event
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel() {

    val category: Category = Category.randomCategory()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val error: MutableLiveData<Event<String>> by lazy { MutableLiveData<Event<String>>() }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}
