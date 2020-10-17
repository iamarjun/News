package com.example.news

import com.example.news.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String?,
        @Query("pageSize") pageSize: Int?
    ): NewsResponse

}