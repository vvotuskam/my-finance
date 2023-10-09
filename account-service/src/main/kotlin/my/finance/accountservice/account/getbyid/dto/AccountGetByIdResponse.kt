package my.finance.accountservice.account.getbyid.dto

import java.util.UUID

data class AccountGetByIdResponse(
    val id: UUID,
    val name: String,
    val amount: Double,
)