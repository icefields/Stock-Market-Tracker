package org.hungrytessy.stockmarkettracker.data.remote

import okhttp3.ResponseBody
import org.hungrytessy.stockmarkettracker.BuildConfig
import org.hungrytessy.stockmarkettracker.data.remote.dto.CompanyInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(@Query("apikey") apiKey: String = API_KEY): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): CompanyInfoDto

    companion object {
        const val API_KEY = BuildConfig.API_KEY
        const val BASE_URL = "https://alphavantage.co/"
    }
}
