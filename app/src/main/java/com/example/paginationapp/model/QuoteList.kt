package com.example.paginationapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class QuoteList (
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)
