package my.finance.transactionservice.feature.transaction.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {
}