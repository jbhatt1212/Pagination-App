package com.example.paginationapp.paging

import android.content.Context
import androidx.room.Room
import com.example.paginationapp.db.QuotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
 @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : QuoteDatabase{
        return Room.databaseBuilder(context, QuoteDatabase::class.java, "quoteDb")
            .fallbackToDestructiveMigration()
            .build()
    }
}
