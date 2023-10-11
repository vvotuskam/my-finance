package my.finance.accountservice.feature.account.rest.dto.response

import java.util.UUID

data class AccountFullResponse(
    val id: UUID,
    val name: String,
    val amount: Double,
)