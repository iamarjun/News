package com.example.news.ui.master

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.example.news.RestApi
import com.example.news.model.Article
import java.io.IOException

private const val PAGE = 1

class NewsPagingSource(
    private val restApi: RestApi,
    private val country: String? = null,
    private val sources: String? = null
) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: PAGE

        return try {
            val response = when {
                country != null -> restApi.getNewsFromCountry(country, page)
                sources != null -> restApi.getNewsFromSources(sources, page)
                else -> throw IllegalStateException()
            }

            val articles = response.articles
            LoadResult.Page(
                data = articles,
                prevKey = if (page == PAGE) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}