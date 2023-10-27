package my.finance.transactionservice.feature.salary.rest.dto.response

import java.time.LocalDateTime
import java.util.*

data class SalaryResponse(
    val id: UUID,
    val transferredAt: LocalDateTime,

    val email: String,
    val amount: Double,
    val accountId: UUID,
    val companyId: UUID,
    val admin: String
)