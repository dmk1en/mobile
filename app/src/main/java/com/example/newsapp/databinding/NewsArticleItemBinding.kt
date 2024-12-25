package com.example.newsapp.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsapp.R

class NewsArticleItemBinding private constructor(val root: View) {
    var newsTitle: TextView = root.findViewById(R.id.news_title)
    var newsDescription: TextView = root.findViewById(R.id.news_description)
    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean): NewsArticleItemBinding {
            val view = inflater.inflate(R.layout.new_article_items, parent, attachToParent)
            return NewsArticleItemBinding(view)
        }
    }
}
