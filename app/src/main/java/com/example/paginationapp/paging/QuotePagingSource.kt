package com.example.paginationapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationapp.API.ApiService
import com.example.paginationapp.model.Quote


class QuotePagingSource(private val quoteApi :ApiService) :PagingSource<Int, Quote>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 0 // Start pagination from 0
        private const val LIMIT = 30 // Number of items per page
    }
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
            val position = params.key ?: STARTING_PAGE_INDEX

            return try {
                val response = quoteApi.getQuotes(
                    skip = position * LIMIT,
                    limit = LIMIT
                )


                val totalPages = (response.total + LIMIT - 1) / LIMIT

                LoadResult.Page(
                    data = response.quotes,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (position >= totalPages - 1) null else position + 1
                )
       } catch (e:Exception){
           LoadResult.Error(e)
       }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}
