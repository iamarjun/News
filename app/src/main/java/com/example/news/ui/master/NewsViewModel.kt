package com.example.news.ui.master

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.RestApi
import com.example.news.model.Article
import kotlinx.coroutines.flow.Flow

class NewsViewModel @ViewModelInject constructor(private val restApi: RestApi) : ViewModel() {

    private val _country by lazy { MutableLiveData<String>() }
    val country: LiveData<String>
        get() = _country

    fun setCountry(country: String) {
        _country.value = country
    }

    fun getNewsStream(country: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(restApi, country) }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}