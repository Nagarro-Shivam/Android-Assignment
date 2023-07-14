package com.example.android_assignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android_assignment.data.model.News
import com.example.android_assignment.data.repo.NewsRepository
import com.example.android_assignment.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val newRepo :  NewsRepository
) :  ViewModel() {

    val emailedNews = newRepo.getEmailedNews().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        null
    )

    val sharedNews = newRepo.getSharedNews().stateIn(
        viewModelScope,
        SharingStarted.Lazily, null
    )

    val viewedNews = newRepo.getViewedNews().stateIn(
        viewModelScope,
        SharingStarted.Lazily, null
    )

}