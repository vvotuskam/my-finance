package my.finance.accountservice.feature.transaction.data

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {

    @Transactional
    fun save(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }
}