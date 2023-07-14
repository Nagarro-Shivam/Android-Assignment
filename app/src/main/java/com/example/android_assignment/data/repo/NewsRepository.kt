package com.example.android_assignment.data.repo

import com.example.android_assignment.data.model.News
import com.example.android_assignment.data.model.NewsDTO
import com.example.android_assignment.data.remote.NewsService
import com.example.android_assignment.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api :  NewsService
){

    fun getEmailedNews() = flow<NetworkResult<List<News>>> {

        emit(NetworkResult.Loading())

        try {

            val response = api.getEmailedArticles()
            if (response.isSuccessful && response.body() != null) {

                response.body()?.let {
                val newsList =     it.results.map { newDto ->
                        newDto.toNews()
                    }

                    emit(NetworkResult.Success(newsList))

                } ?: emit(NetworkResult.Error("Something Went Wrong"))
            }
        }

        catch (e : Exception) {
            e.printStackTrace()
            emit(NetworkResult.Error(e.localizedMessage, null))
        }

    }

    fun getSharedNews() = flow<NetworkResult<List<News>>>{

        emit(NetworkResult.Loading())

        try {

            val response = api.getSharedArticles()
            if (response.isSuccessful && response.body() != null) {

                response.body()?.let {
                    val newsList =     it.results.map { newDto ->
                        newDto.toNews()
                    }

                    emit(NetworkResult.Success(newsList))

                } ?: emit(NetworkResult.Error("Something Went Wrong"))
            }

        }catch (e : Exception) {
            e.printStackTrace()
            emit(NetworkResult.Error(e.localizedMessage, null))
        }
    }

    fun getViewedNews() = flow<NetworkResult<List<News>>>{

        emit(NetworkResult.Loading())

        try {

            val response = api.getViewedArticles()
            if (response.isSuccessful && response.body() != null) {

                response.body()?.let {
                    val newsList =     it.results.map { newDto ->
                        newDto.toNews()
                    }

                    emit(NetworkResult.Success(newsList))

                } ?: emit(NetworkResult.Error("Something Went Wrong"))
            }

        }catch (e : Exception) {
            e.printStackTrace()
            emit(NetworkResult.Error(e.localizedMessage, null))
        }
    }
}