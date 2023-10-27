package my.finance.accountservice.feature.transaction.domain.source.response

import java.time.LocalDateTime
import java.util.*

data class TransactionHistoryResponse(
    val transactions: List<TransactionResponse>
) {
    data class TransactionResponse(
        val id: UUID,
        val transferredAt: LocalDateTime,
        val email: String,
        val amount: Double,
        val accountId: UUID,
        val secondId: UUID,
        val isPositive: Boolean,
        val description: String
    )
}