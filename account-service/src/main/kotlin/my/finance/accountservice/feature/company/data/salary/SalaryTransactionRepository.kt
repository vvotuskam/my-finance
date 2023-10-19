package my.finance.accountservice.feature.company.data.salary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SalaryTransactionRepository : JpaRepository<SalaryTransaction, UUID> {
}