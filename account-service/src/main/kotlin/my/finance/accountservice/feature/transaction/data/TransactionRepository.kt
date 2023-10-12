package my.finance.accountservice.feature.transaction.data

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.feature.account.data.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {

    @Query("from Transaction where from = :account or to = :account")
    fun findAllByAccount(account: Account): List<Transaction>
}