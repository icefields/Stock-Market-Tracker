package org.hungrytessy.stockmarkettracker.data.remote.dto

import java.sql.Timestamp

data class IntradayInfoDto(
    val timestamp: String,
    val close: Double
)
