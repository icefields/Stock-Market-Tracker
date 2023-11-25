package org.hungrytessy.stockmarkettracker.data.csv

import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.hungrytessy.stockmarkettracker.domain.model.CompanyListing
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This object can be injected.
 * when annotating the class constructor with @Inject, Dagger already knows this object can be
 * injected, there's no need in this case to write a @Provide function in a module.
 * If the constructor needs parameters, those need to be Injectable, so either have an @Inject
 * constructor or have a @Provide method.
 * BUT
 * In this specific case, since we're passing the interface, not the implementation, (see
 * StockRepositoryImpl) to the other layers, we need to create a @Provide method.
 */
@Singleton
class CompanyListingsParser @Inject constructor(): CSVParser<CompanyListing>  {
    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                val symbol = line.getOrNull(0)
                val name = line.getOrNull(1)
                val exchange = line.getOrNull(2)
                CompanyListing(
                    name = name ?: return@mapNotNull null,
                    symbol = symbol ?: return@mapNotNull null,
                    exchange = exchange ?: return@mapNotNull null
                )
            }.also { csvReader.close() }
        }
    }
}
