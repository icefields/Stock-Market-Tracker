package org.hungrytessy.stockmarkettracker.data.csv

import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.hungrytessy.stockmarkettracker.data.mapper.toIntradayInfo
import org.hungrytessy.stockmarkettracker.data.remote.dto.IntradayInfoDto
import org.hungrytessy.stockmarkettracker.domain.model.CompanyListing
import org.hungrytessy.stockmarkettracker.domain.model.IntradayInfo
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.LocalDateTime
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
class IntradayInfoParser @Inject constructor(): CSVParser<IntradayInfo>  {
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(timestamp = timestamp, close = close.toDouble())
                    dto.toIntradayInfo()
            }
                .filter {
                    it.timestamp.dayOfMonth == LocalDate.now().minusDays(1).dayOfMonth
                }
                .sortedBy {
                    it.timestamp.hour
                }
                .also { csvReader.close() }
        }
    }
}
