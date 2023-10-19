package my.finance.accountservice.feature.company.data.salary

import org.springframework.stereotype.Service

@Service
class SalaryTransactionService(
    private val salaryTransactionRepository: SalaryTransactionRepository
) {

    fun saveAll(transactions: List<SalaryTransaction>): List<SalaryTransaction> {
        return salaryTransactionRepository.saveAll(transactions)
    }
}