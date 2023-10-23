package my.finance.transactionservice.feature.transaction.data

import org.springframework.stereotype.Service
import javax.lang.model.type.PrimitiveType

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    fun save(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }
}