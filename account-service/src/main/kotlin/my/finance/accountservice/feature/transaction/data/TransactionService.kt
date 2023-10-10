package my.finance.accountservice.feature.transaction.data

import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
}