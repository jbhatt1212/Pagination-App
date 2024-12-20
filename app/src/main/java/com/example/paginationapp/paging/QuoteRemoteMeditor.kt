package com.example.paginationapp.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paginationapp.API.ApiService
import com.example.paginationapp.db.QuoteDatabase
import com.example.paginationapp.model.Quote
import com.example.paginationapp.model.QuoteRemoteKeys

@ExperimentalPagingApi
class QuoteRemoteMediator(
    private val apiService: ApiService,
    private val quoteDatabase: QuoteDatabase
) : RemoteMediator<Int, Quote>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Quote>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    state.pages.size
                }
            }

            val response = apiService.getQuotes(skip = page * state.config.pageSize, limit = state.config.pageSize)

            quoteDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    quoteDatabase.quoteDao().deleteQuotes()
                }
                quoteDatabase.quoteDao().addQuotes(response.quotes)
            }

            MediatorResult.Success(endOfPaginationReached = response.quotes.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}
