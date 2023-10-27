package my.finance.transactionservice.feature.transaction.rest.dto.request

import jakarta.validation.Valid

class TransactionBatchCreateRequest(
    val transactions: List<TransactionCreateRequest>?
) {
    class TransactionRequest(

    )
}