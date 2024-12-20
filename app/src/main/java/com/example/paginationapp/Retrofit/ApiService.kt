package com.example.paginationapp.Retrofit

import com.example.paginationapp.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/quotes")
    suspend fun getQuotes(@Query("skip") skip: Int,
                           @Query("limit") limit: Int
    ) : QuoteList
}
