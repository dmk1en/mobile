package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapter.NewsArticlesAdapter
import com.example.newsapp.model.NewsArticle
import com.example.newsapp.viewmodel.NewsViewModel
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate called")
        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.news_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NewsArticlesAdapter(emptyList()) { article ->
            Toast.makeText(this, "Clicked on: ${article.content}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter

        // Observe LiveData
        viewModel.allNews.observe(this, Observer { newsList ->
            if (newsList != null) {
                val articles = newsList.map { NewsArticle(it.title, it.description ?: "",it.content ?: "") }
                adapter.updateData(articles)
            }
        })

        // Fetch News
        val country = "us" // or dynamically set based on user input
        //val apiKey = BuildConfig.NEWS_API_KEY
        val apiKey = "4d6071a070fb4779a59e624aec9e5f7c"
        viewModel.fetchNews(country, apiKey)
    }
}
