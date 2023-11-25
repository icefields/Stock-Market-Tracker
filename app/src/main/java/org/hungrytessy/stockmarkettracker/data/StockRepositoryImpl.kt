package org.hungrytessy.stockmarkettracker.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.hungrytessy.stockmarkettracker.data.csv.CSVParser
import org.hungrytessy.stockmarkettracker.data.local.StockDatabase
import org.hungrytessy.stockmarkettracker.data.mapper.toCompanyListing
import org.hungrytessy.stockmarkettracker.data.mapper.toCompanyListingEntity
import org.hungrytessy.stockmarkettracker.data.remote.StockApi
import org.hungrytessy.stockmarkettracker.domain.model.CompanyListing
import org.hungrytessy.stockmarkettracker.domain.repository.StockRepository
import org.hungrytessy.stockmarkettracker.util.Resource
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(data = localListings.map { it.toCompanyListing() }))

            val isDbEmpty = localListings.isEmpty() && query.isEmpty()
            val shouldLoadCacheOnly = !isDbEmpty && !fetchRemote
            if(shouldLoadCacheOnly) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                emit(Resource.Error(message = "cannot load data", exception = e))
                null
            } catch (e: HttpException) {
                emit(Resource.Error(message = "cannot load data", exception = e))
                null
            }

            remoteListings?.let { listings ->

                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                // stick to the single source of truth pattern despite performance deterioration
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}
