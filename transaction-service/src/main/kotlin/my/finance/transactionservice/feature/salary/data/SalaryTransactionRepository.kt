package my.finance.transactionservice.feature.salary.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SalaryTransactionRepository : JpaRepository<SalaryTransaction, UUID> {

    fun findAllByCompanyIdAndAdmin(companyId: UUID, admin: String): List<SalaryTransaction>

    fun findAllByAccountIdAndEmail(accountId: UUID, email: String): List<SalaryTransaction>
}