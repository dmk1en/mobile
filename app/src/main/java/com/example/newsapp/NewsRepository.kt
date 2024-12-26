package com.example.newsapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.newsapp.database.NewsDao
import com.example.newsapp.database.NewsDatabase
import com.example.newsapp.database.News
import com.example.newsapp.service.NewsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class NewsRepository(application: Application) {
    private val newsDao: NewsDao
    private val newsApiService: NewsApiService

    init {
        val db = NewsDatabase.getDatabase(application)
        newsDao = db.newsDao()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApiService = retrofit.create(NewsApiService::class.java)

    }

    fun getAllNews(): LiveData<List<News>> {
        return newsDao.getAllNews()
    }

    suspend fun fetchAndStoreNews(country: String, apiKey: String) {
        val requestUrl = "https://newsapi.org/v2/top-headlines?country=$country&apiKey=$apiKey"
        Log.d("NewsRepository", "Request URL: $requestUrl")
        withContext(Dispatchers.IO) {
            try {
                val response = newsApiService.getTopHeadlines(country, apiKey)

                val newsList = response.articles.map { article ->
                    News(
                        title = article.title,
                        description = article.description,
                        url = article.url,
                        publishedAt = article.publishedAt,
                        content = article.content
                    )
                }
                newsDao.deleteAllNews()
                newsDao.insertNews(newsList)

                Log.d("NewsRepository", "Fetched and stored ${newsList.size} news items")

            } catch (e: Exception) {
                Log.e("NewsRepository", "Error fetching news", e)
            }
        }
    }

}