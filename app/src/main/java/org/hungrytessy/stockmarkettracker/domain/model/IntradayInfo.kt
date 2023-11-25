package org.hungrytessy.stockmarkettracker.domain.model

import java.time.LocalDateTime

data class IntradayInfo(
    val timestamp: LocalDateTime,
    val close: Double
)
