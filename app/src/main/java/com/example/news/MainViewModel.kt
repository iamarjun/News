package com.example.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.news.model.NewsSource
import com.momentsnap.android.Event
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val restApi: RestApi) : BaseViewModel() {

    val newsSource: LiveData<Event<NewsSource>> = liveData(Dispatchers.IO) {
        val data = Event(restApi.getSources(category.title, "us", "en", 10))
                emit(data)
    }

//    fun getNewsSource() {
//        compositeDisposable.add(
//            restApi.getSources(category.title, "us", "en", 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { newsSource.postValue(Event(it)) },
//                    { error.postValue(Event(it.localizedMessage)) }
//                )
//        )
//
//    }
}