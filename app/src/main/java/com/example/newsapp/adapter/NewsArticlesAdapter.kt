package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsArticleItemBinding
import com.example.newsapp.model.NewsArticle

class NewsArticlesAdapter(
    private val articles: List<NewsArticle>,
    private val onClick: (NewsArticle) -> Unit
) : RecyclerView.Adapter<NewsArticlesAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // Sử dụng View Binding để inflate layout
        val binding = NewsArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position], onClick)
    }

    override fun getItemCount(): Int = articles.size

    // ViewHolder sử dụng View Binding
    class NewsViewHolder(private val binding: NewsArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: NewsArticle, onClick: (NewsArticle) -> Unit) {
            // Sử dụng binding để truy xuất và thiết lập dữ liệu vào các View
            binding.newsTitle.text = article.title
            binding.newsDescription.text = article.description
            binding.root.setOnClickListener { onClick(article) }
        }
    }
}
