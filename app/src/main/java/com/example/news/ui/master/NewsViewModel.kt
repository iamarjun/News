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
import com.bumptech.glide.load.HttpException
import com.example.news.RestApi
import com.example.news.model.Article
import com.example.news.model.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class NewsViewModel @ViewModelInject constructor(private val restApi: RestApi) : ViewModel() {

    private val _sourceList = arrayListOf<Source>()

    val sourceList: List<Source>
        get() = _sourceList

    init {

        viewModelScope.launch {
            try {
                val response = restApi.getSources()
                _sourceList.addAll(response.sources)
            } catch (exception: IOException) {
                Timber.e(exception)
            } catch (exception: HttpException) {
                Timber.e(exception)
            }
        }
    }

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

    private val _query by lazy { MutableLiveData<String>() }
    val query: LiveData<String>
        get() = _query

    fun setQuery(query: String) {
        _query.value = query
    }

    fun getNewsStream(
        country: String? = null,
        sources: String? = null,
        query: String? = null
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(restApi, country, sources, query) }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}