package com.example.news.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.news.BaseViewModel
import com.example.news.RestApi
import com.example.news.model.Article
import com.example.news.model.Source
import com.momentsnap.android.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PageViewModel @Inject constructor(private val restApi: RestApi) : BaseViewModel() {

    private val _index by lazy { MutableLiveData<Event<Int>>() }
    val articles by lazy { MutableLiveData<Event<List<Article?>?>>() }

    val text: LiveData<Event<String>> = Transformations.map(_index) {
        Event("Hello world from section: ${it.peekContent()}")
    }

    fun setIndex(index: Int) {
        _index.value = Event(index)
    }

    fun setSource(source: Source) {
        compositeDisposable.add(
            restApi.getNews(source.id, category.title, "us", "en", 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { articles.postValue(Event(it?.articles)) },
                    { error.postValue(Event(it.localizedMessage)) }
                )
        )

    }
}