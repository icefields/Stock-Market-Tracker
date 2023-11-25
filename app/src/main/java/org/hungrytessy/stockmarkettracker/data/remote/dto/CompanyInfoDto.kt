package org.hungrytessy.stockmarkettracker.data.remote.dto

import com.squareup.moshi.Json

/**
 * fields have to be nullable because we might get an error response since our free quota is very
 * limited
 */
data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol: String?,
    @field:Json(name = "Description") val description: String?,
    @field:Json(name = "Name") val name: String?,
    @field:Json(name = "Country") val country: String?,
    @field:Json(name = "Industry") val industry: String?
)
