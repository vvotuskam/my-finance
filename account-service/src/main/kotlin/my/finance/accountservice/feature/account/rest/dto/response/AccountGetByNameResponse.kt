package my.finance.accountservice.feature.account.rest.dto.response

import java.util.UUID

data class AccountGetByNameResponse(
    val id: UUID,
    val name: String,
    val amount: Double,
)