package org.hungrytessy.stockmarkettracker.domain.repository

import kotlinx.coroutines.flow.Flow
import org.hungrytessy.stockmarkettracker.domain.model.CompanyListing
import org.hungrytessy.stockmarkettracker.util.Resource

interface StockRepository {
    suspend fun getCompanyListings(fetchRemote: Boolean, query: String): Flow<Resource<List<CompanyListing>>>
}
