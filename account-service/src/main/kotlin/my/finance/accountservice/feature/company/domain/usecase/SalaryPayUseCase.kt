package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.company.data.company.CompanyService
import my.finance.accountservice.feature.company.domain.failure.CompanyNotFoundFailure
import my.finance.accountservice.feature.company.domain.failure.EmployeeNotFoundFailure
import my.finance.accountservice.feature.company.domain.usecase.SalaryPayUseCase.SalaryPayParams
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.service.TransactionService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SalaryPayUseCase(
    private val companyService: CompanyService,
    private val accountService: AccountService,
    private val transactionService: TransactionService
): UseCase<SalaryPayParams, SuccessResponse> {

    data class SalaryPayParams(
        val admin: String,
        val employeeId: UUID,
        val companyId: UUID
    )

    override fun invoke(params: SalaryPayParams): SuccessResponse {
        val (admin, employeeId, companyId) = params

        val company = companyService.findById(companyId)
            ?: throw BusinessException(CompanyNotFoundFailure())

        if (company.admin != admin) throw BusinessException(CompanyNotFoundFailure())

        val employee = company.employees.firstOrNull { it.id == employeeId }
            ?: throw BusinessException(EmployeeNotFoundFailure())

        val paidAccount = employee.account.copy(
            amount = employee.salary + employee.account.amount
        )

        accountService.save(paidAccount)

        val transaction = TransactionCreateRequest(
            amount = employee.salary,
            accountId = employee.account.id!!,
            secondId = companyId,
            email = employee.email,
            isPositive = true,
            description = "Salary from '${company.name}'"
        )
        val securityUser = SecurityContextHolder.getContext().authentication.principal

        transactionService.create(transaction, securityUser as SecurityUser)

        return SuccessResponse("Salary paid")
    }
}