package com.example.newsapp.retrofit




import com.example.newsapp.model.NewsArticle


/**
 * Fetch all the latest news article from Google news
 *
 * @author Akshay Chordiya
 * @since 10/09/2017
 */
interface NewsArticleService {

    /**
     * Retrieves all the latest news article from Google news
     */
    fun getAllNewsArticles()
}