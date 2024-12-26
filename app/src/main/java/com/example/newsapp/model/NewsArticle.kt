package com.example.newsapp.model

data class NewsArticle(
    val title: String,
    val description: String,
    val content: String
)

data class NewsResponse(
    val articles: List<NewsArticle>
)