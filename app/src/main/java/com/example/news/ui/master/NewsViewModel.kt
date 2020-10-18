package com.example.news.ui.master

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.RestApi
import com.example.news.model.Article
import com.example.news.model.Source
import kotlinx.coroutines.flow.Flow

class NewsViewModel @ViewModelInject constructor(private val restApi: RestApi) : ViewModel() {

    private val _country by lazy { MutableLiveData<String>() }
    val country: LiveData<String>
        get() = _country

    fun setCountry(country: String) {
        _country.value = country
    }

    private val _sources by lazy { MutableLiveData<String>() }
    val sources: LiveData<String>
        get() = _sources

    fun setSources(sources: String) {
        _sources.value = sources
    }

    fun getNewsStream(country: String? = null, sources: String? = null): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(restApi, country, sources) }
        ).flow.cachedIn(viewModelScope)
    }

    val getSource: LiveData<List<Source>>
        get() = liveData {
            val response = restApi.getSources()
            emit(response.sources)
        }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}