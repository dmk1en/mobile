package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsArticlesAdapter
import com.example.newsapp.model.NewsArticle

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.new_article_items)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val articles = listOf(
            NewsArticle("Title 1", "Description 1"),
            NewsArticle("Title 2", "Description 2"),
            NewsArticle("Title 3", "Description 3")
        )

        // Tạo Adapter
        val adapter = NewsArticlesAdapter(articles) { article ->
            // Xử lý sự kiện click vào mỗi bài viết
            Toast.makeText(this, "Clicked on: ${article.title}", Toast.LENGTH_SHORT).show()
        }

        // Thiết lập RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.news_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}