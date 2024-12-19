package com.example.paginationapp.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.paginationapp.Retrofit.QuoteApi
import com.example.paginationapp.db.QuotesDatabase
import javax.inject.Inject

@ExperimentalPagingApi
class QuoteRepository @Inject constructor(
    private val quotesAPI: QuoteApi,
    private val quoteDatabase: QuotesDatabase
) {

    fun getQuotes() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        remoteMediator = QuoteRemoteMediator(quotesAPI, quoteDatabase),
        pagingSourceFactory = { quoteDatabase.quoteDao().getQuotes() }
    ).liveData




}