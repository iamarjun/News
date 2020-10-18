package com.example.news

import com.example.news.model.NewsResponse
import com.example.news.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApi {

    @GET("sources")
    suspend fun getSources(): SourceResponse

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("page") page: Int
    ): NewsResponse

}