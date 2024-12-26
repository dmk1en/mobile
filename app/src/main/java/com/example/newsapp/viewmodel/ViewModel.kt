package com.example.newsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.NewsRepository
import com.example.newsapp.database.News
import kotlinx.coroutines.launch


class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsRepository(application)
    val allNews: LiveData<List<News>> = repository.getAllNews()

    fun fetchNews(country: String, apiKey: String) {
        viewModelScope.launch {
            Log.d("NewsViewModel", "Fetching news for country: $country")
            repository.fetchAndStoreNews(country, apiKey)
        }
    }
}
