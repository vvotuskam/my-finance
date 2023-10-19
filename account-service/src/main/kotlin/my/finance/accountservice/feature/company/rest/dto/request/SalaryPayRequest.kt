package my.finance.accountservice.feature.company.rest.dto.request

import org.springframework.security.core.Authentication

data class SalaryPayRequest(
    val employeeId: String,
    val companyId: String,
    val authentication: Authentication
)