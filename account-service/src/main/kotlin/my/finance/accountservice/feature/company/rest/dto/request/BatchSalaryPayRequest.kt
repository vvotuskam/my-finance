package my.finance.accountservice.feature.company.rest.dto.request

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication

data class BatchSalaryPayRequest(
    val authentication: Authentication,
    val companyId: String
)