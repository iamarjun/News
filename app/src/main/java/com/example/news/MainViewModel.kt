package com.example.news

import androidx.lifecycle.MutableLiveData
import com.example.news.model.NewsSource
import com.momentsnap.android.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val restApi: RestApi) : BaseViewModel() {

    val newsSource: MutableLiveData<Event<NewsSource?>> by lazy { MutableLiveData<Event<NewsSource?>>() }

    fun getNewsSource() {
        compositeDisposable.add(
            restApi.getSources(category.title, "us", "en", 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { newsSource.postValue(Event(it)) },
                    { error.postValue(Event(it.localizedMessage)) }
                )
        )

    }
}