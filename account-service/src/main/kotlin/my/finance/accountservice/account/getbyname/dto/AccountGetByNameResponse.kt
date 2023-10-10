package my.finance.accountservice.account.getbyname.dto

import java.util.UUID

data class AccountGetByNameResponse(
    val id: UUID,
    val name: String,
    val amount: Double,
)