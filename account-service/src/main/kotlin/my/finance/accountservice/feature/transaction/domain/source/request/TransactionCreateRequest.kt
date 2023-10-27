package my.finance.accountservice.feature.transaction.domain.source.request

import java.util.*

class TransactionCreateRequest(
    val email: String,
    val accountId: UUID,
    val secondId: UUID,
    val amount: Double,
    val isPositive: Boolean,
    val description: String,
)