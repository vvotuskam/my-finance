package my.finance.accountservice.feature.account.rest.dto.response

import java.util.*

data class AccountFullResponse(
    val id: UUID,
    val name: String,
    val amount: Double,
)