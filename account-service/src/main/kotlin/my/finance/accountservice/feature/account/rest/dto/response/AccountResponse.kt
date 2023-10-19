package my.finance.accountservice.feature.account.rest.dto.response

import java.util.*

data class AccountResponse(
    val id: UUID,
    val name: String,
    val owner: String
)