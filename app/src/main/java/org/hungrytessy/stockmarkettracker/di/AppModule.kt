package org.hungrytessy.stockmarkettracker.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.hungrytessy.stockmarkettracker.data.local.StockDatabase
import org.hungrytessy.stockmarkettracker.data.remote.StockApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi = Retrofit.Builder()
        .baseUrl(StockApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(StockApi::class.java)


    @Provides
    @Singleton
    fun provideStockDatabase(application: Application): StockDatabase = Room.databaseBuilder(
        application,
        StockDatabase::class.java,
        "stockdb.db"
    ).build()
}
