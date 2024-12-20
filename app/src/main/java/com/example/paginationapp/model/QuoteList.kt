package com.example.paginationapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class QuoteList (
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
)
@Entity(tableName = "Quote")
data class Quote(
    @PrimaryKey(autoGenerate = true) val tbId: Int = 0,
    val author: String,
    val id: Int,
    val quote: String
)

