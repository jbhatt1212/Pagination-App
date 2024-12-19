package com.example.paginationapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paginationapp.paging.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class QuoteViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {
    val list = repository.getQuotes().cachedIn(viewModelScope)
}