package com.example.newsapp.model

data class NewsArticle(
    val title: String,
    val description: String
)

data class NewsResponse(
    val articles: List<NewsArticle>
)