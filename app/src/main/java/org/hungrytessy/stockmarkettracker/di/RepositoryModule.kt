package org.hungrytessy.stockmarkettracker.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.hungrytessy.stockmarkettracker.data.StockRepositoryImpl
import org.hungrytessy.stockmarkettracker.data.csv.CSVParser
import org.hungrytessy.stockmarkettracker.data.csv.CompanyListingsParser
import org.hungrytessy.stockmarkettracker.domain.model.CompanyListing
import org.hungrytessy.stockmarkettracker.domain.repository.StockRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}
