package my.finance.accountservice.feature.transaction.domain.source.request

class TransactionBatchCreateRequest(
    val transactions: List<TransactionCreateRequest>
)