package com.example.news.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.example.news.BaseViewModel
import com.example.news.RestApi
import com.example.news.model.Article
import com.example.news.model.Source
import com.momentsnap.android.Event
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PageViewModel @Inject constructor(private val restApi: RestApi) : BaseViewModel() {

    private val _index by lazy { MutableLiveData<Event<Int>>() }
    lateinit var articles: LiveData<Event<List<Article?>?>>

    val text: LiveData<Event<String>> = Transformations.map(_index) {
        Event("Hello world from section: ${it.peekContent()}")
    }

    fun setIndex(index: Int) {
        _index.value = Event(index)
    }

    fun setSource(source: Source) {

        articles = liveData(Dispatchers.IO) {
            val data = Event(restApi.getNews(source.id, category.title, "us", "en", 10).articles)
            emit(data)
        }
    }
}