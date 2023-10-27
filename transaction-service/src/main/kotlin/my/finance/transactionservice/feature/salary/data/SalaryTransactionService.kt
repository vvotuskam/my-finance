package my.finance.transactionservice.feature.salary.data

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SalaryTransactionService(
    private val salaryRepository: SalaryTransactionRepository,
) {

    fun saveAll(transactions: List<SalaryTransaction>): List<SalaryTransaction> {
        return salaryRepository.saveAll(transactions)
    }

    fun findByCompanyIdAndAdmin(companyId: UUID, admin: String): List<SalaryTransaction> {
        return salaryRepository.findAllByCompanyIdAndAdmin(companyId, admin)
    }

    fun findByAccountAndEmail(accountId: UUID, email: String): List<SalaryTransaction> {
        return salaryRepository.findAllByAccountIdAndEmail(accountId, email)
    }
}