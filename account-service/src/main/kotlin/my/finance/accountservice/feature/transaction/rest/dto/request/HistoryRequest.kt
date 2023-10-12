package my.finance.accountservice.feature.transaction.rest.dto.request

import org.springframework.security.core.Authentication

data class HistoryRequest(
    val name: String,
    val authentication: Authentication
)