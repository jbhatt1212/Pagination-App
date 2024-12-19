package com.example.paginationapp.Retrofit

import com.example.paginationapp.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {
    @GET("/v3/e12c628c-01c3-4587-86ac-fca369ea882c")
    suspend fun getQuotes(@Query("page") page: Int) : QuoteList
}