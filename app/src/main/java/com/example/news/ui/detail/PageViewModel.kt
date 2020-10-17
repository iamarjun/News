//package com.example.news.ui.detail
//
//import androidx.hilt.lifecycle.ViewModelInject
//import androidx.lifecycle.*
//import com.example.news.RestApi
//import com.example.news.model.Article
//import com.example.news.model.Source
//import com.example.news.util.Event
//import kotlinx.coroutines.Dispatchers
//
//class PageViewModel @ViewModelInject constructor(private val restApi: RestApi) : ViewModel() {
//
//    private val _index by lazy { MutableLiveData<Event<Int>>() }
//    lateinit var articles: LiveData<Event<List<Article?>?>>
//
//    val text: LiveData<Event<String>> = Transformations.map(_index) {
//        Event("Hello world from section: ${it.peekContent()}")
//    }
//
//    fun setIndex(index: Int) {
//        _index.value = Event(index)
//    }
//
//    fun setSource(source: Source) {
//
//        articles = liveData(Dispatchers.IO) {
//            val data = Event(restApi.getNews("us", 10).articles)
//            emit(data)
//        }
//    }
//}