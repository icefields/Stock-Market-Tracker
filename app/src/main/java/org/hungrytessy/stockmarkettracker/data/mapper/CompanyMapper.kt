package org.hungrytessy.stockmarkettracker.data.mapper

import org.hungrytessy.stockmarkettracker.data.local.CompanyListingEntity
import org.hungrytessy.stockmarkettracker.data.remote.dto.CompanyInfoDto
import org.hungrytessy.stockmarkettracker.domain.model.CompanyInfo
import org.hungrytessy.stockmarkettracker.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing = CompanyListing(
    name = name,
    symbol = symbol,
    exchange = exchange
)

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity = CompanyListingEntity(
    name = name,
    symbol = symbol,
    exchange = exchange
)

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo = CompanyInfo(
    symbol = symbol ?: "",
    description = description ?: "",
    name = name ?: "",
    country = country ?: "",
    industry = industry ?: ""
)
