package my.finance.transactionservice.feature.salary.domain.usecase

import my.finance.transactionservice.core.domain.usecase.UseCase
import my.finance.transactionservice.core.rest.dto.SuccessResponse
import my.finance.transactionservice.feature.salary.data.SalaryTransaction
import my.finance.transactionservice.feature.salary.data.SalaryTransactionService
import my.finance.transactionservice.feature.salary.domain.usecase.SalaryTransactionUseCase.SalaryParams
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SalaryTransactionUseCase(
    private val salaryService: SalaryTransactionService
) : UseCase<SalaryParams, SuccessResponse> {

    data class SalaryParams(
        val admin: String,
        val companyId: UUID,
        val employees: List<EmployeeParams>
    ) {
        data class EmployeeParams(
            val accountId: UUID,
            val salary: Double,
            val email: String
        )
    }

    override fun invoke(params: SalaryParams): SuccessResponse {
        val transactions = params.employees.map {
            SalaryTransaction(
                email = it.email,
                amount = it.salary,
                accountId = it.accountId,
                companyId = params.companyId,
                admin = params.admin
            )
        }
        salaryService.saveAll(transactions)
        return SuccessResponse("Transferred")
    }
}