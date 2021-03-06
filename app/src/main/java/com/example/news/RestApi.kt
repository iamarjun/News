package com.example.news

import com.example.news.model.NewsList
import com.example.news.model.NewsSource
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface RestApi {

    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("sources")
    fun getSources(
        @Query("category") category: String?,
        @Query("country") country: String?,
        @Query("language") language: String?,
        @Query("pageSize") pageSize: Int?
    ): Observable<NewsSource?>

    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("top-headlines")
    fun getNews(
        @Query("source") source: String?,
        @Query("category") category: String?,
        @Query("country") country: String?,
        @Query("language") language: String?,
        @Query("pageSize") pageSize: Int?
    ): Observable<NewsList?>

}