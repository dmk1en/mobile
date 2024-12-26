package com.example.newsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsArticleItemBinding
import com.example.newsapp.model.NewsArticle

class NewsArticlesAdapter(
    private var articles: List<NewsArticle>,
    private val onItemClick: (NewsArticle) -> Unit
) : RecyclerView.Adapter<NewsArticlesAdapter.NewsViewHolder>() {

    fun updateData(newArticles: List<NewsArticle>) {
        Log.d("NewsArticlesAdapter", "Updating data with ${newArticles.size} articles")
        articles = newArticles
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    inner class NewsViewHolder(private val binding: NewsArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsArticle: NewsArticle) {
            binding.newsTitle.text = newsArticle.title
            binding.newsDescription.text = newsArticle.description
            binding.root.setOnClickListener {
                onItemClick(newsArticle)
            }
        }
    }

}
