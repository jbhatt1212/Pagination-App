package com.example.paginationapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quotes")
data class Result(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val author: String,
    val content: String,
    val authorSlug: String,
    val length: Int,
    val dateAdded: String,
    val dateModified: String
)
