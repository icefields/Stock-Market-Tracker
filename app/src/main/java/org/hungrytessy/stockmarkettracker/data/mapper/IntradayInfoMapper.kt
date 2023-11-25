package org.hungrytessy.stockmarkettracker.data.mapper

import org.hungrytessy.stockmarkettracker.data.remote.dto.IntradayInfoDto
import org.hungrytessy.stockmarkettracker.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        timestamp = localDateTime,
        close = close
    )
}
