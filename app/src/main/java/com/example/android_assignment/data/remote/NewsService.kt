package com.example.android_assignment.data.remote

import com.example.android_assignment.data.model.News
import com.example.android_assignment.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("emailed/7.json")
    suspend fun getEmailedArticles() : Response<NewsResponse>

    @GET("shared/7.json")
    suspend fun getSharedArticles() : Response<NewsResponse>

    @GET("viewed/7.json")
    suspend fun getViewedArticles() : Response<NewsResponse>
}