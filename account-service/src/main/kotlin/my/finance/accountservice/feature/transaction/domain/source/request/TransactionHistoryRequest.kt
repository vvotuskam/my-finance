package my.finance.accountservice.feature.transaction.domain.source.request

import java.util.*

data class TransactionHistoryRequest(
    val accountId: UUID
)