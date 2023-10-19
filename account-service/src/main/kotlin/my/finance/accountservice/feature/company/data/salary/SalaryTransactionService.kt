package my.finance.accountservice.feature.company.data.salary

import org.springframework.stereotype.Service

@Service
class SalaryTransactionService(
    private val salaryTransactionRepository: SalaryTransactionRepository
) {

}