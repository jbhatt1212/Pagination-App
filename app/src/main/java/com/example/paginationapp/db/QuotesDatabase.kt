package com.example.paginationapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paginationapp.model.QuoteRemoteKeys

@Database(entities = [Result::class, QuoteRemoteKeys::class], version = 1, exportSchema = false)
abstract class QuotesDatabase :RoomDatabase(){
    abstract fun quoteDao() : QuoteDao
    abstract fun remoteKeysDao() : RemoteKeysDao
}