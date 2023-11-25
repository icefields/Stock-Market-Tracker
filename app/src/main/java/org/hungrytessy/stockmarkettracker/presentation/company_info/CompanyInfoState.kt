package org.hungrytessy.stockmarkettracker.presentation.company_info

import org.hungrytessy.stockmarkettracker.domain.model.CompanyInfo
import org.hungrytessy.stockmarkettracker.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val companyInfo: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
