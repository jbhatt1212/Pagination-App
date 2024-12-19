package com.example.paginationapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationapp.model.QuoteViewModel
import com.example.paginationapp.paging.QuoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalPagingApi::class)
    lateinit var quoteViewModel: QuoteViewModel

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.quoteList)
        val adapter = QuoteAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


        quoteViewModel.list.observe(this) {
            adapter.submitData(lifecycle, it)
        }

    }
}