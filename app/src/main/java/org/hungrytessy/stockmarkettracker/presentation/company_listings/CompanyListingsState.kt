package org.hungrytessy.stockmarkettracker.presentation.company_listings

import org.hungrytessy.stockmarkettracker.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
