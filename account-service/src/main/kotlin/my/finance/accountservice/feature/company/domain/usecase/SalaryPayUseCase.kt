package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.company.data.company.CompanyService
import my.finance.accountservice.feature.company.data.salary.SalaryTransaction
import my.finance.accountservice.feature.company.data.salary.SalaryTransactionService
import my.finance.accountservice.feature.company.domain.failure.CompanyNotFoundFailure
import my.finance.accountservice.feature.company.domain.failure.EmployeeNotFoundFailure
import my.finance.accountservice.feature.company.domain.usecase.SalaryPayUseCase.SalaryPayParams
import org.springframework.stereotype.Component
import java.util.*

@Component
class SalaryPayUseCase(
    private val companyService: CompanyService,
    private val accountService: AccountService,
    private val transactionService: SalaryTransactionService
): UseCase<SalaryPayParams, SuccessResponse> {

    data class SalaryPayParams(
        val admin: User,
        val employeeId: UUID,
        val companyId: UUID
    )

    override fun invoke(params: SalaryPayParams): SuccessResponse {
        val (admin, employeeId, companyId) = params

        val company = companyService.findById(companyId)
            ?: throw BusinessException(CompanyNotFoundFailure())

        if (company.admin.id != admin.id) throw BusinessException(CompanyNotFoundFailure())

        val employee = company.employees.firstOrNull { it.id == employeeId }
            ?: throw BusinessException(EmployeeNotFoundFailure())

        val paidAccount = employee.account.copy(
            amount = employee.salary + employee.account.amount
        )

        accountService.save(paidAccount)

        val transaction = SalaryTransaction(
            amount = employee.salary,
            transferredBy = admin,
            company = company,
            employee = employee
        )

        transactionService.saveAll(listOf(transaction))

        return SuccessResponse("Salary paid")
    }
}