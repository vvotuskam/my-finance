package my.finance.transactionservice.feature.transaction.data

import org.springframework.stereotype.Service
import java.util.UUID
import javax.lang.model.type.PrimitiveType

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    fun save(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }

    fun findByEmailAndAccount(email: String, accountId: UUID): List<Transaction> {
        return transactionRepository.findAllByEmailAndAccountId(email, accountId)
    }

    fun saveAll(transactions: List<Transaction>): List<Transaction> {
        return transactionRepository.saveAll(transactions)
    }
}