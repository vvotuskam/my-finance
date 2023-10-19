package my.finance.accountservice.feature.transaction.data

import my.finance.accountservice.feature.account.data.Account
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

    fun findAllByAccount(account: Account): List<Transaction> {
        return transactionRepository.findAllByAccount(account)
    }
}